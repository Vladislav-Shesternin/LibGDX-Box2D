package com.veldan.pinup.actors.superGame

import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.veldan.pinup.R
import com.veldan.pinup.actors.animation.click.ClickAnim
import com.veldan.pinup.actors.label.LabelStyleUtil
import com.veldan.pinup.actors.label.spinning.SpinningLabel
import com.veldan.pinup.actors.slot.slotGroup.boxGroup.BoxGroup
import com.veldan.pinup.advanced.group.AbstractAdvancedGroup
import com.veldan.pinup.advanced.group.AdvancedGroup
import com.veldan.pinup.layout.Layout
import com.veldan.pinup.manager.assets.SpriteManager
import com.veldan.pinup.utils.disable
import com.veldan.pinup.utils.language.Language
import com.veldan.pinup.utils.listeners.toClickable
import com.veldan.pinup.layout.Layout.SuperGame as LSG

class SuperGame : AbstractAdvancedGroup() {
    override val controller = SuperGameController(this)

    val resultGroup         = AdvancedGroup()
    val gameGroup           = AdvancedGroup()
    val bonusGroup          = AdvancedGroup()

    // BonusGroup
    val bonusLabel          by lazy { SpinningLabel(Language.getStringResource(R.string.bonus), LabelStyleUtil.white60) }
    val bonusTextLabel      = SpinningLabel("", LabelStyleUtil.amaranteWhite100)

    // GameGroup
    val boxGroup            = BoxGroup()

    // ResultGroup
    val betPanelGroup       = AdvancedGroup()
    val betPanelImage       = Image(SpriteManager.GameSprite.BET_PANEL.data.texture)
    val betTextLabel        = SpinningLabel("", LabelStyleUtil.white60)
    val betLabel            by lazy { SpinningLabel(Language.getStringResource(R.string.bet), LabelStyleUtil.white50) }
    val multiplicationLabel = Label("*", LabelStyleUtil.amaranteWhite100)
    val equalsLabel         = Label("=", LabelStyleUtil.amaranteWhite100)
    val resultBonusLabel    = SpinningLabel("", LabelStyleUtil.amaranteWhite100)
    val resultLabel         = SpinningLabel("", LabelStyleUtil.amaranteWhite100)


    init {
        addActorsOnGroup()
    }



    private fun AdvancedGroup.addActorsOnGroup() {
        addResultGroup()
        addBonusGroup()
        addGameGroup()
    }

    private fun AdvancedGroup.addGameGroup() {
        addAndFillActor(gameGroup)
        addActorsOnGameGroup()
    }

    private fun AdvancedGroup.addResultGroup() {
        addAndFillActor(resultGroup)
        addActorsOnResultGroup()
        resultGroup.children.onEach { it.addAction(Actions.alpha(0f)) }
    }

    private fun AdvancedGroup.addBonusGroup() {
        addAndFillActor(bonusGroup)
        addActorsOnBonusGroup()
    }

    // ------------------------------------------------------------------------
    // BonusGroup
    // ------------------------------------------------------------------------

    private fun AdvancedGroup.addBonusLabel() {
        addActor(bonusLabel)
        bonusLabel.setBounds(LSG.BonusGroup.BONUS_LABEL_X, LSG.BonusGroup.BONUS_LABEL_Y, LSG.BonusGroup.BONUS_LABEL_W, LSG.BonusGroup.BONUS_LABEL_H)
    }

    private fun AdvancedGroup.addBonusTextLabel() {
        addActor(bonusTextLabel)
        bonusTextLabel.setBounds(LSG.BonusGroup.BONUS_TEXT_X, LSG.BonusGroup.BONUS_TEXT_Y, LSG.BonusGroup.BONUS_TEXT_W, LSG.BonusGroup.BONUS_TEXT_H)
        controller.collectBonus()
    }

    private fun addActorsOnBonusGroup() {
        with(bonusGroup) {
            addBonusLabel()
            addBonusTextLabel()
        }
    }

    // ------------------------------------------------------------------------
    // GameGroup
    // ------------------------------------------------------------------------
    private fun AdvancedGroup.addBoxGroup() {
        addActor(boxGroup)
        boxGroup.apply {
            setPosition(LSG.GameGroup.BOX_GROUP_X, LSG.GameGroup.BOX_GROUP_Y)
            controller.boxClickBlock = { boxPrize -> this@SuperGame.controller.boxHandler(boxPrize) }
        }

    }

    private fun addActorsOnGameGroup() {
        with(gameGroup) {
            addBoxGroup()
        }
    }

    // ------------------------------------------------------------------------
    // ResultGroup
    // ------------------------------------------------------------------------
    
    private fun AdvancedGroup.addBetPanelGroup() {
        addActor(betPanelGroup)
        betPanelGroup.apply {
            setBounds(LSG.ResultGroup.BET_PANEL_X, LSG.ResultGroup.BET_PANEL_Y, LSG.ResultGroup.BET_PANEL_W, LSG.ResultGroup.BET_PANEL_H)
            
            addAndFillActor(betPanelImage)
            addActors(betLabel, betTextLabel)
            
            betLabel.setBounds(LSG.ResultGroup.BET_LABEL_X, LSG.ResultGroup.BET_LABEL_Y, LSG.ResultGroup.BET_LABEL_W, LSG.ResultGroup.BET_LABEL_H)
            betTextLabel.setBounds(LSG.ResultGroup.BET_TEXT_X, LSG.ResultGroup.BET_TEXT_Y, LSG.ResultGroup.BET_TEXT_W, LSG.ResultGroup.BET_TEXT_H)
        }
    }

    private fun AdvancedGroup.addMultiplicationLabel() {
        addActor(multiplicationLabel)
        multiplicationLabel.setAlignment(Align.center)
        multiplicationLabel.setBounds(LSG.ResultGroup.MULTIPLICATION_X, LSG.ResultGroup.MULTIPLICATION_Y, LSG.ResultGroup.MULTIPLICATION_W, LSG.ResultGroup.MULTIPLICATION_H)
    }

    private fun AdvancedGroup.addResultBonusLabel() {
        addActor(resultBonusLabel)
        resultBonusLabel.setBounds(LSG.ResultGroup.BONUS_X, LSG.ResultGroup.BONUS_Y, LSG.ResultGroup.BONUS_W, LSG.ResultGroup.BONUS_H)
    }

    private fun AdvancedGroup.addEqualsLabel() {
        addActor(equalsLabel)
        equalsLabel.setAlignment(Align.center)
        equalsLabel.setBounds(LSG.ResultGroup.EQUALS_X, LSG.ResultGroup.EQUALS_Y, LSG.ResultGroup.EQUALS_W, LSG.ResultGroup.EQUALS_H)
    }

    private fun AdvancedGroup.addResultLabel() {
        addActor(resultLabel)
        resultLabel.setBounds(LSG.ResultGroup.RESULT_X, LSG.ResultGroup.RESULT_Y, LSG.ResultGroup.RESULT_W, LSG.ResultGroup.RESULT_H)
    }

    private fun addActorsOnResultGroup() {
        with(resultGroup) {
            addBetPanelGroup()
            addMultiplicationLabel()
            addResultBonusLabel()
            addEqualsLabel()
            addResultLabel()
        }
    }

}