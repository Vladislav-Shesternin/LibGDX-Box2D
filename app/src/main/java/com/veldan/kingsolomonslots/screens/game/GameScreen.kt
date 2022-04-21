package com.veldan.kingsolomonslots.screens.game

import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.veldan.kingsolomonslots.R
import com.veldan.kingsolomonslots.actors.button.ButtonClickable
import com.veldan.kingsolomonslots.actors.button.ButtonClickableStyle
import com.veldan.kingsolomonslots.actors.label.LabelStyle
import com.veldan.kingsolomonslots.actors.label.spinning.SpinningLabel
import com.veldan.kingsolomonslots.actors.bonusGameGroup.miniGame.MiniGameGroup
import com.veldan.kingsolomonslots.actors.bonusGameGroup.superGame.SuperGameGroup
import com.veldan.kingsolomonslots.actors.bonusGameGroup.superGame.finish.FinishSuperGameGroup
import com.veldan.kingsolomonslots.actors.bonusGameGroup.superGame.superGameElementGroup.SuperGameElementGroup
import com.veldan.kingsolomonslots.actors.slot.slotGroup.SlotGroup
import com.veldan.kingsolomonslots.actors.tutorial.TutorialGroup
import com.veldan.kingsolomonslots.advanced.AdvancedScreen
import com.veldan.kingsolomonslots.advanced.AdvancedStage
import com.veldan.kingsolomonslots.advanced.group.AdvancedGroup
import com.veldan.kingsolomonslots.manager.DataStoreManager
import com.veldan.kingsolomonslots.manager.NavigationManager
import com.veldan.kingsolomonslots.manager.assets.SpriteManager
import com.veldan.kingsolomonslots.manager.assets.util.MusicUtil
import com.veldan.kingsolomonslots.manager.assets.util.SoundUtil
import com.veldan.kingsolomonslots.screens.options.OptionsScreen
import com.veldan.kingsolomonslots.utils.disable
import com.veldan.kingsolomonslots.utils.enable
import com.veldan.kingsolomonslots.utils.language.Language
import kotlinx.coroutines.*
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
    val optionsButton     = ButtonClickable(ButtonClickableStyle.options)
    // autospin
    val autoSpinButton    = ButtonClickable(ButtonClickableStyle.autoSpin)
    // spin
    val spinButton        = ButtonClickable(ButtonClickableStyle.spin)
    val spinTextLabel     by lazy { SpinningLabel(Language.getStringResource(R.string.spin), LabelStyle.white_60) }
    // slotGroup
    val slotGroup         = SlotGroup()
    // tutorialGroup
    val tutorialGroup     = TutorialGroup()

    // miniGameGroup
    lateinit var miniGameGroup: MiniGameGroup
    // superGameGroup
    lateinit var superGameGroup       : SuperGameGroup
    lateinit var superGameElementGroup: SuperGameElementGroup
    lateinit var finishSuperGameGroup : FinishSuperGameGroup



    override fun show() {
        super.show()
        with(MusicUtil) { currentMusic = MAIN }
        setBackgrounds(SpriteManager.GameRegion.BACKGROUND.region)
        stage.addActorsOnStage()
    }



    private fun AdvancedStage.addActorsOnStage() {
        addGameGroup()
        addTutorialGroup()
    }

    // ------------------------------------------------------------------------
    // GameGroup
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addGameGroup() {
        addAndFillActor(gameGroup)
        addActorsOnGameGroup()
    }

    private fun addActorsOnGameGroup() {
        with(gameGroup) {
            addBalancePanel()
            addBetPanel()
            addBetPlusButton()
            addBetMinusButton()
            addMenuButton()
            addAutoSpinButton()
            addSpinButton()
            addSlotGroup()
        }
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
            controller.setOnClickListener(SoundUtil.PLUS_MINUS) { this@GameScreen.controller.betPlusHandler() }
        }
    }

    private fun AdvancedGroup.addBetMinusButton() {
        addActor(betMinusButton)
        betMinusButton.apply {
            setBounds(LG.BET_MINUS_X, LG.BET_MINUS_Y, LG.BET_MINUS_W, LG.BET_MINUS_H)
            controller.setOnClickListener(SoundUtil.PLUS_MINUS) { this@GameScreen.controller.betMinusHandler() }
        }
    }

    private fun AdvancedGroup.addMenuButton() {
        addActor(optionsButton)
        optionsButton.apply {
            setBounds(LG.OPTIONS_X, LG.OPTIONS_Y, LG.OPTIONS_W, LG.OPTIONS_H)
            controller.setOnClickListener { NavigationManager.navigate(OptionsScreen(), GameScreen()) }
        }
    }

    private fun AdvancedGroup.addAutoSpinButton() {
        addActor(autoSpinButton)
        autoSpinButton.apply {
            setBounds(LG.AUTO_SPIN_X, LG.AUTO_SPIN_Y, LG.AUTO_SPIN_W, LG.AUTO_SPIN_H)
            controller.setOnClickListener { this@GameScreen.controller.autoSpinHandler() }
        }
    }

    private fun AdvancedGroup.addSpinButton() {
        addActor(spinButton)
        spinButton.apply {
            setBounds(LG.SPIN_X, LG.SPIN_Y, LG.SPIN_W, LG.SPIN_H)

            addActor(spinTextLabel)
            spinTextLabel.setBounds(LG.SPIN_TEXT_X, LG.SPIN_TEXT_Y, LG.SPIN_TEXT_W, LG.SPIN_TEXT_H)

            controller.setOnClickListener { this@GameScreen.controller.spinHandler() }
        }
    }

    private fun AdvancedGroup.addSlotGroup() {
        addActor(slotGroup)
        slotGroup.setPosition(LG.SLOT_GROUP_X, LG.SLOT_GROUP_Y)
    }

    // ------------------------------------------------------------------------
    // TutorialGroup
    // ------------------------------------------------------------------------
    private fun AdvancedStage.addTutorialGroup() {
        CoroutineScope(Dispatchers.IO).launch {

            suspend fun startTutorial() {
                gameGroup.disable()
                addAndFillActor(tutorialGroup)
                tutorialGroup.controller.start()
                removeTutorialGroup()
                cancel()
            }

            if (DataStoreManager.Tutorial.get() == null) {
                DataStoreManager.Tutorial.update { false }
                startTutorial()
            }
            else if (DataStoreManager.Tutorial.get()!!) startTutorial()
        }
    }

    private suspend fun removeTutorialGroup() = CompletableDeferred<Boolean>().also { continuation ->
        tutorialGroup.addAction(Actions.sequence(
            Actions.fadeOut(GameScreenController.TIME_HIDE_SCREEN),
            Actions.run { continuation.complete(true) },
            Actions.removeActor(),
        ))
        gameGroup.enable()
    }.await()

    // ------------------------------------------------------------------------
    // MiniGameGroup
    // ------------------------------------------------------------------------

    fun addMiniGameGroup() {
        with(stage) {
            miniGameGroup = MiniGameGroup()
            addAndFillActor(miniGameGroup)
        }
    }

    fun removeMiniGameGroup() {
        miniGameGroup.remove()
    }

    // ------------------------------------------------------------------------
    // SuperGameGroup
    // ------------------------------------------------------------------------
    fun addSuperGameGroup() {
        with(stage) {
            superGameGroup = SuperGameGroup()
            superGameGroup.addAction(Actions.alpha(0f))
            addAndFillActor(superGameGroup)
        }
    }

    fun addSuperGameElementGroup() {
        with(gameGroup) {
            superGameElementGroup = SuperGameElementGroup()
            addActor(superGameElementGroup)
            superGameElementGroup.setPosition(LG.SUPER_GAME_ELEMENT_GROUP_X, LG.SUPER_GAME_ELEMENT_GROUP_Y)

        }
    }

    fun addFinishSuperGameGroup(balance: Long) {
        with(stage) {
            finishSuperGameGroup = FinishSuperGameGroup(balance)
            finishSuperGameGroup.addAction(Actions.alpha(0f))
            addAndFillActor(finishSuperGameGroup)
        }
    }

    fun removeSuperGameElementGroup() {
        superGameElementGroup.remove()
    }

    fun removeFinishSuperGameGroup() {
        finishSuperGameGroup.remove()
    }

    fun removeSuperGameGroup() {
        superGameGroup.remove()
    }

}