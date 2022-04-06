package com.veldan.pinup.actors.slot.util

import com.veldan.pinup.actors.slot.slot.Slot
import com.veldan.pinup.utils.log
import com.veldan.pinup.utils.probability

class FillManager(val slotList: List<Slot>) {

    private val slotItemContainer = SlotItemContainer()

    var fillResult: FillResult? = null
        private set



    private fun fillRandom(isUseWild: Boolean = true) {
        log("FILL_RANDOM")

        val shuffledSlotItemList = slotItemContainer.list.shuffled().toMutableList()

        if (isUseWild) probability(20) {
            log("FILL_RANDOM set WILD")
            shuffledSlotItemList.add(slotItemContainer.wild)
        }

        // Создаем список списков по 3 рандомных элемента
        val prepackagedSlotItemList = shuffledSlotItemList.chunked(3).shuffled()

        slotList.onEachIndexed { index, slot -> when (index) {
            in 0..1 -> slot.slotItemWinList = prepackagedSlotItemList[index].shuffled()
            2       -> slot.slotItemWinList = if (prepackagedSlotItemList[2].size == 3) prepackagedSlotItemList[2].shuffled() else prepackagedSlotItemList[0].shuffled()
        } }
    }

    private fun fillWin() {
        log("FILL_WIN")
        fillRandom(false)

        val shuffledSlotItemList = slotItemContainer.list.shuffled()
        val combinationMatrix    = CombinationMatrix.values().random()

        val winSlotItemSet = mutableSetOf<SlotItem>()
        val winJointSet    = combinationMatrix.matrix.jointList.toSet()

        slotList.onEachIndexed { slotIndex, slot ->
            slot.slotItemWinList = mutableListOf<SlotItem>().apply {
                combinationMatrix.matrix.slotList[slotIndex].onEach { slotItemIndex ->
                    val slotItem: SlotItem = when (val index = slotItemIndex.toSlotItemIndex) {
                        100              -> {
                            winSlotItemSet.add(slotItemContainer.wild)
                            slotItemContainer.wild
                        }
                        in winIndexArray -> {
                            winSlotItemSet.add(shuffledSlotItemList[index])
                            shuffledSlotItemList[index]
                        }
                        else             -> shuffledSlotItemList[index]
                    }
                    add(slotItem)
                }
            }
        }

        fillResult = FillResult(winSlotItemSet, winJointSet)
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

}