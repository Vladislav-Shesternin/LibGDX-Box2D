package com.veldan.pinup.actors.superGame

import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.utils.Disposable
import com.veldan.pinup.actors.slot.slotGroup.boxGroup.BoxGroupController
import com.veldan.pinup.utils.*
import com.veldan.pinup.utils.controller.GroupController
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlin.math.roundToLong

class SuperGameController(override val group: SuperGame) : GroupController, Disposable {

    companion object {
        // seconds
        const val TIME_SHOW_ACTOR           = 1f
        const val TIME_HIDE_ACTOR           = 1f
        const val TIME_WAIT_RESULT          = 3f
    }

    private val coroutineStart         = CoroutineScope(Dispatchers.Default)
    private val coroutineBonus         = CoroutineScope(Dispatchers.Default)

    var bet = 0L
        private set
    var result = 0L
        private set

    val bonusFlow = MutableStateFlow(0L)

    var doAfterFinish: suspend (result: Long) -> Unit = {}



    override fun dispose() {
        cancelCoroutinesAll(coroutineStart, coroutineBonus)
    }

    private suspend fun hideGameGroupActors() = CompletableDeferred<Boolean>().also { continuation ->
        with(group) { gameGroup.apply {
                addAction(Actions.sequence(
                    Actions.fadeOut(TIME_HIDE_ACTOR),
                    Actions.run { continuation.complete(true) }
                ))
        } }
    }.await()

    private suspend fun showResultGroupActors() = CompletableDeferred<Boolean>().also { continuation ->
        with(group) {
            resultBonusLabel.setText(bonusFlow.value.toString())
            result = (bet * bonusFlow.value)
            resultLabel.setText(result.transformToBalanceFormat())

            resultGroup.children.onEach {  actor ->
                actor.addAction(Actions.sequence(
                    Actions.fadeIn(TIME_SHOW_ACTOR),
                    Actions.run { continuation.complete(true) }
                ))
            }
        }
    }.await()



    fun boxHandler(boxPrize: BoxGroupController.BoxPrize) {
        when(boxPrize) {
            is BoxGroupController.BoxPrize.WIN  -> bonusFlow.value += boxPrize.factor
            is BoxGroupController.BoxPrize.Fail -> {
                coroutineStart.launch {
                    group.gameGroup.disable()
                    delay(TIME_WAIT_RESULT.toDelay)
                    hideGameGroupActors()
                    showResultGroupActors()
                    delay(TIME_WAIT_RESULT.toDelay)
                    doAfterFinish(result)
                }
            }
        }
    }

    fun collectBonus() {
        coroutineBonus.launch { bonusFlow.collect { bonus ->
            group.bonusTextLabel.setText(bonus.toString())
        } }
    }

    fun start(bet: Long) {
        this.bet = bet
        group.betTextLabel.setText(bet.transformToBalanceFormat())
    }

}