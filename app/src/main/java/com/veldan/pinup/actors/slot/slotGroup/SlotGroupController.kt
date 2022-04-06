package com.veldan.pinup.actors.slot.slotGroup

import com.veldan.pinup.actors.slot.util.*
import com.veldan.pinup.utils.controller.GroupController
import com.veldan.pinup.utils.log
import kotlinx.coroutines.coroutineScope

class SlotGroupController(override val group: SlotGroup) : GroupController {

    companion object {
        const val SLOT_COUNT = 3
       // const val GLOW_COUNT = SLOT_COUNT

        // seconds
        //const val TIME_BETWEEN_SPIN        = SlotController.TIME_SPIN / SLOT_COUNT
        const val TIME_WAIT_AFTER_GLOW_IN  = 2f
    }

    private var winNumber       = (1..5).random()
    private var miniGameNumber  = (5..8).random()
    private var superGameNumber = (8..10).random()

    private var spinWinCounter       = 0
    private var spinMiniGameCounter  = 0
    private var spinSuperGameCounter = 0

    private val fillManager by lazy { FillManager(group.slotList) }

    private var bonus: Bonus? = null



    private fun fillSlots() {
        when {
           // spinSuperGameCounter == superGameNumber -> {
           //     fillManager.fill(FillStrategy.SUPER)
           //     bonus = Bonus.SUPER_GAME
           // }
           // spinSuperGameCounter == miniGameNumber  -> {
           //     fillManager.fill(FillStrategy.MINI)
           //     bonus = Bonus.MINI_GAME
           // }
           // spinWinCounter == winNumber             -> {
           //     fillManager.fill(FillStrategy.WIN)
           // }
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
            miniGameNumber      = (8..10).random()
        }
        fun resetSuperGame() {
            spinSuperGameCounter = 0
            superGameNumber      = (8..10).random()
        }

        if (spinMiniGameCounter == miniGameNumber) resetMiniGame()
        if (spinSuperGameCounter == superGameNumber) resetSuperGame()

        bonus = null
    }



    suspend fun spin() = coroutineScope<SpinResult> {
        spinWinCounter++
        spinMiniGameCounter++
        spinSuperGameCounter++
        logCounter()

        fillSlots()

        group.slotList.onEach {
            it.controller.spin()
        }

        return@coroutineScope SpinResult(
            winSlotItemSet = null,
            bonus = null
        )
    }



    private fun logCounter() {
        log("""
            
            winSpinCounter = $spinWinCounter WIN_NUM = $winNumber
            superGameSpinCounter = $spinSuperGameCounter SUPER_NUM = $superGameNumber
        """)
    }

}