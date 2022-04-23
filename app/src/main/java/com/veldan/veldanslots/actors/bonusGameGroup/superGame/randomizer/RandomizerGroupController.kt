package com.veldan.veldanslots.actors.bonusGameGroup.superGame.randomizer

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.utils.Align
import com.veldan.veldanslots.actors.slot.slotGroup.SlotGroupController
import com.veldan.veldanslots.manager.assets.util.SoundUtil
import com.veldan.veldanslots.manager.assets.util.playAdvanced
import com.veldan.veldanslots.utils.controller.GroupController
import kotlinx.coroutines.CompletableDeferred
import kotlin.random.Random

class RandomizerGroupController(override val group: RandomizerGroup) : GroupController {
    companion object {
        const val TIME_FADE_IN  = 0.1f
        const val TIME_DELAY    = 0.05f
        const val TIME_FADE_OUT = 0.1f
    }

    private fun generateNumber() = Random.nextInt(1, SlotGroupController.SLOT_COUNT.inc())



    suspend fun start(): Int {
        var completableAnim: CompletableDeferred<Boolean>
        var resultNumber = 0

        Gdx.app.postRunnable { group.panelImage.addAction(Actions.rotateBy(-360f, 5f)) }

        repeat(20) {
            completableAnim = CompletableDeferred()
            resultNumber    = generateNumber()

            Gdx.app.postRunnable {
                group.textLabel.apply {
                    setOrigin(Align.center)
                    setText(resultNumber.toString())
                    addAction(Actions.sequence(
                            Actions.fadeIn(TIME_FADE_IN),
                            Actions.delay(TIME_DELAY),
                            Actions.fadeOut(TIME_FADE_OUT),
                            Actions.run { completableAnim.complete(true) }
                    ))
                }
            }
            completableAnim.await()
        }

        Gdx.app.postRunnable {
            SoundUtil.WIN_BOX.playAdvanced()
            group.panelImage.apply {
                clearActions()
                rotation = 0f
            }
            group.textLabel.addAction(Actions.alpha(1f))
        }

        return resultNumber
    }

}