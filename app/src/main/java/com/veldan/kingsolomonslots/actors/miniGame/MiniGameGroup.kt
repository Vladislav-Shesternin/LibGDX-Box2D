package com.veldan.kingsolomonslots.actors.miniGame

import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.veldan.kingsolomonslots.actors.miniGame.boxGroup.BoxGroup
import com.veldan.kingsolomonslots.actors.miniGame.resultGroup.ResultGroup
import com.veldan.kingsolomonslots.advanced.group.AbstractAdvancedGroup
import com.veldan.kingsolomonslots.advanced.group.AdvancedGroup
import com.veldan.kingsolomonslots.utils.disable
import com.veldan.kingsolomonslots.layout.Layout.MiniGameGroup as LMG

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