package com.veldan.pinup.actors.slot.util

import com.veldan.pinup.actors.slot.slot.Slot
import com.veldan.pinup.actors.slot.util.combination.*
import com.veldan.pinup.utils.log
import com.veldan.pinup.utils.probability

class FillManager(val slotList: List<Slot>) {

    var fillResult: FillResult? = null
        private set



    private fun fillRandom(isUseWild: Boolean = true) {
        log("FILL_RANDOM")

        val combinationMatrix: CombinationMatrixEnum = if (isUseWild && probability(20)) {
            log("FILL_RANDOM use WILD")
            CombinationRandomWithWild.values().random()
        } else CombinationRandom.values().random()

        slotList.onEachIndexed { index, slot ->
            slot.slotItemWinList = combinationMatrix.matrix.generateSlot(index)
        }
    }

    private fun fillWin() {
        log("FILL_WIN")

        val combinationMatrixEnum = listOf<CombinationMatrixEnum>(
            *CombinationWinNonIntersectingColorful.values(),
            *CombinationWinIntersectingMonochrome.values(),
            *CombinationWinIntersectingMonochromeWithWild.values(),
            *CombinationWinIntersectingColorfulWithWild.values(),
        ).random()

        combinationMatrixEnum.logCombinationMatrixEnum()

        val combinationMatrix = combinationMatrixEnum.matrix

        slotList.onEachIndexed { index, slot ->
            slot.slotItemWinList = combinationMatrix.generateSlot(index)
        }

        fillResult = with(combinationMatrix) {
            if (winSlotItemSet != null) FillResult(winSlotItemSet!!, intersectionList!!)
            else null
        }
    }

    private fun fillSuper() {
//        log("FILL_SUPER")
//        fillRandom(false)
//
//        val randomSlotList = slotList.shuffled().take(3)
//        randomSlotList.onEach { slot -> with(slot) { slotItemWinList = slotItemWinList.toMutableList().apply { setInRandomRow(slotItemScatter) } } }
    }



    fun fill(fillStrategy: FillStrategy) {
        fillResult = null

        when (fillStrategy) {
            FillStrategy.RANDOM -> fillRandom()
            FillStrategy.WIN    -> fillWin()
            FillStrategy.MINI   -> fillSuper()
            FillStrategy.SUPER  -> fillSuper()
        }
    }



    private fun CombinationMatrixEnum.logCombinationMatrixEnum() {
        val combinationEnumName = when (this) {
            is CombinationWinNonIntersectingColorful        -> "Комбинация Победы { Не пересекающаяся | Разноцветная } $name"
            is CombinationWinIntersectingMonochrome         -> "Комбинация Победы { Пересекающаяся | Одноцветная } $name"
            is CombinationWinIntersectingMonochromeWithWild -> "Комбинация Победы { Пересекающаяся | Одноцветная | WILD } $name"
            is CombinationWinIntersectingColorfulWithWild   -> "Комбинация Победы { Пересекающаяся | Разноцветная | WILD } $name"
            else                                            -> "Noname"
        }
        log(combinationEnumName)
    }

}