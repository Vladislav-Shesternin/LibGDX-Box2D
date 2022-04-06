package com.veldan.pinup.actors.masks.normal

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.veldan.pinup.advanced.group.AbstractAdvancedGroup

class Mask(
    val mask: Texture? = null,
) : AbstractAdvancedGroup() {
    override val controller = MaskController(this)



    override fun draw(batch: Batch?, parentAlpha: Float) {
       controller.draw(batch, parentAlpha)
    }



    fun drawSuper(batch: Batch?, parentAlpha: Float) {
        super.draw(batch, parentAlpha)
    }

}