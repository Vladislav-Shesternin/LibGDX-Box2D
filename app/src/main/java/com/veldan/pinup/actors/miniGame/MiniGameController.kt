package com.veldan.pinup.actors.miniGame

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.utils.Disposable
import com.veldan.pinup.utils.*
import com.veldan.pinup.utils.controller.GroupController
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlin.math.roundToLong

class MiniGameController(override val group: MiniGame) : GroupController, Disposable {

    companion object {
        const val COUNT_DOWN = 3
        const val COUNT_DOWN_TIME = 10

        // seconds
        const val TIME_COUNT_DOWN_SHOW      = 0.5f
        const val TIME_COUNT_DOWN_HIDE      = 0.5f
        const val TIME_COUNT_DOWN_WAIT      = 0.2f
        const val TIME_COUNT_DOWN_TIME_SHOW = 0.4f
        const val TIME_COUNT_DOWN_TIME_HIDE = 0.4f
        const val TIME_COUNT_DOWN_TIME_WAIT = 0.2f
        const val TIME_SHOW_ACTOR           = 1f
        const val TIME_HIDE_ACTOR           = 1f
        const val TIME_WAIT_RESULT          = 3f
    }

    private val coroutineStart         = CoroutineScope(Dispatchers.Default)
    private val coroutineCountDown     = CoroutineScope(Dispatchers.Default)
    private val coroutineCountDownTime = CoroutineScope(Dispatchers.Default)
    private val coroutineBonus         = CoroutineScope(Dispatchers.Default)

    var bet = 0L
        private set
    var result = 0L
        private set

    val bonusFlow = MutableStateFlow(0f)

    var doAfterFinish: suspend (result: Long) -> Unit = {}



    override fun dispose() {
        cancelCoroutinesAll(coroutineStart, coroutineCountDown, coroutineCountDownTime, coroutineBonus)
    }



    private suspend fun showCountDownTime() = CompletableDeferred<Boolean>().also { continuation ->
        val countDownTimeFlow = MutableStateFlow(COUNT_DOWN_TIME)

        coroutineCountDownTime.launch { countDownTimeFlow.collect { countDownTime -> group.timeTextLabel.apply { Gdx.app.postRunnable {
            setText(countDownTime.toString())
            addAction(Actions.sequence(
                Actions.fadeIn(TIME_COUNT_DOWN_TIME_SHOW),
                Actions.delay(TIME_COUNT_DOWN_TIME_WAIT),
                Actions.fadeOut(TIME_COUNT_DOWN_TIME_HIDE),
                Actions.run {
                    if (countDownTime == 0) {
                        setText("-")
                        addAction(Actions.fadeIn(TIME_SHOW_ACTOR))
                        continuation.complete(true)
                    }
                    else countDownTimeFlow.value -= 1
                }
            ))
        } } } }
    }.await()

    private suspend fun showCountDown() = CompletableDeferred<Boolean>().also { continuation ->
        val countDownFlow = MutableStateFlow(COUNT_DOWN)

        coroutineCountDown.launch { countDownFlow.collect { countDown -> group.countDownLabel.apply { Gdx.app.postRunnable {
            setText(countDown)
            addAction(Actions.sequence(
                Actions.fadeIn(TIME_COUNT_DOWN_SHOW),
                Actions.delay(TIME_COUNT_DOWN_WAIT),
                Actions.fadeOut(TIME_COUNT_DOWN_HIDE),
                Actions.run {
                    if (countDown == 1) continuation.complete(true)
                    else countDownFlow.value -= 1
                }
            ))
        } } } }
    }.await()

    private suspend fun showTopPanelAndGameGroupActors() {
        with(group) {
            Gdx.app.postRunnable { topPanelGroup.addAction(Actions.fadeIn(TIME_SHOW_ACTOR)) }

            val completableBagShow = CompletableDeferred<Boolean>()

            Gdx.app.postRunnable { bagImage.apply {
                enable()
                addAction(Actions.sequence(
                    Actions.parallel(
                        Actions.scaleTo(1f, 1f, TIME_SHOW_ACTOR),
                        Actions.fadeIn(TIME_SHOW_ACTOR),
                    ),
                    Actions.run { completableBagShow.complete(true) }
                ))
            } }

            completableBagShow.await()

            Gdx.app.postRunnable { clickAnim.apply {
                addAction(Actions.alpha(1f))
                controller.play()
            } }
        }
    }

    private suspend fun hideGameGroupActors() = CompletableDeferred<Boolean>().also { continuation ->
        with(group) {
            gameGroup.apply {
                disable()
                addAction(Actions.sequence(
                    Actions.fadeOut(TIME_HIDE_ACTOR),
                    Actions.run { continuation.complete(true) }
                ))
            }
        }
    }.await()

    private suspend fun showResultGroupActors() = CompletableDeferred<Boolean>().also { continuation ->
        with(group) {
            resultBonusLabel.setText(String.format("%.1f", bonusFlow.value))
            result = (bet * bonusFlow.value).roundToLong()
            resultLabel.setText(result.transformToBalanceFormat())

            resultGroup.children.onEach {  actor ->
                actor.addAction(Actions.sequence(
                    Actions.fadeIn(TIME_SHOW_ACTOR),
                    Actions.run { continuation.complete(true) }
                ))
            }
        }
    }.await()


    fun collectBonus() {
        coroutineBonus.launch { bonusFlow.collect { bonus ->
            group.bonusTextLabel.setText(String.format("%.1f", bonus))
        } }
    }


    fun start(bet: Long) {
        this.bet = bet
        group.betTextLabel.setText(bet.transformToBalanceFormat())

        coroutineStart.launch {
            showCountDown()
            showTopPanelAndGameGroupActors()
            showCountDownTime()
            hideGameGroupActors()
            showResultGroupActors()
            delay(TIME_WAIT_RESULT.toDelay)
            doAfterFinish(result)
        }
    }

}