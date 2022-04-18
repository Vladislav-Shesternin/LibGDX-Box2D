package com.veldan.kingsolomonslots.actors.slot.slotGroup

import com.veldan.kingsolomonslots.actors.slot.util.Bonus
import com.veldan.kingsolomonslots.actors.slot.util.FillManager
import com.veldan.kingsolomonslots.actors.slot.util.FillStrategy
import com.veldan.kingsolomonslots.actors.slot.util.SpinResult
import com.veldan.kingsolomonslots.utils.controller.GroupController
import com.veldan.kingsolomonslots.utils.log
import com.veldan.kingsolomonslots.utils.toDelay
import kotlinx.coroutines.*

class SlotGroupController(override val group: SlotGroup) : GroupController {

    companion object {
        const val SLOT_COUNT = 5
        const val GLOW_COUNT = SLOT_COUNT

        // seconds
        const val TIME_SHOW_WIN     = 2f
        const val TIME_BETWEEN_SPIN = 0.3f
    }

    private var winNumber       = (1..1).random()
    private var miniGameNumber  = (2..2).random()
    private var superGameNumber = (3..3).random()

    private var spinWinCounter       = 0
    private var spinMiniGameCounter  = 0
    private var spinSuperGameCounter = 0

    private val fillManager by lazy { FillManager(group.slotList) }

    private var bonus: Bonus? = null



    private fun fillSlots() {
        when {
//            spinSuperGameCounter == superGameNumber -> {
//                fillManager.fill(FillStrategy.SUPER)
//                bonus = Bonus.SUPER_GAME
//            }
//            spinMiniGameCounter == miniGameNumber   -> {
//                fillManager.fill(FillStrategy.MINI)
//                bonus = Bonus.MINI_GAME
//            }
//            spinWinCounter == winNumber             -> {
//                fillManager.fill(FillStrategy.WIN)
//            }
            else                                    -> {
                fillManager.fill(FillStrategy.WIN)
            }
        }
    }

    private fun resetWin() {
        spinWinCounter = 0
        winNumber      = (1..5).random()
    }

    private fun resetBonus() {
        fun resetMiniGame() {
            spinMiniGameCounter = 0
            miniGameNumber      = (6..10).random()
        }
        fun resetSuperGame() {
            spinSuperGameCounter = 0
            superGameNumber      = (11..15).random()
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

//    private suspend fun FillResult.showWin() = CompletableDeferred<Boolean>().also { continuation ->
//        val glowInCounterFlow = MutableSharedFlow<Boolean>(replay = intersectionList.size)
//
//        intersectionList.onEach { intersection ->
//            val glow = group.glowList[intersection.slotIndex]
//            glow.controller.glowIn(intersection.rowIndex).apply { glowInCounterFlow.emit(true) }
//        }
//
//        glowInCounterFlow.take(intersectionList.size).collectIndexed { index, _ ->
//            if (index == intersectionList.size.dec()) {
//                glowInCounterFlow.resetReplayCache()
//                delay(TIME_SHOW_WIN.toDelay)
//                group.glowList.onEach { glow -> glow.controller.glowOutAll() }
//                continuation.complete(true)
//            }
//        }
//
//    }.await()



    suspend fun spin() = CompletableDeferred<SpinResult>().also { continuation ->
        spinWinCounter++
        spinMiniGameCounter++
        spinSuperGameCounter++

        logCounter()
        fillSlots()

        val completableSpinList = List(SLOT_COUNT) { CompletableDeferred<Boolean>() }
        group.slotList.onEachIndexed { slotIndex, slot ->
            CoroutineScope(Dispatchers.Default).launch {
                slot.controller.spin()
                completableSpinList[slotIndex].complete(true)
                cancel()
            }
            delay(TIME_BETWEEN_SPIN.toDelay)
        }
        completableSpinList.onEach { it.await() }
//        if (bonus == null) {
//            fillManager.winFillResult?.showWin()
//        }
//
//        val winSlotItemSet = if (bonus == null) fillManager.winFillResult?.winSlotItemList?.toSet() else null

        continuation.complete(SpinResult(
            winSlotItemSet = null,// winSlotItemSet?.apply { resetWin() },
            bonus = bonus?.apply { resetBonus() }
        ))

    }.await()

}