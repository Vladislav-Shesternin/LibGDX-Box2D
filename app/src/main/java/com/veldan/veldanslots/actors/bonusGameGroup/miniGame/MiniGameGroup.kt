package com.veldan.veldanslots.actors.bonusGameGroup.miniGame

import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.veldan.veldanslots.actors.bonusGameGroup.miniGame.boxGroup.BoxGroup
import com.veldan.veldanslots.actors.bonusGameGroup.miniGame.resultGroup.ResultGroup
import com.veldan.veldanslots.advanced.group.AbstractAdvancedGroup
import com.veldan.veldanslots.advanced.group.AdvancedGroup
import com.veldan.veldanslots.utils.disable
import com.veldan.veldanslots.layout.Layout.MiniGameGroup as LMG

class MiniGameGroup: AbstractAdvancedGroup() {
    override val controller = MiniGameGroupController(this)

    val boxGroup    = BoxGroup()
    val resultGroup by lazy { ResultGroup(controller.bet) }


    init {
        addAction(Actions.alpha(0f))
    }

    override fun sizeChanged() {
        if (width > 0 && height > 0) {
            disable()
            addActorsOnGroup()
        }
    }



    private fun AdvancedGroup.addActorsOnGroup() {
        addBoxGroup()
    }

    private fun AdvancedGroup.addBoxGroup() {
        addActor(boxGroup)
        boxGroup.setPosition(LMG.BOX_GROUP_X, LMG.BOX_GROUP_Y)
        boxGroup.controller.doAfterGetResult = { prize -> controller.doAfterGetBoxPrize(prize) }
    }

    fun removeBoxGroup() {
        boxGroup.remove()
    }

    fun addResultGroup() {
        resultGroup.addAction(Actions.alpha(0f))
        addAndFillActor(resultGroup)
    }


}