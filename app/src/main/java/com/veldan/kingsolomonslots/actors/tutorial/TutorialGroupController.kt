package com.veldan.kingsolomonslots.actors.tutorial

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.badlogic.gdx.utils.Disposable
import com.veldan.kingsolomonslots.R
import com.veldan.kingsolomonslots.actors.tutorial.util.TutorialItem
import com.veldan.kingsolomonslots.manager.assets.SpriteManager
import com.veldan.kingsolomonslots.utils.cancelCoroutinesAll
import com.veldan.kingsolomonslots.utils.controller.GroupController
import com.veldan.kingsolomonslots.utils.disable
import com.veldan.kingsolomonslots.utils.enable
import com.veldan.kingsolomonslots.utils.language.Language
import com.veldan.kingsolomonslots.utils.listeners.toClickable
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import com.veldan.kingsolomonslots.layout.Layout.TutorialGroup as LTG

class TutorialGroupController(override val group: TutorialGroup) : GroupController, Disposable {

    companion object {
        const val TIME_FADE_IN  = 0.3f
        const val TIME_FADE_OUT = 0.3f
    }

    private val tutorialList = listOf<TutorialItem>(
        TutorialItem(
            regionFrame  = SpriteManager.TutorialRegion.TF_BALANCE.region,
            regionDialog = SpriteManager.TutorialRegion.TD_BALANCE.region,
            layout       = LTG.Balance,
            text         = Language.getStringResource(R.string.t_balance),
        ),
       TutorialItem(
           regionFrame  = SpriteManager.TutorialRegion.TF_BET.region,
           regionDialog = SpriteManager.TutorialRegion.TD_BALANCE.region,
           layout       = LTG.Bet,
           text         = Language.getStringResource(R.string.t_bet),
       ),
       TutorialItem(
           regionFrame  = SpriteManager.TutorialRegion.TF_PLUS_MINUS.region,
           regionDialog = SpriteManager.TutorialRegion.TD_BALANCE.region,
           layout       = LTG.PlusMinus,
           text         = Language.getStringResource(R.string.t_plus_minus),
       ),
       TutorialItem(
           regionFrame  = SpriteManager.TutorialRegion.TF_SPIN.region,
           regionDialog = SpriteManager.TutorialRegion.TD_BALANCE.region,
           layout       = LTG.Spin,
           text         = Language.getStringResource(R.string.t_spin),
       ),
       TutorialItem(
           regionFrame  = SpriteManager.TutorialRegion.TF_SLOT_GROUP.region,
           regionDialog = SpriteManager.TutorialRegion.TD_SLOT_GROUP.region,
           layout       = LTG.SlotGroup,
           text         = Language.getStringResource(R.string.t_slot_group),
       ),
    )

    private val coroutineMain = CoroutineScope(Dispatchers.Default)

    private var tutorialItemIndex = 0

    val isFinishFlow = MutableStateFlow(false)



    override fun dispose() {
        cancelCoroutinesAll(coroutineMain)
    }



    private suspend fun showTutorialItem(item: TutorialItem) {
        Gdx.app.postRunnable {
            with(group) {
                dialogImage.drawable = TextureRegionDrawable(item.regionDialog)
                frameImage.drawable = TextureRegionDrawable(item.regionFrame)
                textLabel.label.setText(item.text)

                with(item.layout) {
                    dialogImage.setBounds(DIALOG_X, DIALOG_Y, DIALOG_W, DIALOG_H)
                    frameImage.setBounds(FRAME_X, FRAME_Y, FRAME_W, FRAME_H)
                    textLabel.setBounds(TEXT_X, TEXT_Y, TEXT_W, TEXT_H)
                }
            }
        }

        val completableAnimList = List(group.children.size) { CompletableDeferred<Boolean>() }
        Gdx.app.postRunnable {
            group.children.onEachIndexed { index, actor ->
                actor.addAction(Actions.sequence(
                    Actions.fadeIn(TIME_FADE_IN),
                    Actions.run { completableAnimList[index].complete(true) }
                ))
            }
        }
        completableAnimList.onEach { it.await() }
    }

    private suspend fun hideTutorialItem() {
        val completableAnimList = List(group.children.size) { CompletableDeferred<Boolean>() }
        Gdx.app.postRunnable {
            group.children.onEachIndexed { index, actor ->
                actor.addAction(Actions.sequence(
                    Actions.fadeOut(TIME_FADE_OUT),
                    Actions.run { completableAnimList[index].complete(true) }
                ))
            }
        }
        completableAnimList.onEach { it.await() }
    }



    fun clickHandler() {
        group.disable()

        if (tutorialItemIndex == tutorialList.size) isFinishFlow.tryEmit(true)
        else if (isFinishFlow.value.not()) {
            coroutineMain.launch {
                hideTutorialItem()
                showTutorialItem(tutorialList[tutorialItemIndex])
                tutorialItemIndex++
                group.enable()
            }
        }
    }

    suspend fun start() = CompletableDeferred<Boolean>().also { continuation ->
        showTutorialItem(tutorialList.first())
        group.enable()
        tutorialItemIndex++
        CoroutineScope(Dispatchers.Default).launch {
            isFinishFlow.collect { isFinish ->
                if (isFinish) {
                    continuation.complete(true)
                    cancel()
                }
            }
        }
    }.await()

}