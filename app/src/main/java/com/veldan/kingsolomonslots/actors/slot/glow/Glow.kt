package com.veldan.kingsolomonslots.actors.slot.glow

import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.veldan.kingsolomonslots.advanced.group.AbstractAdvancedGroup
import com.veldan.kingsolomonslots.layout.Layout
import com.veldan.kingsolomonslots.layout.Layout.Glow as LG
import com.veldan.kingsolomonslots.manager.assets.SpriteManager

class Glow : AbstractAdvancedGroup() {
    override val controller = GlowController(this)

    val glowImageList = List(GlowController.GLOW_ITEM_COUNT) { Image(SpriteManager.GameRegion.GLOW.region) }



    init {
        setSize(LG.W, LG.H)
        addActorsOnGroup()
    }



    private fun addActorsOnGroup() {
        addGlowGroup()
    }



    private fun addGlowGroup() {
        var newY = LG.GLOW_ITEM_FIRST_Y
        glowImageList.onEach { image ->
            addActor(image)
            image.apply {
                setBounds(0f, newY, LG.GLOW_ITEM_W, LG.GLOW_ITEM_H)
                addAction(Actions.alpha(0f))
            }
            newY -= (LG.GLOW_ITEM_SPACE_VERTICAL + LG.GLOW_ITEM_H)

        }
    }

}