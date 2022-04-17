package com.veldan.kingsolomonslots.actors.slot.slotGroup

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.veldan.kingsolomonslots.actors.masks.normal.Mask
import com.veldan.kingsolomonslots.actors.slot.slot.Slot
import com.veldan.kingsolomonslots.advanced.group.AbstractAdvancedGroup
import com.veldan.kingsolomonslots.manager.assets.SpriteManager
import com.veldan.kingsolomonslots.layout.Layout.Slot as LS
import com.veldan.kingsolomonslots.layout.Layout.SlotGroup as LSG

class SlotGroup : AbstractAdvancedGroup() {
    override val controller = SlotGroupController(this)

    val slotList   = List(SlotGroupController.SLOT_COUNT) { Slot() }
   // val glowList   = List(SlotGroupController.GLOW_COUNT) { Glow() }
    val mask       = Mask(SpriteManager.GameRegion.SLOT_GROUP_MASK.region)
    val panelImage = Image(SpriteManager.GameRegion.SLOT_GROUP_IMAGE.region)



    init {
        setSize(LSG.W, LSG.H)
        addActorsOnGroup()
    }



    private fun addActorsOnGroup() {
        addPanel()
        addGlows()
        addMask()
    }

    private fun addPanel() {
        addAndFillActor(panelImage)
    }

    private fun addGlows() {
//        var newX = LSG.GLOW_FIRST_X
//        glowList.onEach { glow ->
//            addActor(glow)
//            glow.setPosition(newX, LSG.GLOW_Y)
//            newX += LG.W + LSG.GLOW_SPACE_HORIZONTAL
//        }
    }

    private fun addMask() {
        addActor(mask)
        mask.setBounds(LSG.MASK_X, LSG.MASK_Y, LSG.MASK_W, LSG.MASK_H)

        addSlots()
    }

    private fun addSlots() {
        var newX = LSG.SLOT_FIRST_X
        slotList.onEach { slot ->
            mask.addActor(slot)
            slot.setPosition(newX, LS.START_Y)
            newX += LS.W + LSG.SLOT_SPACE_HORIZONTAL
        }
    }

}