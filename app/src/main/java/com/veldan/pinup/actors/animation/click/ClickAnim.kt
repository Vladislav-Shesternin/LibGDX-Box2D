package com.veldan.pinup.actors.animation.click

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.veldan.pinup.advanced.group.AbstractAdvancedGroup
import com.veldan.pinup.advanced.group.AdvancedGroup
import com.veldan.pinup.layout.Layout.ClickAnim as LCA

class ClickAnim: AbstractAdvancedGroup() {
    override val controller = ClickAnimController(this)

    val image = Image()


    init {
        setSize(LCA.W, LCA.H)
        addActorsOnGroup()
    }

    override fun draw(batch: Batch?, parentAlpha: Float) {
        super.draw(batch, parentAlpha)
        controller.drawAnimation(Gdx.graphics.deltaTime)
    }



    private fun AdvancedGroup.addActorsOnGroup() {
        addAndFillActor(image)
    }

}