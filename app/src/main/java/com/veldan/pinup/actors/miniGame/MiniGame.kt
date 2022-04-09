package com.veldan.pinup.actors.miniGame

import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.veldan.pinup.R
import com.veldan.pinup.actors.animation.click.ClickAnim
import com.veldan.pinup.actors.label.LabelStyleUtil
import com.veldan.pinup.actors.label.spinning.SpinningLabel
import com.veldan.pinup.advanced.group.AbstractAdvancedGroup
import com.veldan.pinup.advanced.group.AdvancedGroup
import com.veldan.pinup.manager.assets.SpriteManager
import com.veldan.pinup.utils.disable
import com.veldan.pinup.utils.language.Language
import com.veldan.pinup.utils.listeners.toClickable
import com.veldan.pinup.layout.Layout.MiniGame as LMG

class MiniGame : AbstractAdvancedGroup() {
    override val controller = MiniGameController(this)

    val topPanelGroup       = AdvancedGroup()
    val resultGroup         = AdvancedGroup()
    val gameGroup           = AdvancedGroup()
    // TopPanelGroup
    val timeLabel           by lazy { SpinningLabel(Language.getStringResource(R.string.time), LabelStyleUtil.white60) }
    val bonusLabel          by lazy { SpinningLabel(Language.getStringResource(R.string.bonus), LabelStyleUtil.white60) }
    val timeTextLabel       = SpinningLabel("", LabelStyleUtil.amaranteWhite100)
    val bonusTextLabel      = SpinningLabel("", LabelStyleUtil.amaranteWhite100)
    // GameGroup
    val countDownLabel      = Label("", LabelStyleUtil.amaranteWhite550)
    val bagImage            = Image(SpriteManager.GameSprite.BAG.data.texture)
    val clickAnim           = ClickAnim()
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
        addTopPanelGroup()
        addResultGroup()
        addGameGroup()
    }

    private fun AdvancedGroup.addTopPanelGroup() {
        addActor(topPanelGroup)
        topPanelGroup.apply {
            setBounds(LMG.TopPanel.X, LMG.TopPanel.Y, LMG.TopPanel.W, LMG.TopPanel.H)
            addAction(Actions.alpha(0f))
        }
        addActorsOnTopPanelGroup()
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

    // ------------------------------------------------------------------------
    // TopPanelGroup
    // ------------------------------------------------------------------------

    private fun AdvancedGroup.addTimeLabel() {
        addActor(timeLabel)
        timeLabel.setBounds(LMG.TopPanel.TIME_X, LMG.TopPanel.TIME_Y, LMG.TopPanel.TIME_W, LMG.TopPanel.TIME_H)
    }

    private fun AdvancedGroup.addTimeTextLabel() {
        addActor(timeTextLabel)
        timeTextLabel.setBounds(LMG.TopPanel.TIME_TEXT_X, LMG.TopPanel.TIME_TEXT_Y, LMG.TopPanel.TIME_TEXT_W, LMG.TopPanel.TIME_TEXT_H)
    }

    private fun AdvancedGroup.addBonusLabel() {
        addActor(bonusLabel)
        bonusLabel.setBounds(LMG.TopPanel.BONUS_X, LMG.TopPanel.BONUS_Y, LMG.TopPanel.BONUS_W, LMG.TopPanel.BONUS_H)
    }

    private fun AdvancedGroup.addBonusTextLabel() {
        addActor(bonusTextLabel)
        bonusTextLabel.setBounds(LMG.TopPanel.BONUS_TEXT_X, LMG.TopPanel.BONUS_TEXT_Y, LMG.TopPanel.BONUS_TEXT_W, LMG.TopPanel.BONUS_TEXT_H)
        controller.collectBonus()
    }

    private fun addActorsOnTopPanelGroup() {
        with(topPanelGroup) {
            addTimeLabel()
            addTimeTextLabel()
            addBonusLabel()
            addBonusTextLabel()
        }
    }

    // ------------------------------------------------------------------------
    // GameGroup
    // ------------------------------------------------------------------------

    private fun AdvancedGroup.addCountDownLabel() {
        addActor(countDownLabel)
        countDownLabel.apply {
            setBounds(LMG.GameGroup.COUNT_DOWN_X, LMG.GameGroup.COUNT_DOWN_Y, LMG.GameGroup.COUNT_DOWN_W, LMG.GameGroup.COUNT_DOWN_H)
            setAlignment(Align.center)
            addAction(Actions.alpha(0f))
        }
    }

    private fun AdvancedGroup.addBag() {
        addActor(bagImage)
        bagImage.apply {
            disable()
            setBounds(LMG.GameGroup.BAG_X, LMG.GameGroup.BAG_Y, LMG.GameGroup.BAG_W, LMG.GameGroup.BAG_H)
            setOrigin(Align.center)
            addAction(Actions.parallel(
                Actions.scaleTo(0f, 0f),
                Actions.alpha(0f),
            ))

            val scaleValue  = listOf(0.03f, -0.03f)
            val rotateValue = listOf(5f, -5f)

            toClickable().setOnClickListener {
                setScale(scaleX - scaleValue.random())
                rotation -= rotateValue.random()
                controller.bonusFlow.value += 0.1f
            }
        }
    }

    private fun AdvancedGroup.addClickAnim() {
        addActor(clickAnim)
        clickAnim.apply {
            disable()
            setPosition(LMG.GameGroup.CLICK_ANIM_X, LMG.GameGroup.CLICK_ANIM_Y)
            addAction(Actions.alpha(0f))
        }
    }

    private fun addActorsOnGameGroup() {
        with(gameGroup) {
            addCountDownLabel()
            addBag()
            addClickAnim()
        }
    }

    // ------------------------------------------------------------------------
    // ResultGroup
    // ------------------------------------------------------------------------
    
    private fun AdvancedGroup.addBetPanelGroup() {
        addActor(betPanelGroup)
        betPanelGroup.apply {
            setBounds(LMG.ResultGroup.BET_PANEL_X, LMG.ResultGroup.BET_PANEL_Y, LMG.ResultGroup.BET_PANEL_W, LMG.ResultGroup.BET_PANEL_H)
            
            addAndFillActor(betPanelImage)
            addActors(betLabel, betTextLabel)
            
            betLabel.setBounds(LMG.ResultGroup.BET_LABEL_X, LMG.ResultGroup.BET_LABEL_Y, LMG.ResultGroup.BET_LABEL_W, LMG.ResultGroup.BET_LABEL_H)
            betTextLabel.setBounds(LMG.ResultGroup.BET_TEXT_X, LMG.ResultGroup.BET_TEXT_Y, LMG.ResultGroup.BET_TEXT_W, LMG.ResultGroup.BET_TEXT_H)
        }
    }

    private fun AdvancedGroup.addMultiplicationLabel() {
        addActor(multiplicationLabel)
        multiplicationLabel.setAlignment(Align.center)
        multiplicationLabel.setBounds(LMG.ResultGroup.MULTIPLICATION_X, LMG.ResultGroup.MULTIPLICATION_Y, LMG.ResultGroup.MULTIPLICATION_W, LMG.ResultGroup.MULTIPLICATION_H)
    }

    private fun AdvancedGroup.addResultBonusLabel() {
        addActor(resultBonusLabel)
        resultBonusLabel.setBounds(LMG.ResultGroup.BONUS_X, LMG.ResultGroup.BONUS_Y, LMG.ResultGroup.BONUS_W, LMG.ResultGroup.BONUS_H)
    }

    private fun AdvancedGroup.addEqualsLabel() {
        addActor(equalsLabel)
        equalsLabel.setAlignment(Align.center)
        equalsLabel.setBounds(LMG.ResultGroup.EQUALS_X, LMG.ResultGroup.EQUALS_Y, LMG.ResultGroup.EQUALS_W, LMG.ResultGroup.EQUALS_H)
    }

    private fun AdvancedGroup.addResultLabel() {
        addActor(resultLabel)
        resultLabel.setBounds(LMG.ResultGroup.RESULT_X, LMG.ResultGroup.RESULT_Y, LMG.ResultGroup.RESULT_W, LMG.ResultGroup.RESULT_H)
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