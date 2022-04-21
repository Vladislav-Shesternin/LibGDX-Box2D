package com.veldan.kingsolomonslots.actors.bonusGameGroup.superGame.finish

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.veldan.kingsolomonslots.R
import com.veldan.kingsolomonslots.actors.label.LabelStyle
import com.veldan.kingsolomonslots.actors.label.spinning.SpinningLabel
import com.veldan.kingsolomonslots.advanced.group.AbstractAdvancedGroup
import com.veldan.kingsolomonslots.advanced.group.AdvancedGroup
import com.veldan.kingsolomonslots.layout.Layout
import com.veldan.kingsolomonslots.manager.assets.SpriteManager
import com.veldan.kingsolomonslots.utils.language.Language
import com.veldan.kingsolomonslots.utils.transformToBalanceFormat
import com.veldan.kingsolomonslots.layout.Layout.FinishSuperGameGroup as LFSG

class FinishSuperGameGroup(balance: Long) : AbstractAdvancedGroup() {
    override val controller = FinishSuperGameGroupController(this)

    // title
    val titleLabel        = SpinningLabel("", LabelStyle.white_60)

    // balance
    val balancePanelGroup = AdvancedGroup()
    val balancePanelImage = Image(SpriteManager.GameRegion.BIG_BALANCE_PANEL.region)
    val balanceTextLabel  = SpinningLabel(balance.transformToBalanceFormat(), LabelStyle.reggaeOne_120)



    init {
        addActorsOnGroup()
    }



    private fun AdvancedGroup.addActorsOnGroup() {
        addTitleLabel()
        addBalancePanel()
    }

    private fun AdvancedGroup.addTitleLabel() {
        addActor(titleLabel)
        titleLabel.controller.setText(Language.getStringResource(R.string.super_game_title_2))
        titleLabel.setBounds(LFSG.TITLE_X, LFSG.TITLE_Y, LFSG.TITLE_W, LFSG.TITLE_H)
    }

    private fun AdvancedGroup.addBalancePanel() {
        addActor(balancePanelGroup)
        balancePanelGroup.apply {
            setBounds(LFSG.BALANCE_PANEL_X, LFSG.BALANCE_PANEL_Y, LFSG.BALANCE_PANEL_W, LFSG.BALANCE_PANEL_H)

            addAndFillActor(balancePanelImage)
            addActor(balanceTextLabel)

            balanceTextLabel.apply {
                setBounds(LFSG.BALANCE_TEXT_X, LFSG.BALANCE_TEXT_Y, LFSG.BALANCE_TEXT_W, LFSG.BALANCE_TEXT_H)
            }
        }
    }
}