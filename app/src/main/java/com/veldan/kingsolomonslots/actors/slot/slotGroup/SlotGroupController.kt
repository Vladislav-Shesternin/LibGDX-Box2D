package com.veldan.kingsolomonslots.actors.slot.slotGroup

import com.veldan.kingsolomonslots.actors.slot.util.*
import com.veldan.kingsolomonslots.utils.controller.GroupController
import com.veldan.kingsolomonslots.utils.log
import com.veldan.kingsolomonslots.utils.probability
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



    private fun fillSlots(isSuperGame: Boolean, slotNumber: Int) {
        if (isSuperGame) {
            val fillStrategy = if(probability(20)) FillStrategy.WIN_SUPER_GAME(slotNumber) else FillStrategy.FAIL_SUPER_GAME(slotNumber)
            fillManager.fill(fillStrategy)
        }
        else when {
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
                fillManager.fill(FillStrategy.SUPER)
                bonus = Bonus.SUPER_GAME
            }
        }
    }

    private fun resetWin() {
        spinWinCounter = 0
        winNumber      = (1..1).random()
    }

    private fun resetBonus() {
        fun resetMiniGame() {
            spinMiniGameCounter = 0
            miniGameNumber      = (2..2).random()
        }
        fun resetSuperGame() {
            spinSuperGameCounter = 0
            superGameNumber      = (3..3).random()
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

    private suspend fun FillResult.showWin() {
        intersectionList.onEach { intersection ->
            group.glowList[intersection.slotIndex].controller.glowIn(intersection.rowIndex)
        }
    }



    suspend fun spin(isSuperGame: Boolean, slotNumber: Int) = CompletableDeferred<SpinResult>().also { continuation ->
        if (isSuperGame.not()) {
            spinWinCounter++
            spinMiniGameCounter++
            spinSuperGameCounter++
        }

        logCounter()
        fillSlots(isSuperGame, slotNumber)

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

        val winSlotItemSet = fillManager.winFillResult?.apply {
            showWin()
            delay(TIME_SHOW_WIN.toDelay)
            group.glowList.onEach { it.controller.glowOutAll() }
        }?.winSlotItemList?.toSet()

        continuation.complete(SpinResult(
            winSlotItemSet = winSlotItemSet?.apply { resetWin() },
            bonus = bonus?.apply { resetBonus() }
        ))

    }.await()

}