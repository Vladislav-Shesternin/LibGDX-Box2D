package com.veldan.kingsolomonslots.actors.bonusGameGroup.miniGame

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.veldan.kingsolomonslots.actors.bonusGameGroup.miniGame.boxGroup.util.BoxPrize
import com.veldan.kingsolomonslots.manager.assets.util.MusicUtil
import com.veldan.kingsolomonslots.utils.controller.GroupController
import com.veldan.kingsolomonslots.utils.enable
import com.veldan.kingsolomonslots.utils.toDelay
import kotlinx.coroutines.*

class MiniGameGroupController(override val group: MiniGameGroup) : GroupController {

    companion object {
        const val TIME_SHOW_GROUP             = 2f
        const val TIME_HIDE_GROUP             = 2f
        const val TIME_WAIT_AFTER_SHOW_RESULT = 2f
    }

    private val coroutineMain = CoroutineScope(Dispatchers.Default)

    var doAfterFinish: (BoxPrize) -> Unit = {}

    var bet: Long = 0L
        private set


    private suspend fun showGroup() {
        val completableAnim = CompletableDeferred<Boolean>()
        Gdx.app.postRunnable {
            group.addAction(Actions.sequence(
                Actions.fadeIn(TIME_SHOW_GROUP),
                Actions.run { completableAnim.complete(true) }
            ))
        }
        completableAnim.await()
        group.enable()
    }

    private fun doAfterGetFail() {
        doAfterFinish(BoxPrize.FAIL)
    }

    private fun doAfterGetWin() {
        coroutineMain.launch {
            hideBoxGroup()
            showResultGroup()
        }
    }

    private suspend fun hideBoxGroup() {
        val completableAnim = CompletableDeferred<Boolean>()
        Gdx.app.postRunnable {
            group.boxGroup.addAction(Actions.sequence(
                Actions.fadeOut(TIME_HIDE_GROUP),
                Actions.run { completableAnim.complete(true) }
            ))
        }
        completableAnim.await()
        group.removeBoxGroup()
    }

    private suspend fun showResultGroup() {
        val completableAnim = CompletableDeferred<Boolean>()
        Gdx.app.postRunnable {
            group.addResultGroup()
            group.resultGroup.addAction(Actions.sequence(
                Actions.fadeIn(TIME_SHOW_GROUP),
                Actions.run { completableAnim.complete(true) }
            ))
        }
        completableAnim.await()

        delay(TIME_WAIT_AFTER_SHOW_RESULT.toDelay)

        doAfterFinish(BoxPrize.WIN)
    }



    fun start(bet: Long) {
        with(MusicUtil) { currentMusic = MINI_GAME }

        this.bet = bet

        coroutineMain.launch {
            showGroup()
        }
    }

    fun doAfterGetBoxPrize(boxPrize: BoxPrize) {
        when(boxPrize) {
            BoxPrize.FAIL -> doAfterGetFail()
            BoxPrize.WIN  -> doAfterGetWin()
        }
    }



}