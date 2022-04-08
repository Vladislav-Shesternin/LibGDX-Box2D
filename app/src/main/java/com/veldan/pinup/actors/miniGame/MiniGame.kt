package com.veldan.pinup.actors.miniGame

import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.veldan.pinup.R
import com.veldan.pinup.actors.label.LabelStyleUtil
import com.veldan.pinup.actors.label.spinning.SpinningLabel
import com.veldan.pinup.advanced.group.AbstractAdvancedGroup
import com.veldan.pinup.advanced.group.AdvancedGroup
import com.veldan.pinup.manager.assets.SpriteManager
import com.veldan.pinup.utils.disable
import com.veldan.pinup.utils.language.Language
import com.veldan.pinup.layout.Layout.MiniGame as LMG

class MiniGame : AbstractAdvancedGroup() {
    override val controller = MiniGameController(this)

    val topPanelGroup  = AdvancedGroup()
    val countDownLabel = Label("", LabelStyleUtil.amaranteWhite550)
    val timeLabel      by lazy { SpinningLabel(Language.getStringResource(R.string.time), LabelStyleUtil.white60) }
    val bonusLabel     by lazy { SpinningLabel(Language.getStringResource(R.string.bonus), LabelStyleUtil.white60) }
    val timeTextLabel  = SpinningLabel("", LabelStyleUtil.amaranteWhite60)
    val bonusTextLabel = SpinningLabel("", LabelStyleUtil.amaranteWhite60)
    val bagImage       = Image(SpriteManager.GameSprite.BAG.data.texture)


    init {
        addActorsOnGroup()
    }



    private fun AdvancedGroup.addActorsOnGroup() {
        addTopPanelGroup()
        addCountDownLabel()
        addBag()
    }

    private fun AdvancedGroup.addTopPanelGroup() {
        addActor(topPanelGroup)
        topPanelGroup.apply {
            setBounds(LMG.TopPanel.X, LMG.TopPanel.Y, LMG.TopPanel.W, LMG.TopPanel.H)
            addAction(Actions.alpha(0f))
        }
        addActorsOnTopPanelGroup()
    }

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
    }

    private fun AdvancedGroup.addCountDownLabel() {
        addActor(countDownLabel)
        countDownLabel.apply {
            setBounds(LMG.COUNT_DOWN_X, LMG.COUNT_DOWN_Y, LMG.COUNT_DOWN_W, LMG.COUNT_DOWN_H)
            setAlignment(Align.center)
            addAction(Actions.alpha(0f))
        }
    }

    private fun AdvancedGroup.addBag() {
        addActor(bagImage)
        bagImage.apply {
            disable()
            setBounds(LMG.BAG_X, LMG.BAG_Y, LMG.BAG_W, LMG.BAG_H)
            setOrigin(Align.center)
            addAction(Actions.parallel(
                Actions.scaleTo(0f, 0f),
                Actions.alpha(0f),
            ))
        }
    }

    private fun addActorsOnTopPanelGroup() {
        with(topPanelGroup) {
            addTimeLabel()
            addTimeTextLabel()
            addBonusLabel()
            addBonusTextLabel()
        }
    }

}