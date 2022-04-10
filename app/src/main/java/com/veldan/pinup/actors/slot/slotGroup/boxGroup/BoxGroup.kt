package com.veldan.pinup.actors.slot.slotGroup.boxGroup

import com.veldan.pinup.advanced.group.AbstractAdvancedGroup
import com.veldan.pinup.advanced.group.AdvancedGroup
import com.veldan.pinup.layout.Layout.BoxGroup as LB

class BoxGroup: AbstractAdvancedGroup() {
    override val controller = BoxGroupController(this)

    val boxPrizeGroup = AdvancedGroup()



    init {
        setSize(LB.W, LB.H)
        addActorsOnGroup()
    }



    private fun AdvancedGroup.addActorsOnGroup() {
        addBoxPrizeGroup()
        addBoxGroup()
    }

    private fun AdvancedGroup.addBoxPrizeGroup() {
        addActor(boxPrizeGroup)
        boxPrizeGroup.setBounds(LB.BOX_PIZE_GROUP_X, LB.BOX_PIZE_GROUP_Y, LB.BOX_PIZE_GROUP_W, LB.BOX_PIZE_GROUP_H)
    }

    private fun addBoxGroup() {
        controller.layoutBoxGroup()
    }


}