package com.veldan.veldanslots.actors.slot.slot

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.veldan.veldanslots.layout.Layout.Slot as LS
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.veldan.veldanslots.manager.assets.SpriteManager
import com.veldan.veldanslots.utils.controller.GroupController
import kotlinx.coroutines.CompletableDeferred

class SlotController(override val group: Slot) : GroupController {
    companion object {
        // seconds
        const val TIME_SPIN = 3f

        const val SLOT_ITEM_INTERMEDIATE_COUNT = 94
        const val SLOT_ITEM_VISIBLE_COUNT      = 3
    }

    val slotItemImageList: List<Image> by lazy { generateSlotItemImageList() }

    var firstIntermediateSlotItem = Image()


    private fun generateSlotItemImageList() = mutableListOf<Image>().apply {
        addAll(generateWinItems())
        addAll(generateIntermediateItems())
        addAll(generateFakeItems())
    }

    private fun generateWinItems() = group.slotItemImageWinList.apply { onEach { it.setUpItem() } }

    private fun generateFakeItems() = group.slotItemImageFakeList.apply { onEach { it.setUpItem() } }

    private fun generateIntermediateItems() = List(SLOT_ITEM_INTERMEDIATE_COUNT) { Image().apply { setUpItem() } }.apply { firstIntermediateSlotItem = first() }

    private fun Image.setUpItem() {
        val tmpSlotItemList = SpriteManager.ListRegion.SLOT_ITEM.regionList + SpriteManager.GameRegion.SCATTER.region + SpriteManager.GameRegion.WILD.region
        drawable = TextureRegionDrawable(tmpSlotItemList.random())
        setSize(LS.SLOT_ITEM_W, LS.SLOT_ITEM_H)
    }



    fun reset() {
        Gdx.app.postRunnable { with(group) {
            clearActions()
            slotItemImageWinList.onEachIndexed { index, image -> slotItemImageFakeList[index].drawable = image.drawable }
            setPosition(x, LS.START_Y)
            firstIntermediateSlotItem.y += 100f
        } }
    }

    suspend fun spin() = CompletableDeferred<Boolean>().also { continuation ->
        Gdx.app.postRunnable { with(group) {
            addAction(Actions.sequence(
                Actions.moveTo(x, LS.END_Y, TIME_SPIN, Interpolation.pow4),
                Actions.run {
                    firstIntermediateSlotItem.addAction(Actions.sequence(
                        Actions.moveBy(0f, -100f, 0.5f),
                        Actions.run {
                            reset()
                            continuation.complete(true)
                        }
                    ))
                }
            ))
        } }
    }.await()

}