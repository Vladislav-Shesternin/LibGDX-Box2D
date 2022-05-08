package com.veldan.veldanslots.game.screens.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.utils.Disposable
import com.veldan.veldanslots.game.actors.bonusGameGroup.miniGame.boxGroup.util.BoxPrize
import com.veldan.veldanslots.game.actors.slot.util.Bonus
import com.veldan.veldanslots.game.actors.slot.util.SpinResult
import com.veldan.veldanslots.game.manager.GameDataStoreManager
import com.veldan.veldanslots.game.manager.assets.util.MusicUtil
import com.veldan.veldanslots.game.utils.*
import com.veldan.veldanslots.game.utils.controller.ScreenController
import com.veldan.veldanslots.utils.cancelCoroutinesAll
import com.veldan.veldanslots.utils.transformToBalanceFormat
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first

class GameScreenController(override val screen: GameScreen): ScreenController, Disposable {

    companion object {
        const val BET_STEP              = 50L
        const val BET_MIN               = 50L
        const val BET_MAX               = 1000L
        const val SUPER_GAME_SPIN_COUNT = 10

        // seconds
        const val TIME_WAIT_AFTER_AUTOSPIN          = 1f
        const val TIME_WAIT_AFTER_SUPER_GAME        = 1f
        const val TIME_WAIT_AFTER_FINISH_SUPER_GAME = 1f
        const val TIME_SHOW_SCREEN                  = 1f
        const val TIME_HIDE_SCREEN                  = 1f
    }

    private val coroutineBalance        = CoroutineScope(Dispatchers.Default)
    private val coroutineBet            = CoroutineScope(Dispatchers.Default)
    private val coroutineSpin           = CoroutineScope(Dispatchers.Default)
    private val coroutineAutoSpin       = CoroutineScope(Dispatchers.Default)
    private val coroutineMiniGame       = CoroutineScope(Dispatchers.Default)
    private val coroutineSuperGame      = CoroutineScope(Dispatchers.Default)

    private val betFlow                = MutableSharedFlow<Long>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    private val autoSpinStateFlow      = MutableStateFlow(AutospinState.DEFAULT)
    private val superGameSpinCountFlow = MutableStateFlow(SUPER_GAME_SPIN_COUNT)

    private val onceStartAutoSpin = Once()

    private var isSuperGame = false
    private var superGameSlotNumber = 0

    var balanceSuperGame = 0L



    override fun dispose() {
        cancelCoroutinesAll(
            coroutineBalance , coroutineBet     , coroutineSpin     ,
            coroutineAutoSpin, coroutineMiniGame, coroutineSuperGame,
        )
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
                                if (isSuperGame.not()) {
                                    betPlusButton.controller.enable()
                                    betMinusButton.controller.enable()
                                }
                                cancel()
                            }
                        }
                    }

                    AutospinState.DEFAULT -> screen.autoSpinButton.controller.disable()
                }
            }
        }
    }

    private suspend fun checkBalance() = CompletableDeferred<Boolean>().also { continuation ->
        if (isSuperGame) {
            if (superGameSpinCountFlow.value.dec() != -1) {
                superGameSpinCountFlow.value -= 1
                continuation.complete(true)
                return@also
            }
        }

        GameDataStoreManager.Balance.update { balance ->
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
        GameDataStoreManager.Balance.update { it + sumPrice }

        if (isSuperGame) {
            balanceSuperGame += sumPrice
            if(superGameSpinCountFlow.value == 0) doAfterFinishSuperGame()
        }
    }

    private suspend fun spinAndSetResult() {
        screen.slotGroup.controller.spin(isSuperGame, superGameSlotNumber).apply {
            when (bonus) {
                Bonus.MINI_GAME  -> startMiniGame()
                Bonus.SUPER_GAME -> startSuperGame()
                else             -> {}
            }
            calculateAndSetResult()
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

    private suspend fun startSuperGame() = CompletableDeferred<Boolean>().also { continuation ->
        hideGameGroup()
        screen.addSuperGameGroup()
        showSuperGameGroup()

        with(screen.superGameGroup.controller) {
            start().also { number -> initSuperGameNumber(number) }
            isSuperGame = true
            delay(TIME_WAIT_AFTER_SUPER_GAME.toDelay)
            hideSuperGameGroup()
            showGameGroup()
            continuation.complete(true)
        }
    }.await()

    private suspend fun hideGameGroup() {
        val completableAnim = CompletableDeferred<Boolean>()

        screen.gameGroup.apply {
            Gdx.app.postRunnable {
                disable()
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
            enable()
            with(MusicUtil) { currentMusic = MAIN }
        }
    }

    private suspend fun showSuperGameGroup() {
        val completableAnim = CompletableDeferred<Boolean>()

        screen.superGameGroup.apply {
            Gdx.app.postRunnable {
                addAction(Actions.sequence(
                    Actions.fadeIn(TIME_SHOW_SCREEN),
                    Actions.run { completableAnim.complete(true) }
                ))
            }
            completableAnim.await()
        }
    }

    private suspend fun showFinishSuperGameGroup() {
        val completableAnim = CompletableDeferred<Boolean>()

        screen.finishSuperGameGroup.apply {
            Gdx.app.postRunnable {
                addAction(Actions.sequence(
                    Actions.fadeIn(TIME_SHOW_SCREEN),
                    Actions.run { completableAnim.complete(true) }
                ))
            }
            completableAnim.await()
        }
    }

    private suspend fun hideFinishSuperGameGroup() {
        val completableAnim = CompletableDeferred<Boolean>()

        screen.finishSuperGameGroup.apply {
            Gdx.app.postRunnable {
                addAction(Actions.sequence(
                    Actions.fadeOut(TIME_HIDE_SCREEN),
                    Actions.run { completableAnim.complete(true) }
                ))
            }
            completableAnim.await()
        }
        screen.removeFinishSuperGameGroup()
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

    private suspend fun hideSuperGameGroup() {
        val completableAnim = CompletableDeferred<Boolean>()

        screen.superGameGroup.apply {
            Gdx.app.postRunnable {
                addAction(Actions.sequence(
                    Actions.fadeOut(TIME_HIDE_SCREEN),
                    Actions.run { completableAnim.complete(true) }
                ))
            }
            completableAnim.await()
        }
        screen.removeSuperGameGroup()
    }

    private suspend fun doAfterFinishMiniGame(boxPrize: BoxPrize) {
        hideMiniGameGroup()
        showGameGroup()

        val prize = boxPrize.prize * betFlow.first()
        GameDataStoreManager.Balance.update { it + prize }
    }

    private fun initSuperGameNumber(number: Int) {
        with(screen) {
            addSuperGameElementGroup()
            superGameElementGroup.apply {
                superGameSlotNumber = number
                slotNumberLabel.setText(number)
                coroutineSuperGame.launch {
                    superGameSpinCountFlow.emit(SUPER_GAME_SPIN_COUNT)
                    superGameSpinCountFlow.collect { count -> spinCountLabel.setText(count) } }
            }
        }
    }

    private suspend fun doAfterFinishSuperGame() {
        isSuperGame = false
        autoSpinStateFlow.value = AutospinState.DEFAULT
        hideGameGroup()
        screen.addFinishSuperGameGroup(balanceSuperGame)
        showFinishSuperGameGroup()
        screen.removeSuperGameElementGroup()
        delay(TIME_WAIT_AFTER_FINISH_SUPER_GAME.toDelay)
        hideFinishSuperGameGroup()
        showGameGroup()
    }



    fun updateBalance() {
        coroutineBalance.launch { GameDataStoreManager.Balance.collect { balance -> Gdx.app.postRunnable {
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
                if (isSuperGame.not()) {
                    betPlusButton.controller.enable()
                    betMinusButton.controller.enable()
                }
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