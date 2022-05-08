package com.veldan.veldanslots.game.actors.slot.util

import com.veldan.veldanslots.game.actors.slot.slot.Slot
import com.veldan.veldanslots.game.actors.slot.slot.SlotController
import com.veldan.veldanslots.game.actors.slot.util.combination.Combination
import com.veldan.veldanslots.game.actors.slot.util.combination.CombinationFailSuperGame
import com.veldan.veldanslots.game.actors.slot.util.combination.CombinationMatrixEnum
import com.veldan.veldanslots.game.actors.slot.util.combination.CombinationWinSuperGame
import com.veldan.veldanslots.utils.log
import com.veldan.veldanslots.utils.probability

class FillManager(val slotList: List<Slot>) {

    var winFillResult: FillResult? = null
        private set



    private fun fillMix(isUseWild: Boolean = true) {
        log("FILL_MIX")

        val combinationMatrixEnum: CombinationMatrixEnum = if (isUseWild && probability(20)) {
            log("FILL_RANDOM use WILD")
            Combination.MixWithWild.values().random()
        } else Combination.Mix.values().random()

        combinationMatrixEnum.logCombinationMatrixEnum()

        slotList.onEachIndexed { index, slot ->
            slot.slotItemWinList = combinationMatrixEnum.matrix.generateSlot(index)
        }
    }

    private fun fillWin() {
        log("FILL_WIN")

        val combinationMatrixEnum = listOf<CombinationMatrixEnum>(
            *Combination.WinMonochrome.values(),
            *Combination.WinColorful.values(),
            *Combination.WinWithWild.values(),
        ).random()
        combinationMatrixEnum.logCombinationMatrixEnum()
        val combinationMatrix = combinationMatrixEnum.matrix.init()

        slotList.onEachIndexed { index, slot ->
            slot.slotItemWinList = combinationMatrix.generateSlot(index)
        }

        log("""
            
            scheme = ${combinationMatrix.scheme}
            slotIndex = ${combinationMatrix.intersectionList?.map { it.slotIndex }}
            rowIndex = ${combinationMatrix.intersectionList?.map { it.rowIndex }}
        """.trimIndent())

        winFillResult = with(combinationMatrix) {
            if (winSlotItemList != null) FillResult(winSlotItemList!!, intersectionList!!)
            else null
        }
    }

    private fun fillMini() {
        log("FILL_MINI")
        fillMix(false)

        val randomSlotList = slotList.shuffled().take(2)
        randomSlotList.onEach { slot -> with(slot) { slotItemWinList = slotItemWinList.toMutableList().apply {
            setInRandomRow(SlotItemContainer.scatter)
        } } }
    }

    private fun fillSuper() {
        log("FILL_SUPER")
        fillMix(false)

        val randomSlotList = slotList.shuffled().take(3)
        randomSlotList.onEach { slot -> with(slot) { slotItemWinList = slotItemWinList.toMutableList().apply {
            setInRandomRow(SlotItemContainer.scatter)
        } } }
    }

    private fun fillWinSuperGame(slotNumber: Int) {
        log("FILL_WIN_SUPER_GAME")

        val slot1 = CombinationWinSuperGame.Slot1.values()
        val slot2 = CombinationWinSuperGame.Slot2.values()
        val slot3 = CombinationWinSuperGame.Slot3.values()
        val slot4 = CombinationWinSuperGame.Slot4.values()
        val slot5 = CombinationWinSuperGame.Slot5.values()


        val combinationMatrixEnum = listOf(slot1, slot2, slot3, slot4, slot5)[slotNumber.dec()].random()
        combinationMatrixEnum.logCombinationMatrixEnum()
        val combinationMatrix = combinationMatrixEnum.matrix.init()

        slotList.onEachIndexed { index, slot ->
            slot.slotItemWinList = combinationMatrix.generateSlot(index)
        }

        log("""
            
            scheme = ${combinationMatrix.scheme}
            slotIndex = ${combinationMatrix.intersectionList?.map { it.slotIndex }}
            rowIndex = ${combinationMatrix.intersectionList?.map { it.rowIndex }}
        """.trimIndent())

        winFillResult = with(combinationMatrix) {
            if (winSlotItemList != null) FillResult(winSlotItemList!!, intersectionList!!)
            else null
        }


    }

    private fun fillFailSuperGame(slotNumber: Int) {
        log("FILL_FAIL_SUPER_GAME")

        val slot1 = CombinationFailSuperGame.Slot1.values()
        val slot2 = CombinationFailSuperGame.Slot2.values()
        val slot3 = CombinationFailSuperGame.Slot3.values()
        val slot4 = CombinationFailSuperGame.Slot4.values()
        val slot5 = CombinationFailSuperGame.Slot5.values()

        val combinationMatrixEnum = listOf(slot1, slot2, slot3, slot4, slot5)[slotNumber.dec()].random()
        combinationMatrixEnum.logCombinationMatrixEnum()
        val combinationMatrix = combinationMatrixEnum.matrix.init()

        slotList.onEachIndexed { index, slot ->
            slot.slotItemWinList = combinationMatrix.generateSlot(index)
        }

    }

    private fun MutableList<SlotItem>.setInRandomRow(slotItem: SlotItem) = apply {
        val randomRow = (0 until SlotController.SLOT_ITEM_VISIBLE_COUNT).random()
        set(randomRow, slotItem)
    }



    fun fill(fillStrategy: FillStrategy) {
        winFillResult = null

        when (fillStrategy) {
            is FillStrategy.MIX             -> fillMix()
            is FillStrategy.WIN             -> fillWin()
            is FillStrategy.MINI            -> fillMini()
            is FillStrategy.SUPER           -> fillSuper()
            is FillStrategy.WIN_SUPER_GAME  -> fillWinSuperGame(fillStrategy.slotNumber)
            is FillStrategy.FAIL_SUPER_GAME -> fillFailSuperGame(fillStrategy.slotNumber)
        }
    }



    private fun CombinationMatrixEnum.logCombinationMatrixEnum() {
        val combinationEnumName = when (this) {
            is Combination.Mix           -> "Комбинация Mix $name"
            is Combination.MixWithWild   -> "Комбинация Mix WILD $name"
            is Combination.WinMonochrome -> "Комбинация Победа Одноцветная $name"
            is Combination.WinColorful   -> "Комбинация Победа Разноцветная $name"
            is Combination.WinWithWild   -> "Комбинация Победа WILD $name"

            is CombinationWinSuperGame.Slot1 -> "Комбинация WIN_SUPER_GAME Slot1 $name"
            is CombinationWinSuperGame.Slot2 -> "Комбинация WIN_SUPER_GAME Slot2 $name"
            is CombinationWinSuperGame.Slot3 -> "Комбинация WIN_SUPER_GAME Slot3 $name"
            is CombinationWinSuperGame.Slot4 -> "Комбинация WIN_SUPER_GAME Slot4 $name"
            is CombinationWinSuperGame.Slot5 -> "Комбинация WIN_SUPER_GAME Slot5 $name"

            is CombinationFailSuperGame.Slot1 -> "Комбинация FAIL_SUPER_GAME Slot1 $name"
            is CombinationFailSuperGame.Slot2 -> "Комбинация FAIL_SUPER_GAME Slot2 $name"
            is CombinationFailSuperGame.Slot3 -> "Комбинация FAIL_SUPER_GAME Slot3 $name"
            is CombinationFailSuperGame.Slot4 -> "Комбинация FAIL_SUPER_GAME Slot4 $name"
            is CombinationFailSuperGame.Slot5 -> "Комбинация FAIL_SUPER_GAME Slot5 $name"
            else                         -> "Noname"
        }
        log(combinationEnumName)
    }

}