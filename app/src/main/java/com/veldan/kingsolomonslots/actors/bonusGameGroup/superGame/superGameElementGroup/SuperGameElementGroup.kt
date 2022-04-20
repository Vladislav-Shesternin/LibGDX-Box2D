package com.veldan.kingsolomonslots.actors.bonusGameGroup.superGame.superGameElementGroup

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.veldan.kingsolomonslots.actors.label.LabelStyle
import com.veldan.kingsolomonslots.advanced.group.AbstractAdvancedGroup
import com.veldan.kingsolomonslots.advanced.group.AdvancedGroup
import com.veldan.kingsolomonslots.manager.assets.SpriteManager
import com.veldan.kingsolomonslots.layout.Layout.SuperGameElementGroup as LSGE

class SuperGameElementGroup: AbstractAdvancedGroup() {
    override val controller = SuperGameElementGroupController(this)

    val slotNumberPanel = Image(SpriteManager.GameRegion.SMALL_CIRCLE_PANEL.region)
    val slotNumberLabel = Label("", LabelStyle.reggaeOne_50)
    val spinCountPanel  = Image(SpriteManager.GameRegion.SMALL_CIRCLE_PANEL.region)
    val spinCountLabel  = Label("", LabelStyle.reggaeOne_50)



    init {
        setSize(LSGE.W, LSGE.H)
        addActorsOnGroup()
    }



    private fun AdvancedGroup.addActorsOnGroup() {
        addSlotNumberPanel()
        addSpinCountPanel()
    }

    private fun AdvancedGroup.addSlotNumberPanel() {
        addActors(slotNumberPanel, slotNumberLabel)
        slotNumberPanel.setBounds(LSGE.SLOT_X, LSGE.SLOT_Y, LSGE.ELEMENT_W, LSGE.ELEMENT_H)
        slotNumberLabel.apply {
            setAlignment(Align.center)
            setBounds(LSGE.SLOT_X, LSGE.SLOT_Y, LSGE.ELEMENT_W, LSGE.ELEMENT_H)
        }
    }

    private fun AdvancedGroup.addSpinCountPanel() {
        addActors(spinCountPanel, spinCountLabel)
        spinCountPanel.setBounds(LSGE.SPIN_X, LSGE.SPIN_Y, LSGE.ELEMENT_W, LSGE.ELEMENT_H)
        spinCountLabel.apply {
            setAlignment(Align.center)
            setBounds(LSGE.SPIN_X, LSGE.SPIN_Y, LSGE.ELEMENT_W, LSGE.ELEMENT_H)
        }
    }

}