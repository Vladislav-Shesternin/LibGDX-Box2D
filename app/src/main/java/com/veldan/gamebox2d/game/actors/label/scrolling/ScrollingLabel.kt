package com.veldan.gamebox2d.game.actors.label.scrolling

import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane
import com.badlogic.gdx.utils.Align
import com.veldan.gamebox2d.game.utils.advanced.AdvancedGroup

class ScrollingLabel(
   text                     : String,
   style                    : Label.LabelStyle,
   private val alignmentText: Int = Align.top,
   private val alignmentLine: Int = Align.left,
): AdvancedGroup() {

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