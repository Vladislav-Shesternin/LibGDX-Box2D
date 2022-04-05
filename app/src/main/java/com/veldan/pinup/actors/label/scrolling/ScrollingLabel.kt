package com.veldan.pinup.actors.label.scrolling

import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane
import com.badlogic.gdx.utils.Align
import com.veldan.pinup.advanced.AbstractAdvancedGroup

class ScrollingLabel(
    val text         : String,
    val style        : Label.LabelStyle,
    val alignmentText: Int = Align.top,
    val alignmentLine: Int = Align.left,
): AbstractAdvancedGroup() {
    override val controller = ScrollingLabelController(this)

    val label      = Label(text, style).apply {
        wrap = true
        setAlignment(alignmentText, alignmentLine)
    }
    val scrollPane = ScrollPane(label)



    init {
        addActorsOnGroup()
    }



    private fun addActorsOnGroup() {
        addScrollPane()
    }


    private fun addScrollPane() {
        addAndFillActor(scrollPane)
    }


}