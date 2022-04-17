package com.veldan.kingsolomonslots.actors.slot.slot

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.veldan.kingsolomonslots.actors.slot.util.SlotItem
import com.veldan.kingsolomonslots.advanced.group.AbstractAdvancedGroup
import com.veldan.kingsolomonslots.advanced.group.util.ChainManager
import com.veldan.kingsolomonslots.utils.disable
import com.veldan.kingsolomonslots.layout.Layout.Slot as LS

class Slot : AbstractAdvancedGroup() {
    override val controller by lazy { SlotController(this) }

    val slotItemImageWinList  = List(SlotController.SLOT_ITEM_VISIBLE_COUNT) { Image() }
    val slotItemImageFakeList = List(SlotController.SLOT_ITEM_VISIBLE_COUNT) { Image() }

    var slotItemWinList = listOf<SlotItem>()
        set(value) {
            field = value
            value.onEachIndexed { index, slotItem -> slotItemImageWinList[index].drawable = TextureRegionDrawable(slotItem.region) }
        }



    init {
        disable()
        setSize(LS.W, LS.H)
        addActorsOnGroup()
    }



    private fun addActorsOnGroup() {
        addSlotItems()
    }

    private fun addSlotItems() {
        controller.slotItemImageList.onEach { slotItemImage ->
            addActorChain(slotItemImage, ChainManager.ChainStyle.START_TOP_END_BOTTOM, 1, 0f, LS.SLOT_ITEM_SPACE_VERTICAL)
        }
    }

}