package com.veldan.pinup.actors.slot.slot

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.veldan.pinup.manager.assets.SpriteManager
import com.veldan.pinup.utils.controller.GroupController
import kotlinx.coroutines.CompletableDeferred
import com.veldan.pinup.layout.Layout.Slot as LS

class SlotController(override val group: Slot) : GroupController {
    companion object {
        // seconds
        const val TIME_SPIN = 3f

        const val SLOT_ITEM_INTERMEDIATE_COUNT = 94
        const val SLOT_ITEM_VISIBLE_COUNT      = 3
    }

    val slotItemImageList: List<Image> by lazy { generateSlotItemImageList() }



    private fun generateSlotItemImageList() = mutableListOf<Image>().apply {
        addAll(generateWinItems())
        addAll(generateIntermediateItems())
        addAll(generateFakeItems())
    }

    private fun generateWinItems() = group.slotItemImageWinList.apply { onEach { it.setUpItem() } }

    private fun generateFakeItems() = group.slotItemImageFakeList.apply { onEach { it.setUpItem() } }

    private fun generateIntermediateItems() = List(SLOT_ITEM_INTERMEDIATE_COUNT) { Image().apply { setUpItem() } }

    private fun Image.setUpItem() {
        val tmpSlotItemList = SpriteManager.SpriteList.SLOT_ITEM.dataList + SpriteManager.GameSprite.WILD.data
        drawable = TextureRegionDrawable(tmpSlotItemList.random().texture)
        setSize(LS.SLOT_ITEM_W, LS.SLOT_ITEM_H)
    }



    fun reset() {
        Gdx.app.postRunnable { with(group) {
            clearActions()
            slotItemImageWinList.onEachIndexed { index, image -> slotItemImageFakeList[index].drawable = image.drawable }
            setPosition(x, LS.START_Y)
        } }
    }

    suspend fun spin() = CompletableDeferred<Boolean>().also { continuation ->
        Gdx.app.postRunnable { with(group) {
            addAction(Actions.sequence(
                Actions.moveTo(x, LS.END_Y, TIME_SPIN, Interpolation.pow4),
                Actions.run {
                    reset()
                    continuation.complete(true)
                }
            ))
        } }
    }.await()

}