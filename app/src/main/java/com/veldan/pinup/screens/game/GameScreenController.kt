package com.veldan.pinup.screens.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.utils.Disposable
import com.veldan.pinup.manager.DataStoreManager
import com.veldan.pinup.utils.cancelCoroutinesAll
import com.veldan.pinup.utils.controller.ScreenController
import com.veldan.pinup.utils.transformToBalanceFormat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class GameScreenController(override val screen: GameScreen): ScreenController, Disposable {

    companion object {
        const val BET_STEP = 50L
        const val BET_MIN  = 50L
        const val BET_MAX  = 1000L
    }

    private val coroutineBalance = CoroutineScope(Dispatchers.Default)
    private val coroutineBet     = CoroutineScope(Dispatchers.Default)
    private val coroutineSpin    = CoroutineScope(Dispatchers.Default)

    private val betFlow = MutableSharedFlow<Long>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)


    override fun dispose() {
        cancelCoroutinesAll(coroutineBalance, coroutineBet, coroutineSpin)
    }



    fun updateBalance() {
        coroutineBalance.launch { DataStoreManager.collectBalance { balance -> Gdx.app.postRunnable {
            screen.balanceTextLabel.setText(balance.transformToBalanceFormat())
        } } }
    }

    fun updateBet() {
        coroutineBet.launch { with(betFlow) {
            emit(BET_MIN)
            collect { bet -> Gdx.app.postRunnable { screen.betTextLabel.setText(bet.transformToBalanceFormat()) } }
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
        coroutineSpin.launch {
            screen.slotGroup.controller.spin()
        }
    }

}