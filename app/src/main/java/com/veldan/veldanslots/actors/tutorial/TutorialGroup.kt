package com.veldan.veldanslots.actors.tutorial

import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.utils.Align
import com.veldan.veldanslots.actors.label.LabelStyle
import com.veldan.veldanslots.actors.label.scrolling.ScrollingLabel
import com.veldan.veldanslots.advanced.group.AbstractAdvancedGroup
import com.veldan.veldanslots.advanced.group.AdvancedGroup
import com.veldan.veldanslots.manager.assets.SpriteManager
import com.veldan.veldanslots.utils.disable
import com.veldan.veldanslots.utils.listeners.toClickable
import com.veldan.veldanslots.layout.Layout.TutorialGroup as LT

class TutorialGroup : AbstractAdvancedGroup() {
    override val controller = TutorialGroupController(this)

    val dialogImage = Image()
    val frameImage  = Image()
    val textLabel   = ScrollingLabel("", LabelStyle.gold_40, Align.center, Align.center)

    val skipImage   = Image(SpriteManager.TutorialRegion.SKIP.region)



    init {
        disable()
        addActorsOnGroup()
        toClickable().setOnClickListener { controller.clickHandler() }
        children.onEach { it.addAction(Actions.alpha(0f)) }
    }



    private fun AdvancedGroup.addActorsOnGroup() {
        addDialogImage()
        addFrameImage()
        addTextLabel()
        addSkipImage()
    }

    private fun AdvancedGroup.addDialogImage() {
        addActor(dialogImage)
    }

    private fun AdvancedGroup.addFrameImage() {
        addActor(frameImage)
    }

    private fun AdvancedGroup.addTextLabel() {
        addActor(textLabel)
    }

    private fun AdvancedGroup.addSkipImage() {
        addActor(skipImage)
        skipImage.apply {
            setBounds(LT.SKIP_X, LT.SKIP_Y, LT.SKIP_W, LT.SKIP_H)
            toClickable().setOnClickListener { this@TutorialGroup.controller.isFinishFlow.tryEmit(true) }
        }
    }

}