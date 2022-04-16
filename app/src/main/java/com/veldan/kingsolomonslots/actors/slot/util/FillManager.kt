//package com.veldan.pinup2.actors.slot.util
//
//import com.veldan.pinup2.actors.slot.slot.Slot
//import com.veldan.pinup2.actors.slot.slot.SlotController
//import com.veldan.pinup2.actors.slot.util.combination.*
//import com.veldan.pinup2.utils.log
//import com.veldan.pinup2.utils.probability
//
//class FillManager(val slotList: List<Slot>) {
//
//    var winFillResult: FillResult? = null
//        private set
//
//
//
//    private fun fillRandom(isUseWild: Boolean = true) {
//        log("FILL_RANDOM")
//
//        val combinationMatrixEnum: CombinationMatrixEnum = if (isUseWild && probability(20)) {
//            log("FILL_RANDOM use WILD")
//            CombinationRandomWithWild.values().random()
//        } else CombinationRandom.values().random()
//
//        combinationMatrixEnum.logCombinationMatrixEnum()
//
//        slotList.onEachIndexed { index, slot ->
//            slot.slotItemWinList = combinationMatrixEnum.matrix.generateSlot(index)
//        }
//    }
//
//    private fun fillWin() {
//        log("FILL_WIN")
//
//        val combinationMatrixEnum = listOf<CombinationMatrixEnum>(
//            *CombinationWinNonIntersectingColorful.values(),
//            *CombinationWinIntersectingMonochrome.values(),
//            *CombinationWinIntersectingMonochromeWithWild.values(),
//            *CombinationWinIntersectingColorfulWithWild.values(),
//        ).random()
//        combinationMatrixEnum.logCombinationMatrixEnum()
//        val combinationMatrix = combinationMatrixEnum.matrix
//
//        slotList.onEachIndexed { index, slot ->
//            slot.slotItemWinList = combinationMatrix.generateSlot(index)
//        }
//
//        winFillResult = with(combinationMatrix) {
//            if (winSlotItemList != null) FillResult(winSlotItemList!!, intersectionList!!)
//            else null
//        }
//    }
//
//    private fun fillMini() {
//        log("FILL_MINI")
//        fillRandom(false)
//
//        val randomSlotList = slotList.shuffled().take(2)
//        randomSlotList.onEach { slot -> with(slot) { slotItemWinList = slotItemWinList.toMutableList().apply {
//            setInRandomRow(SlotItemContainer.wild)
//        } } }
//    }
//
//    private fun fillSuper() {
//        log("FILL_SUPER")
//        fillRandom(false)
//
//        val randomSlotList = slotList.shuffled().take(3)
//        randomSlotList.onEach { slot -> with(slot) { slotItemWinList = slotItemWinList.toMutableList().apply {
//            setInRandomRow(SlotItemContainer.wild)
//        } } }
//    }
//
//    private fun MutableList<SlotItem>.setInRandomRow(slotItem: SlotItem) = apply {
//        val randomRow = (0 until SlotController.SLOT_ITEM_VISIBLE_COUNT).random()
//        set(randomRow, slotItem)
//    }
//
//
//
//    fun fill(fillStrategy: FillStrategy) {
//        winFillResult = null
//
//        when (fillStrategy) {
//            FillStrategy.RANDOM -> fillRandom()
//            FillStrategy.WIN    -> fillWin()
//            FillStrategy.MINI   -> fillMini()
//            FillStrategy.SUPER  -> fillSuper()
//        }
//    }
//
//
//
//    private fun CombinationMatrixEnum.logCombinationMatrixEnum() {
//        val combinationEnumName = when (this) {
//            is CombinationRandom                            -> "Комбинация Рандом { Рандом } $name"
//            is CombinationRandomWithWild                    -> "Комбинация Рандом { Рандом | WILD } $name"
//            is CombinationWinNonIntersectingColorful        -> "Комбинация Победы { Не пересекающаяся | Разноцветная } $name"
//            is CombinationWinIntersectingMonochrome         -> "Комбинация Победы { Пересекающаяся | Одноцветная } $name"
//            is CombinationWinIntersectingMonochromeWithWild -> "Комбинация Победы { Пересекающаяся | Одноцветная | WILD } $name"
//            is CombinationWinIntersectingColorfulWithWild   -> "Комбинация Победы { Пересекающаяся | Разноцветная | WILD } $name"
//            else                                            -> "Noname"
//        }
//        log(combinationEnumName)
//    }
//
//}