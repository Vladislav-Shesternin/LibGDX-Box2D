//package com.veldan.fantasticslots.actors.slot.glow
//
//import com.badlogic.gdx.Gdx
//import com.badlogic.gdx.scenes.scene2d.actions.Actions
//import com.badlogic.gdx.scenes.scene2d.ui.Image
//import com.veldan.pinup.actors.slot.slot.Slot
//import com.veldan.fantasticslots.advanced.AbstractAdvancedGroup
//import com.veldan.fantasticslots.assets.SpriteManager
//import com.veldan.fantasticslots.utils.TIME_EXTRA
//import com.veldan.fantasticslots.utils.layout.setBoundsFigmaY
//import com.veldan.fantasticslots.utils.toDelay
//import kotlinx.coroutines.delay
//import com.veldan.fantasticslots.utils.Glow as G
//
//class Glow : AbstractAdvancedGroup() {
//    override val controller = GlowController(this)
//
//    companion object {
//        const val GLOW_ITEM_COUNT = Slot.VISIBLE_SLOT_ITEM_COUNT
//
//        // seconds
//        const val TIME_GLOW_IN  = 0.1f
//        const val TIME_GLOW_OUT = 0.1f
//    }
//
//    private val glowItemList = List(GLOW_ITEM_COUNT) { Image(SpriteManager.GameSprite.GLOW.data.texture) }
//
//
//
//    init {
//        setSize(G.W, G.H)
//        addActorsOnGroup()
//    }
//
//
//
//    private fun addActorsOnGroup() {
//        addGlowItems()
//    }
//
//
//
//    private fun addGlowItems() {
//        glowItemList.onEachIndexed { index, image ->
//            val newY = (G.GLOW_ITEM_H + G.GLOW_ITEM_SPACE_VERTICAL) * index
//            image.apply {
//                setBoundsFigmaY(0f, newY, G.GLOW_ITEM_W, G.GLOW_ITEM_H, G.H)
//                addAction(Actions.alpha(0f))
//            }
//            addActor(image)
//        }
//    }
//
//
//
//    suspend fun glowIn(glowItemIndex: Int) {
//        Gdx.app.postRunnable { glowItemList[glowItemIndex].addAction(Actions.fadeIn(TIME_GLOW_IN)) }
//        delay(TIME_GLOW_IN.toDelay + TIME_EXTRA)
//    }
//
//    suspend fun glowOut(glowItemIndex: Int) {
//        Gdx.app.postRunnable { glowItemList[glowItemIndex].addAction(Actions.fadeOut(TIME_GLOW_OUT)) }
//        delay(TIME_GLOW_OUT.toDelay + TIME_EXTRA)
//    }
//
//    suspend fun glowOutAll() {
//        glowItemList.onEach { Gdx.app.postRunnable {  it.addAction(Actions.fadeOut(TIME_GLOW_OUT)) } }
//        delay(TIME_GLOW_OUT.toDelay + TIME_EXTRA)
//    }
//
//}