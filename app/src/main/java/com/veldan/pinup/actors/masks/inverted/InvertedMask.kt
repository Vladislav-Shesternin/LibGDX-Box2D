package com.veldan.pinup.actors.masks.inverted

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.veldan.pinup.advanced.AbstractAdvancedGroup

class InvertedMask(
    val texture: Texture,
) : AbstractAdvancedGroup() {
    override val controller = InvertedMaskController(this)



    override fun draw(batch: Batch?, parentAlpha: Float) {
       controller.draw(batch, parentAlpha)
    }



    fun drawSuper(batch: Batch?, parentAlpha: Float) {
        super.draw(batch, parentAlpha)
    }

}