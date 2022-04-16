package com.veldan.kingsolomonslots.screens.game

import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.veldan.kingsolomonslots.actors.button.ButtonClickable
import com.veldan.kingsolomonslots.actors.button.ButtonClickableStyle
import com.veldan.kingsolomonslots.actors.label.LabelStyle
import com.veldan.kingsolomonslots.actors.label.spinning.SpinningLabel
import com.veldan.kingsolomonslots.advanced.AdvancedScreen
import com.veldan.kingsolomonslots.advanced.AdvancedStage
import com.veldan.kingsolomonslots.advanced.group.AdvancedGroup
import com.veldan.kingsolomonslots.manager.NavigationManager
import com.veldan.kingsolomonslots.manager.assets.SpriteManager
import com.veldan.kingsolomonslots.screens.options.OptionsScreen
import com.veldan.kingsolomonslots.utils.language.Language
import com.veldan.kingsolomonslots.layout.Layout.Game as LG

class GameScreen: AdvancedScreen() {
    override val controller = GameScreenController(this)

    // gameGroup
    val gameGroup         = AdvancedGroup()
    // balance
    val balancePanelGroup = AdvancedGroup()
    val balancePanelImage = Image(SpriteManager.GameRegion.BALANCE_PANEL.region)
    val balanceTextLabel  = SpinningLabel("", LabelStyle.reggaeOne_50)
    // bet
    val betPanelGroup     = AdvancedGroup()
    val betPanelImage     = Image(SpriteManager.GameRegion.BET_PANEL.region)
    val betTextLabel      = SpinningLabel("", LabelStyle.reggaeOne_40)
    val betPlusButton     = ButtonClickable(ButtonClickableStyle.plus)
    val betMinusButton    = ButtonClickable(ButtonClickableStyle.minus)
    // options
    val optionsButton        = ButtonClickable(ButtonClickableStyle.options)
//    // autospin
//    val autoSpinButton    = ButtonClickable(ButtonClickable.Style.autoSpin)
//    // spin
//    val spinButton        = ButtonClickable(ButtonClickable.Style.spin)
//    val spinTextLabel     by lazy { SpinningLabel(Language.getStringResource(R.string.spin), LabelStyle.white96) }
//    // slotGroup
//    val slotGroup         = SlotGroup()


    override fun show() {
        super.show()
        setBackgrounds(SpriteManager.GameRegion.BACKGROUND.region)
        stage.addActorsOnStage()
    }



    private fun AdvancedStage.addActorsOnStage() {
        addGameGroup()
    }

    private fun AdvancedStage.addGameGroup() {
        addAndFillActor(gameGroup)
        gameGroup.addActorsOnGroup()
    }

    private fun AdvancedGroup.addActorsOnGroup() {
        addBalancePanel()
        addBetPanel()
        addBetPlusButton()
        addBetMinusButton()
        addMenuButton()
//        addAutoSpinButton()
//        addSpinButton()
//        addSlotGroup()
    }

    private fun AdvancedGroup.addBalancePanel() {
        addActor(balancePanelGroup)
        balancePanelGroup.apply {
            setBounds(LG.BALANCE_PANEL_X, LG.BALANCE_PANEL_Y, LG.BALANCE_PANEL_W, LG.BALANCE_PANEL_H)

            addAndFillActor(balancePanelImage)
            addActor(balanceTextLabel)

            balanceTextLabel.apply {
                setBounds(LG.BALANCE_TEXT_X, LG.BALANCE_TEXT_Y, LG.BALANCE_TEXT_W, LG.BALANCE_TEXT_H)
                this@GameScreen.controller.updateBalance()
            }
        }
    }

    private fun AdvancedGroup.addBetPanel() {
        addActor(betPanelGroup)
        betPanelGroup.apply {
            setBounds(LG.BET_PANEL_X, LG.BET_PANEL_Y, LG.BET_PANEL_W, LG.BET_PANEL_H)

            addAndFillActor(betPanelImage)
            addActors(betTextLabel)

            betTextLabel.apply {
                setBounds(LG.BET_TEXT_X, LG.BET_TEXT_Y, LG.BET_TEXT_W, LG.BET_TEXT_H)
                this@GameScreen.controller.updateBet()
            }
        }
    }

    private fun AdvancedGroup.addBetPlusButton() {
        addActor(betPlusButton)
        betPlusButton.apply {
            setBounds(LG.BET_PLUS_X, LG.BET_PLUS_Y, LG.BET_PLUS_W, LG.BET_PLUS_H)
            controller.setOnClickListener { this@GameScreen.controller.betPlusHandler() }
        }
    }

    private fun AdvancedGroup.addBetMinusButton() {
        addActor(betMinusButton)
        betMinusButton.apply {
            setBounds(LG.BET_MINUS_X, LG.BET_MINUS_Y, LG.BET_MINUS_W, LG.BET_MINUS_H)
            controller.setOnClickListener { this@GameScreen.controller.betMinusHandler() }
        }
    }

    private fun AdvancedGroup.addMenuButton() {
        addActor(optionsButton)
        optionsButton.apply {
            setBounds(LG.OPTIONS_X, LG.OPTIONS_Y, LG.OPTIONS_W, LG.OPTIONS_H)
            controller.setOnClickListener { NavigationManager.navigate(OptionsScreen(), GameScreen()) }
        }
    }

  /*  private fun AdvancedGroup.addAutoSpinButton() {
        addActor(autoSpinButton)
        autoSpinButton.apply {
            setBounds(LG.AUTO_SPIN_X, LG.AUTO_SPIN_Y, LG.AUTO_SPIN_W, LG.AUTO_SPIN_H)
            setOnClickListener { this@GameScreen.controller.autoSpinHandler() }
        }
    }

    private fun AdvancedGroup.addSpinButton() {
        addActor(spinButton)
        spinButton.apply {
            setBounds(LG.SPIN_X, LG.SPIN_Y, LG.SPIN_W, LG.SPIN_H)

            addActor(spinTextLabel)
            spinTextLabel.setBounds(LG.SPIN_TEXT_X, LG.SPIN_TEXT_Y, LG.SPIN_TEXT_W, LG.SPIN_TEXT_H)

            setOnClickListener { this@GameScreen.controller.spinHandler() }
        }
    }

    private fun AdvancedGroup.addSlotGroup() {
        addActor(slotGroup)
        slotGroup.setPosition(LG.SLOT_GROUP_X, LG.SLOT_GROUP_Y)
    }*/



//    fun addMiniGame() {
//        miniGame = MiniGame()
//        with(stage) {
//            addAndFillActor(miniGame)
//            miniGame.apply {
//                disable()
//                addAction(Actions.alpha(0f))
//            }
//        }
//    }
//
//    fun addSuperGame() {
//        superGame = SuperGame()
//        with(stage) {
//            addAndFillActor(superGame)
//            superGame.apply {
//                disable()
//                addAction(Actions.alpha(0f))
//            }
//        }
//    }



}