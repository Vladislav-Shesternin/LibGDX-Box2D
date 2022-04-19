package com.veldan.kingsolomonslots.screens.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.utils.Disposable
import com.veldan.kingsolomonslots.actors.miniGame.boxGroup.util.BoxPrize
import com.veldan.kingsolomonslots.actors.slot.util.Bonus
import com.veldan.kingsolomonslots.actors.slot.util.SpinResult
import com.veldan.kingsolomonslots.manager.DataStoreManager
import com.veldan.kingsolomonslots.utils.*
import com.veldan.kingsolomonslots.utils.controller.ScreenController
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlin.coroutines.coroutineContext

class GameScreenController(override val screen: GameScreen): ScreenController, Disposable {

    companion object {
        const val BET_STEP = 50L
        const val BET_MIN  = 50L
        const val BET_MAX  = 1000L

        // seconds
        const val TIME_WAIT_AFTER_AUTOSPIN = 1f
        const val TIME_SHOW_SCREEN         = 1f
        const val TIME_HIDE_SCREEN         = 1f
    }

    private val coroutineBalance        = CoroutineScope(Dispatchers.Default)
    private val coroutineBet            = CoroutineScope(Dispatchers.Default)
    private val coroutineSpin           = CoroutineScope(Dispatchers.Default)
    private val coroutineAutoSpin       = CoroutineScope(Dispatchers.Default)
    private val coroutineMiniGame       = CoroutineScope(Dispatchers.Default)
    private val coroutineSuperGame      = CoroutineScope(Dispatchers.Default)

    private val betFlow           = MutableSharedFlow<Long>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    private val autoSpinStateFlow = MutableStateFlow(AutospinState.DEFAULT)

    private val onceStartAutoSpin = Once()



    override fun dispose() {
        cancelCoroutinesAll(coroutineBalance, coroutineBet, coroutineSpin, coroutineAutoSpin, coroutineMiniGame, coroutineSuperGame)
    }



    private suspend fun checkBalance() = CompletableDeferred<Boolean>().also { continuation ->
        DataStoreManager.Balance.update { balance ->
            if ((balance - betFlow.first()) >= 0) {
                // Достаточно средств для запуска
                continuation.complete(true)
                balance - betFlow.first()
            } else {
                // Недостаточно средств для запуска
                continuation.complete(false)
                balance
            }
        }
    }.await()

    private suspend fun SpinResult.calculateAndSetResult() {
        val slotItemPriceFactor: Float = winSlotItemSet?.map { it.priceFactor }?.sum() ?: 0f
        val sumPrice = (betFlow.first() * slotItemPriceFactor).toLong()
        DataStoreManager.Balance.update { it + sumPrice }
    }

    private suspend fun spinAndSetResult() {
        screen.slotGroup.controller.spin().apply {
            when (bonus) {
                Bonus.MINI_GAME  -> startMiniGame()
               // Bonus.SUPER_GAME -> startSuperGame()
                else             -> {}
            }
            calculateAndSetResult()
        }
    }

    private fun startAutospin() {
        coroutineAutoSpin.launch {
            autoSpinStateFlow.collect { state ->
                when (state) {
                    AutospinState.GO      -> {
                        with(screen) {
                            autoSpinButton.controller.press()
                            spinButton.controller.disable()
                            betPlusButton.controller.disable()
                            betMinusButton.controller.disable()

                            CoroutineScope(Dispatchers.Default).launch {
                                while (autoSpinStateFlow.value == AutospinState.GO) {
                                    if (checkBalance()) {
                                        spinAndSetResult()
                                        delay(TIME_WAIT_AFTER_AUTOSPIN.toDelay)
                                    }
                                    else autoSpinStateFlow.value = AutospinState.DEFAULT
                                }

                                autoSpinButton.controller.enable()
                                spinButton.controller.enable()
                                betPlusButton.controller.enable()
                                betMinusButton.controller.enable()

                                cancel()
                            }
                        }
                    }

                    AutospinState.DEFAULT -> screen.autoSpinButton.controller.disable()
                }
            }
        }
    }

    private suspend fun startMiniGame() = CompletableDeferred<Boolean>().also { continuation ->
        hideGameGroup()
        screen.addMiniGameGroup()

        with(screen.miniGameGroup.controller) {
            start(betFlow.first())

            doAfterFinish = { prize ->
                coroutineMiniGame.launch {
                    doAfterFinishMiniGame(prize)
                    continuation.complete(true)
                }
            }
        }
    }.await()

    private suspend fun hideGameGroup() {
        val completableAnim = CompletableDeferred<Boolean>()

        screen.gameGroup.apply {
            Gdx.app.postRunnable {
                children.onEach { it.disable() }
                addAction(Actions.sequence(
                    Actions.fadeOut(TIME_HIDE_SCREEN),
                    Actions.run { completableAnim.complete(true) }
                ))
            }
            completableAnim.await()
        }
    }

    private suspend fun showGameGroup() {
        val completableAnim = CompletableDeferred<Boolean>()

        screen.gameGroup.apply {
            Gdx.app.postRunnable {
                addAction(Actions.sequence(
                    Actions.fadeIn(TIME_SHOW_SCREEN),
                    Actions.run { completableAnim.complete(true) }
                ))
            }
            completableAnim.await()
            children.onEach { it.enable() }
        }
    }

    private suspend fun hideMiniGameGroup() {
        val completableAnim = CompletableDeferred<Boolean>()

        screen.miniGameGroup.apply {
            Gdx.app.postRunnable {
                addAction(Actions.sequence(
                    Actions.fadeOut(TIME_HIDE_SCREEN),
                    Actions.run { completableAnim.complete(true) }
                ))
            }
            completableAnim.await()
        }
        screen.removeMiniGameGroup()
    }

    private suspend fun doAfterFinishMiniGame(boxPrize: BoxPrize) {
        hideMiniGameGroup()
        showGameGroup()

        val prize = boxPrize.prize * betFlow.first()
        DataStoreManager.Balance.update { it + prize }
    }



    fun updateBalance() {
        coroutineBalance.launch { DataStoreManager.Balance.collect { balance -> Gdx.app.postRunnable {
            screen.balanceTextLabel.controller.setText(balance.transformToBalanceFormat())
        } } }
    }

    fun updateBet() {
        coroutineBet.launch { with(betFlow) {
            emit(BET_MIN)
            collect { bet -> Gdx.app.postRunnable { screen.betTextLabel.controller.setText(bet.transformToBalanceFormat()) } }
        } }
    }

    fun betPlusHandler() {
        coroutineBet.launch { with(betFlow) {
            val emitValue = if ((first() + BET_STEP) < BET_MAX) first() + BET_STEP else BET_MAX
            emit(emitValue)
        } }
    }

    fun betMinusHandler() {
        coroutineBet.launch { with(betFlow) {
            val emitValue = if ((first() - BET_STEP) > BET_MIN) first() - BET_STEP else BET_MIN
            emit(emitValue)
        } }
    }

    fun spinHandler() {
        with(screen) {
            spinButton.controller.pressAndDisable(false)
            autoSpinButton.controller.disable()
            betPlusButton.controller.disable()
            betMinusButton.controller.disable()

            coroutineSpin.launch {
                if (checkBalance()) spinAndSetResult()

                spinButton.controller.enable()
                autoSpinButton.controller.enable()
                betPlusButton.controller.enable()
                betMinusButton.controller.enable()
            }
        }
    }

    fun autoSpinHandler() {
        autoSpinStateFlow.apply {
            value = if (value == AutospinState.DEFAULT) AutospinState.GO else AutospinState.DEFAULT
        }
        onceStartAutoSpin.once { startAutospin() }
    }



    enum class AutospinState {
        DEFAULT, GO,
    }

}