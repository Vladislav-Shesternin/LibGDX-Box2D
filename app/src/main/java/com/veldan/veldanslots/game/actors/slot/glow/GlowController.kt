package com.veldan.veldanslots.game.actors.slot.glow

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.veldan.veldanslots.game.actors.slot.slot.SlotController
import com.veldan.veldanslots.game.manager.assets.util.SoundUtil
import com.veldan.veldanslots.game.manager.assets.util.playAdvanced
import com.veldan.veldanslots.game.utils.controller.GroupController
import kotlinx.coroutines.CompletableDeferred

class GlowController(override val group: Glow) : GroupController {

    companion object {
        const val GLOW_ITEM_COUNT = SlotController.SLOT_ITEM_VISIBLE_COUNT

        // seconds
        const val TIME_GLOW_IN      = 0.3f
        const val TIME_GLOW_OUT     = 0.3f
        const val TIME_ALL_GLOW_IN  = 0.1f
        const val TIME_ALL_GLOW_OUT = 0.1f
    }



    suspend fun glowIn(glowItemIndex: Int) = CompletableDeferred<Boolean>().also { continuation ->
        Gdx.app.postRunnable {
            SoundUtil.WIN_SLOT_ITEM.playAdvanced()
            group.glowImageList[glowItemIndex].addAction(Actions.sequence(
                    Actions.fadeIn(TIME_GLOW_IN),
                    Actions.run { continuation.complete(true) },
            ))
        }
    }.await()

    suspend fun glowOut(glowItemIndex: Int) = CompletableDeferred<Boolean>().also { continuation ->
        Gdx.app.postRunnable {
            group.glowImageList[glowItemIndex].addAction(Actions.sequence(
                Actions.fadeOut(TIME_GLOW_OUT),
                Actions.run { continuation.complete(true) },
            ))
        }
    }.await()

    fun glowInAll() {
        Gdx.app.postRunnable { group.glowImageList.onEach { it.addAction(Actions.fadeIn(TIME_ALL_GLOW_IN)) } }
    }

    fun glowOutAll() {
        Gdx.app.postRunnable { group.glowImageList.onEach { it.addAction(Actions.fadeOut(TIME_ALL_GLOW_OUT)) } }
    }

}