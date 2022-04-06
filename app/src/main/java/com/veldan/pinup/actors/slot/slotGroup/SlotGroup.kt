package com.veldan.pinup.actors.slot.slotGroup

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.veldan.pinup.actors.masks.normal.Mask
import com.veldan.pinup.actors.slot.slot.Slot
import com.veldan.pinup.advanced.group.AbstractAdvancedGroup
import com.veldan.pinup.manager.assets.SpriteManager
import com.veldan.pinup.layout.Layout.SlotGroup as LSG
import com.veldan.pinup.layout.Layout.Slot as LS

class SlotGroup : AbstractAdvancedGroup() {
    override val controller = SlotGroupController(this)

    val slotList   = List(SlotGroupController.SLOT_COUNT) { Slot() }
    // val glowList= List(GLOW_COUNT) { Glow() }
    val mask       = Mask()
    val panelImage = Image(SpriteManager.GameSprite.SLOT_GROUP_PANEL.data.texture)



    init {
        setSize(LSG.W, LSG.H)
        addActorsOnGroup()
    }



    private fun addActorsOnGroup() {
        addPanel()
        addMask()
    }

    private fun addPanel() {
        addAndFillActor(panelImage)
    }

//    private fun addGlows() {
//        addActors(glowList)
//
//        var newX = SG.GLOW_FIRS_X
//        glowList.onEach {
//            it.setPositionFigmaY(newX, SG.GLOW_Y, G.H, SG.H)
//            newX += G.W + SG.GLOW_SPACE_HORIZONTAL
//        }
//    }

    private fun addMask() {
        addActor(mask)
        mask.setBounds(LSG.MASK_X, LSG.MASK_Y, LSG.MASK_W, LSG.MASK_H)

        addSlots()
    }

    private fun addSlots() {
        var newX = LSG.SLOT_FIRST_X
        slotList.onEach { slot ->
            slot.debug()
            mask.addActor(slot)
            slot.setPosition(newX, LS.START_Y)
            newX += LS.W + LSG.SLOT_SPACE_HORIZONTAL
        }
    }
    
}