package com.veldan.pinup.actors.slot.util

import com.veldan.pinup.manager.assets.SpriteManager
import com.veldan.pinup.manager.assets.SpriteManager.SpriteList.SLOT_ITEM

object SlotItemContainer {
    private const val SLOT_ITEM_WILD_ID = 100


    val wild get() = SlotItem(SLOT_ITEM_WILD_ID, 3f, SpriteManager.GameSprite.WILD.data.texture)

    val list get() = listOf<SlotItem>(
        SlotItem(1, 1f  , SLOT_ITEM.dataList[0].texture),
        SlotItem(2, 1.1f, SLOT_ITEM.dataList[1].texture),
        SlotItem(3, 1.2f, SLOT_ITEM.dataList[2].texture),
        SlotItem(4, 1.3f, SLOT_ITEM.dataList[3].texture),
        SlotItem(5, 1.4f, SLOT_ITEM.dataList[4].texture),
        SlotItem(6, 1.5f, SLOT_ITEM.dataList[5].texture),
        SlotItem(7, 1.6f, SLOT_ITEM.dataList[6].texture),
        SlotItem(8, 1.7f, SLOT_ITEM.dataList[7].texture),
    )

}