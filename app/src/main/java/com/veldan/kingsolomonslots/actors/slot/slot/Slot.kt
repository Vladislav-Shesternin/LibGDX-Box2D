//package com.veldan.pinup2.actors.slot.slot
//
//import com.badlogic.gdx.scenes.scene2d.ui.Image
//import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
//import com.veldan.pinup2.actors.slot.util.SlotItem
//import com.veldan.pinup2.advanced.group.AbstractAdvancedGroup
//import com.veldan.pinup2.advanced.group.util.ChainManager
//import com.veldan.pinup2.utils.disable
//import com.veldan.pinup2.layout.Layout.Slot as LS
//
//class Slot : AbstractAdvancedGroup() {
//    override val controller by lazy { SlotController(this) }
//
//    val slotItemImageWinList  = List(SlotController.SLOT_ITEM_VISIBLE_COUNT) { Image() }
//    val slotItemImageFakeList = List(SlotController.SLOT_ITEM_VISIBLE_COUNT) { Image() }
//
//    var slotItemWinList = listOf<SlotItem>()
//        set(value) {
//            field = value
//            value.onEachIndexed { index, slotItem -> slotItemImageWinList[index].drawable = TextureRegionDrawable(slotItem.texture) }
//        }
//
//
//
//    init {
//        disable()
//        setSize(LS.W, LS.H)
//        addActorsOnGroup()
//    }
//
//
//
//    private fun addActorsOnGroup() {
//        addSlotItems()
//    }
//
//    private fun addSlotItems() {
//        controller.slotItemImageList.onEach { slotItemImage ->
//           // slotItemImage.debug()
//            addActorChain(slotItemImage, ChainManager.ChainStyle.START_TOP_END_BOTTOM, 1, 0f, LS.SLOT_ITEM_SPACE_VERTICAL)
//        }
//    }
//
//}