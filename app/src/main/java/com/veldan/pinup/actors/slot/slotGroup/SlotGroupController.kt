package com.veldan.pinup.actors.slot.slotGroup

import com.veldan.pinup.actors.slot.util.*
import com.veldan.pinup.utils.controller.GroupController
import com.veldan.pinup.utils.log
import com.veldan.pinup.utils.toDelay
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.flow.take

class SlotGroupController(override val group: SlotGroup) : GroupController {

    companion object {
        const val SLOT_COUNT = 3
        const val GLOW_COUNT = SLOT_COUNT

        // seconds
        const val TIME_SHOW_WIN = 2f
    }

    private var winNumber       = (1..2).random()
    private var miniGameNumber  = (2..3).random()
    private var superGameNumber = (3..4).random()

    private var spinWinCounter       = 0
    private var spinMiniGameCounter  = 0
    private var spinSuperGameCounter = 0

    private val fillManager by lazy { FillManager(group.slotList) }

    private var bonus: Bonus? = null



    private fun fillSlots() {
        when {
            spinSuperGameCounter == superGameNumber -> {
                fillManager.fill(FillStrategy.SUPER)
                bonus = Bonus.SUPER_GAME
            }
            spinSuperGameCounter == miniGameNumber  -> {
                fillManager.fill(FillStrategy.MINI)
                bonus = Bonus.MINI_GAME
            }
            spinWinCounter == winNumber             -> {
                fillManager.fill(FillStrategy.WIN)
            }
            else                                    -> {
                fillManager.fill(FillStrategy.RANDOM)
            }
        }
    }

    private fun resetWin() {
        spinWinCounter = 0
        winNumber      = (1..2).random()
    }

    private fun resetBonus() {
        fun resetMiniGame() {
            spinMiniGameCounter = 0
            miniGameNumber      = (2..3).random()
        }
        fun resetSuperGame() {
            spinSuperGameCounter = 0
            superGameNumber      = (3..4).random()
        }

        if (spinWinCounter == winNumber) resetWin()
        if (spinMiniGameCounter == miniGameNumber) resetMiniGame()
        if (spinSuperGameCounter == superGameNumber) resetSuperGame()

        bonus = null
    }

    private fun logCounter() {
        log("""
            
            winSpinCounter = $spinWinCounter WIN_NUM = $winNumber
            miniGameSpinCounter = $spinMiniGameCounter MINI_NUM = $miniGameNumber
            superGameSpinCounter = $spinSuperGameCounter SUPER_NUM = $superGameNumber
        """)
    }

    private fun FillResult.checkBonus() {
        val wildCount = winSlotItemList.count { it.id == SlotItemContainer.wild.id }
        bonus = when(wildCount) {
            2 -> Bonus.MINI_GAME
            3 -> Bonus.SUPER_GAME
            else -> bonus
        }
    }

    private suspend fun FillResult.showWin() = CompletableDeferred<Boolean>().also { continuation ->
        val glowInCounterFlow = MutableSharedFlow<Boolean>(replay = intersectionList.size)

        intersectionList.onEach { intersection ->
            val glow = group.glowList[intersection.slotIndex]
            glow.controller.glowIn(intersection.rowIndex).apply { glowInCounterFlow.emit(true) }
        }

        glowInCounterFlow.take(intersectionList.size).collectIndexed { index, _ ->
            if (index == intersectionList.size.dec()) {
                glowInCounterFlow.resetReplayCache()
                delay(TIME_SHOW_WIN.toDelay)
                group.glowList.onEach { glow -> glow.controller.glowOutAll() }
                continuation.complete(true)
            }
        }

    }.await()



    suspend fun spin() = CompletableDeferred<SpinResult>().also { continuation ->
        spinWinCounter++
        spinMiniGameCounter++
        spinSuperGameCounter++

        logCounter()
        fillSlots()

        val spinCounterFlow = MutableSharedFlow<Boolean>(replay = 3)
        val coroutineSpin   = CoroutineScope(Dispatchers.Default)

        group.slotList.onEach { coroutineSpin.launch {
            it.controller.spin().apply { spinCounterFlow.emit(true) }
        } }

        spinCounterFlow.take(3).collectIndexed { index, _ ->
            if (index == 2 ) {
                coroutineSpin.cancel()
                spinCounterFlow.resetReplayCache()

                fillManager.winFillResult?.checkBonus()
                if (bonus == null) { fillManager.winFillResult?.showWin() }

                continuation.complete(SpinResult(
                    winSlotItemSet = fillManager.winFillResult?.winSlotItemList?.toSet()?.apply { resetWin() },
                    bonus = bonus.apply { resetBonus() }
                ))
            }
        }
    }.await()

}