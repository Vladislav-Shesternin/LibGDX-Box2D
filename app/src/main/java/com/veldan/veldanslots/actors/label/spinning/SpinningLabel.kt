package com.veldan.veldanslots.actors.label.spinning

import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.veldan.veldanslots.actors.label.spinning.SpinningLabelController.Companion.TIME_DELAY
import com.veldan.veldanslots.actors.label.spinning.SpinningLabelController.Companion.TIME_ROLL_CURRENT
import com.veldan.veldanslots.actors.masks.normal.Mask
import com.veldan.veldanslots.advanced.group.AbstractAdvancedGroup

class SpinningLabel(
    val text      : CharSequence,
    val labelStyle: Label.LabelStyle,
    var timeDelay : Float = TIME_DELAY,
    var timeRoll  : Float = TIME_ROLL_CURRENT,
    val alignment : Int   = Align.center
): AbstractAdvancedGroup() {
    override val controller = SpinningLabelController(this)

    val mask         = Mask()
    var labelCurrent = Label(text, labelStyle)
    var labelNext    = Label(text, labelStyle)



    override fun sizeChanged() {
        if (width > 0 && height > 0) addActorsOnGroup()
    }


    private fun addActorsOnGroup() {
        addMask()
    }

    private fun addMask() {
        addAndFillActor(mask)
        addCurrentLabel()
    }

    private fun addNextLabel() {
        mask.addAndFillActor(labelNext)
        labelNext.setSize(width,height)
        val align = if (controller.getTextWidth() > labelCurrent.width) Align.left else alignment
        labelNext.setAlignment(align)
        with(controller) {
            setPositionLabelNext()
            spin()
        }
    }



    fun addCurrentLabel() {
        mask.addActor(labelCurrent)
        labelCurrent.setSize(width,height)
        labelCurrent.setPosition(0f, 0f)

        if (controller.getTextWidth() > labelCurrent.width) {
            labelCurrent.setAlignment(Align.left)
            addNextLabel()
        } else labelCurrent.setAlignment(alignment)
    }



}