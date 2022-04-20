package com.veldan.kingsolomonslots.actors.slot.util

import com.veldan.kingsolomonslots.actors.slot.slot.Slot
import com.veldan.kingsolomonslots.actors.slot.slot.SlotController
import com.veldan.kingsolomonslots.actors.slot.util.combination.Combination
import com.veldan.kingsolomonslots.actors.slot.util.combination.CombinationMatrixEnum
import com.veldan.kingsolomonslots.utils.log
import com.veldan.kingsolomonslots.utils.probability

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

    private fun fillWinSuperGame() {
        log("FILL_WIN_SUPER_GAME")

    }

    private fun fillFailSuperGame() {
        log("FILL_FAIL_SUPER_GAME")

    }

    private fun MutableList<SlotItem>.setInRandomRow(slotItem: SlotItem) = apply {
        val randomRow = (0 until SlotController.SLOT_ITEM_VISIBLE_COUNT).random()
        set(randomRow, slotItem)
    }



    fun fill(fillStrategy: FillStrategy) {
        winFillResult = null

        when (fillStrategy) {
            FillStrategy.MIX             -> fillMix()
            FillStrategy.WIN             -> fillWin()
            FillStrategy.MINI            -> fillMini()
            FillStrategy.SUPER           -> fillSuper()
            FillStrategy.WIN_SUPER_GAME  -> fillWinSuperGame()
            FillStrategy.FAIL_SUPER_GAME -> fillFailSuperGame()
        }
    }



    private fun CombinationMatrixEnum.logCombinationMatrixEnum() {
        val combinationEnumName = when (this) {
            is Combination.Mix           -> "Комбинация Mix $name"
            is Combination.MixWithWild   -> "Комбинация Mix WILD $name"
            is Combination.WinMonochrome -> "Комбинация Победа Одноцветная $name"
            is Combination.WinColorful   -> "Комбинация Победа Разноцветная $name"
            is Combination.WinWithWild   -> "Комбинация Победа WILD $name"
            else                         -> "Noname"
        }
        log(combinationEnumName)
    }

}