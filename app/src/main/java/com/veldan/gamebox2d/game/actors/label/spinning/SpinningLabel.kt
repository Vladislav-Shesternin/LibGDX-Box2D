package com.veldan.gamebox2d.game.actors.label.spinning

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.GlyphLayout
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.veldan.gamebox2d.game.actors.masks.normal.Mask
import com.veldan.gamebox2d.game.utils.advanced.AdvancedGroup
import com.veldan.gamebox2d.game.utils.toMS
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.flow.take

class SpinningLabel(
    private var text      : CharSequence,
    private val labelStyle: Label.LabelStyle,
    private var timeDelay : Float = TIME_DELAY,
    private var timeRoll  : Float = TIME_ROLL_CURRENT,
    private val alignment : Int   = Align.center
): AdvancedGroup() {

    companion object {
        const val LABEL_SPACE_PERCENT = 10

        // seconds
        const val TIME_ROLL_CURRENT = 5f
        const val TIME_DELAY        = 2f
    }

    private val mask         = Mask()
    private var labelCurrent = Label(text, labelStyle)
    private var labelNext    = Label(text, labelStyle)

    private val glyphLayout = GlyphLayout()

    private val labelSpace get() = (getTextWidth() / 100) * LABEL_SPACE_PERCENT

    private val timeRollNext = timeRoll + ((timeRoll / 100) * LABEL_SPACE_PERCENT)



    override fun sizeChanged() {
        if (width > 0 && height > 0) addActorsOnGroup()
    }



    private fun addActorsOnGroup() {
        addMask()
        addCurrentLabel()
    }

    private fun addMask() {
        addAndFillActor(mask)
    }

    private fun addCurrentLabel() {
        mask.addActor(labelCurrent)

        labelCurrent.setSize(width,height)
        labelCurrent.setPosition(0f, 0f)

        if (getTextWidth() > labelCurrent.width) {
            labelCurrent.setAlignment(Align.left)
            addNextLabel()
        } else labelCurrent.setAlignment(alignment)
    }

    private fun addNextLabel() {
        mask.addAndFillActor(labelNext)

        labelNext.setSize(width, height)
        labelNext.setAlignment(Align.left)

        setPositionLabelNext()
        spin()
    }


    private fun getTextWidth() = glyphLayout.run {
        setText(labelStyle.font, text)
        width
    }


    private fun swapLabel() {
        Gdx.app.postRunnable {
            labelCurrent = labelNext.apply { labelNext = labelCurrent }
            setPositionLabelNext()
        }
    }

    private fun setPositionLabelNext() {
        Gdx.app.postRunnable { labelNext.setPosition(getTextWidth() + labelSpace, 0f) }
    }

    private fun spin() {
        val finishFlow = MutableSharedFlow<Boolean>(replay = 2)

        coroutineMain.launch {
            while (true) {
                delay(timeDelay.toMS)
                Gdx.app.postRunnable {
                    labelCurrent.addAction(
                        Actions.sequence(
                            Actions.moveBy(-getTextWidth(), 0f, timeRoll),
                            Actions.run { finishFlow.tryEmit(true) }
                        ))
                    labelNext.addAction(
                        Actions.sequence(
                            Actions.moveBy(-(getTextWidth() + labelSpace), 0f, timeRollNext),
                            Actions.run { finishFlow.tryEmit(true) }
                        ))
                }

                finishFlow.take(2).collectIndexed { index, _ ->
                    if (index == 1) {
                        finishFlow.resetReplayCache()
                        swapLabel()
                    }
                }
            }
        }
    }

    fun setText(text: CharSequence) {
        Gdx.app.postRunnable {
            this.text = text

            coroutineMain.coroutineContext.cancelChildren()

            listOf(labelCurrent, labelNext).onEach { it.clearActions() }

            labelCurrent.setText(text)
            labelNext.setText(text)

            if (mask.hasChildren()) {
                mask.clearChildren()
                addCurrentLabel()
            }
        }
    }



}