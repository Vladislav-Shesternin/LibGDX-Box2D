package com.veldan.kingsolomonslots.actors.tutorial

import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.utils.Align
import com.veldan.kingsolomonslots.actors.label.LabelStyle
import com.veldan.kingsolomonslots.actors.label.scrolling.ScrollingLabel
import com.veldan.kingsolomonslots.advanced.group.AbstractAdvancedGroup
import com.veldan.kingsolomonslots.advanced.group.AdvancedGroup
import com.veldan.kingsolomonslots.utils.listeners.toClickable

class TutorialGroup : AbstractAdvancedGroup() {
    override val controller = TutorialGroupController(this)

    val dialogImage = Image()
    val frameImage  = Image()
    val textLabel   = ScrollingLabel("", LabelStyle.gold_40, Align.center, Align.center)

    init {
        addActorsOnGroup()
        toClickable().setOnClickListener { controller.clickHandler() }
        children.onEach { it.addAction(Actions.alpha(0f)) }
    }

    private fun AdvancedGroup.addActorsOnGroup() {
        addDialogImage()
        addFrameImage()
        addTextLabel()
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

}