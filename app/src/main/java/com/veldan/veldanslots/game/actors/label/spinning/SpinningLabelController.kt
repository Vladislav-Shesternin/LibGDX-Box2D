package com.veldan.veldanslots.game.actors.label.spinning

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.GlyphLayout
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.utils.Disposable
import com.veldan.veldanslots.utils.cancelCoroutinesAll
import com.veldan.veldanslots.game.utils.controller.GroupController
import com.veldan.veldanslots.game.utils.toDelay
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.flow.take

class SpinningLabelController(override val group: SpinningLabel) : GroupController, Disposable {

    companion object {
        const val LABEL_SPACE_PERCENT = 10

        // seconds
        const val TIME_ROLL_CURRENT = 5f
        const val TIME_DELAY        = 2f
    }

    private val glyphLayout = GlyphLayout()

    private var coroutineRoll = CoroutineScope(Dispatchers.Default)

    private val labelSpace get() = (getTextWidth() / 100) * LABEL_SPACE_PERCENT



    override fun dispose() {
        cancelCoroutinesAll(coroutineRoll)
    }



    private fun swapLabel() {
        Gdx.app.postRunnable {
            with(group) { labelCurrent = labelNext.apply { labelNext = labelCurrent } }
            setPositionLabelNext()
        }
    }

    fun setPositionLabelNext() {
        Gdx.app.postRunnable {
            group.labelNext.setPosition(getTextWidth() + labelSpace, 0f)
        }
    }

    fun getTextWidth() = glyphLayout.run {
        setText(group.labelStyle.font, group.labelCurrent.text)
        width
    }

    fun spin() {
        with(group) {
            val timeRollNext = timeRoll + ((timeRoll / 100) * LABEL_SPACE_PERCENT)
            val finishFlow   = MutableSharedFlow<Boolean>(replay = 2)

            coroutineRoll.launch {
                while (true) {
                    delay(timeDelay.toDelay)
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
    }

    fun setText(text: CharSequence) {
        Gdx.app.postRunnable {
            coroutineRoll.cancel()
            coroutineRoll = CoroutineScope(Dispatchers.Default)

            with(group) {
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

}