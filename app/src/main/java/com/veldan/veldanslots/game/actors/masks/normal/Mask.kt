package com.veldan.veldanslots.game.actors.masks.normal

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.veldan.veldanslots.game.advanced.group.AbstractAdvancedGroup

class Mask(
    val mask: TextureRegion? = null,
) : AbstractAdvancedGroup() {
    override val controller = MaskController(this)



    override fun draw(batch: Batch?, parentAlpha: Float) {
       controller.draw(batch, parentAlpha)
    }



    fun drawSuper(batch: Batch?, parentAlpha: Float) {
        super.draw(batch, parentAlpha)
    }

}