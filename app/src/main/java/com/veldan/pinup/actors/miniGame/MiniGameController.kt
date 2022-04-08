package com.veldan.pinup.actors.miniGame

import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.utils.Disposable
import com.veldan.pinup.utils.cancelCoroutinesAll
import com.veldan.pinup.utils.controller.GroupController
import com.veldan.pinup.utils.enable
import com.veldan.pinup.utils.log
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow

class MiniGameController(override val group: MiniGame) : GroupController, Disposable {

    companion object {
        const val COUNT_DOWN = 3

        // seconds
        const val TIME_COUNT_DOWN_SHOW = 1f
        const val TIME_COUNT_DOWN_HIDE = 0.5f
        const val TIME_COUNT_DOWN_WAIT = 0.2f
        const val TIME_SHOW_ACTOR      = 1f
    }

    private val coroutineCountDown = CoroutineScope(Dispatchers.Default)

    val bonusFlow = MutableSharedFlow<Long>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)



    override fun dispose() {
        cancelCoroutinesAll(coroutineCountDown)
    }



    fun start() {
        coroutineCountDown.launch {
            val completableCountDownShow = CompletableDeferred<Boolean>()
            val countDownFlow            = MutableStateFlow(COUNT_DOWN)

            launch { countDownFlow.collect { countDown ->
                group.countDownLabel.apply {
                    setText(countDown)
                    addAction(Actions.sequence(
                        Actions.fadeIn(TIME_COUNT_DOWN_SHOW),
                        Actions.delay(TIME_COUNT_DOWN_WAIT),
                        Actions.fadeOut(TIME_COUNT_DOWN_HIDE),
                        Actions.run {
                            if (countDown == 1) {
                                cancel()
                                completableCountDownShow.complete(true)
                            } else countDownFlow.value -= 1
                        }
                    ))
                }
            } }

            completableCountDownShow.await()

            with(group) {
                topPanelGroup.addAction(Actions.fadeIn(TIME_SHOW_ACTOR))

                bagImage.apply {
                    enable()
                    addAction(Actions.parallel(
                        Actions.scaleTo(1f, 1f, TIME_SHOW_ACTOR),
                        Actions.fadeIn(TIME_SHOW_ACTOR),
                    ))
                }
            }


        }
    }

}