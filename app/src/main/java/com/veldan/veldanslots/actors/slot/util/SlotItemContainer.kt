package com.veldan.veldanslots.actors.slot.util

import com.veldan.veldanslots.manager.assets.SpriteManager

object SlotItemContainer {
    const val SLOT_ITEM_WILD_ID    = 100
    const val SLOT_ITEM_SCATTER_ID = 101

    val wild    get() = SlotItem(SLOT_ITEM_WILD_ID   , 3f, SpriteManager.GameRegion.WILD.region)
    val scatter get() = SlotItem(SLOT_ITEM_SCATTER_ID, 0f, SpriteManager.GameRegion.SCATTER.region)

    val list get() = listOf<SlotItem>(
        SlotItem(1, 1f  , SpriteManager.ListRegion.SLOT_ITEM.regionList[0]),
        SlotItem(2, 1.1f, SpriteManager.ListRegion.SLOT_ITEM.regionList[1]),
        SlotItem(3, 1.2f, SpriteManager.ListRegion.SLOT_ITEM.regionList[2]),
        SlotItem(4, 1.3f, SpriteManager.ListRegion.SLOT_ITEM.regionList[3]),
        SlotItem(5, 1.4f, SpriteManager.ListRegion.SLOT_ITEM.regionList[4]),
        SlotItem(6, 1.5f, SpriteManager.ListRegion.SLOT_ITEM.regionList[5]),
        SlotItem(7, 1.6f, SpriteManager.ListRegion.SLOT_ITEM.regionList[6]),
        SlotItem(8, 1.7f, SpriteManager.ListRegion.SLOT_ITEM.regionList[7]),
    )

}