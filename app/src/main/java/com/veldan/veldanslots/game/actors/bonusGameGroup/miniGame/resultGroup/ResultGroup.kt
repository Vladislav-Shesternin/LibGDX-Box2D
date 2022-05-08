package com.veldan.veldanslots.game.actors.bonusGameGroup.miniGame.resultGroup

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.veldan.veldanslots.game.actors.label.LabelStyle
import com.veldan.veldanslots.game.actors.label.spinning.SpinningLabel
import com.veldan.veldanslots.game.actors.bonusGameGroup.miniGame.boxGroup.util.BoxPrize
import com.veldan.veldanslots.game.advanced.group.AbstractAdvancedGroup
import com.veldan.veldanslots.game.advanced.group.AdvancedGroup
import com.veldan.veldanslots.game.layout.Layout.ResultGroup as LRG
import com.veldan.veldanslots.game.manager.assets.SpriteManager
import com.veldan.veldanslots.utils.transformToBalanceFormat

class ResultGroup(bet: Long): AbstractAdvancedGroup() {
    override val controller = ResultGroupController(this)

    // equals
    val equalsImage       = Image(SpriteManager.GameRegion.EQUALS.region)
    // win
    val winImage          = Image(SpriteManager.GameRegion.WIN_20.region)
    // balance
    val balancePanelGroup = AdvancedGroup()
    val balancePanelImage = Image(SpriteManager.GameRegion.BALANCE_PANEL.region)
    val balanceTextLabel  = SpinningLabel((bet * BoxPrize.WIN.prize).transformToBalanceFormat(), LabelStyle.reggaeOne_50)
    // bet
    val betPanelGroup     = AdvancedGroup()
    val betPanelImage     = Image(SpriteManager.GameRegion.BET_PANEL.region)
    val betTextLabel      = SpinningLabel(bet.transformToBalanceFormat(), LabelStyle.reggaeOne_40)



    init {
        addActorsOnGroup()
    }



    private fun AdvancedGroup.addActorsOnGroup() {
        addBetPanel()
        addBalancePanel()
        addEqualsImage()
        addWinImage()
    }

    private fun AdvancedGroup.addBetPanel() {
        addActor(betPanelGroup)
        betPanelGroup.apply {
            setBounds(LRG.BET_PANEL_X, LRG.BET_PANEL_Y, LRG.BET_PANEL_W, LRG.BET_PANEL_H)

            addAndFillActor(betPanelImage)
            addActors(betTextLabel)

            betTextLabel.apply {
                setBounds(LRG.BET_TEXT_X, LRG.BET_TEXT_Y, LRG.BET_TEXT_W, LRG.BET_TEXT_H)
            }
        }
    }

    private fun AdvancedGroup.addBalancePanel() {
        addActor(balancePanelGroup)
        balancePanelGroup.apply {
            setBounds(LRG.BALANCE_PANEL_X, LRG.BALANCE_PANEL_Y, LRG.BALANCE_PANEL_W, LRG.BALANCE_PANEL_H)

            addAndFillActor(balancePanelImage)
            addActor(balanceTextLabel)

            balanceTextLabel.apply {
                setBounds(LRG.BALANCE_TEXT_X, LRG.BALANCE_TEXT_Y, LRG.BALANCE_TEXT_W, LRG.BALANCE_TEXT_H)
            }
        }
    }

    private fun AdvancedGroup.addEqualsImage() {
        addActor(equalsImage)
        equalsImage.setBounds(LRG.EQUALS_X, LRG.EQUALS_Y, LRG.EQUALS_W, LRG.EQUALS_H)
    }

    private fun AdvancedGroup.addWinImage() {
        addActor(winImage)
        winImage.setBounds(LRG.WIN_X, LRG.WIN_Y, LRG.WIN_W, LRG.WIN_H)
    }

}