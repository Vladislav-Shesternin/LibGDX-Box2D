package com.veldan.pinup.screens.menu

import com.veldan.pinup.R
import com.veldan.pinup.actors.button.ButtonClickable
import com.veldan.pinup.actors.label.LabelStyleUtil
import com.veldan.pinup.actors.label.spinning.SpinningLabel
import com.veldan.pinup.advanced.AdvancedScreen
import com.veldan.pinup.advanced.AdvancedStage
import com.veldan.pinup.manager.assets.SpriteManager
import com.veldan.pinup.utils.language.Language
import java.util.*
import com.veldan.pinup.layout.Layout.Menu as LM

class MenuScreen : AdvancedScreen() {
    override val controller = MenuScreenController(this)

    private val playButton   = ButtonClickable(ButtonClickable.Style.button_1)
    private val optionButton = ButtonClickable(ButtonClickable.Style.button_1)
    private val exitButton   = ButtonClickable(ButtonClickable.Style.button_1)
    private val playLabel    by lazy { SpinningLabel(Language.getStringResource(R.string.play), LabelStyleUtil.white92)}
    private val optionsLabel by lazy { SpinningLabel(Language.getStringResource(R.string.options), LabelStyleUtil.white92)}
    private val exitLabel    by lazy { SpinningLabel(Language.getStringResource(R.string.exit), LabelStyleUtil.white92)}



    override fun show() {
        super.show()
        //with(MusicUtil) { currentMusic = MAIN }
        setBackgrounds(SpriteManager.MenuSprite.BACKGROUND.data.texture)
        stage.addActorsOnStage()
    }



    private fun AdvancedStage.addActorsOnStage() {
        addPlay()
        addOptions()
        addExit()
    }

    private fun AdvancedStage.addPlay() {
        addActor(playButton)
        playButton.apply {
            setBounds(LM.Button.X, LM.PLAY_Y, LM.Button.W, LM.Button.H)
            setOnClickListener { this@MenuScreen.controller.playHandler() }

            addActor(playLabel)
            playLabel.setBounds(LM.Button.TEXT_X, LM.Button.TEXT_Y, LM.Button.TEXT_W, LM.Button.TEXT_H)
        }
    }

    private fun AdvancedStage.addOptions() {
        addActor(optionButton)
        optionButton.apply {
            setBounds(LM.Button.X, LM.OPTIONS_Y, LM.Button.W, LM.Button.H)
            setOnClickListener { this@MenuScreen.controller.optionsHandler() }

            addActor(optionsLabel)
            optionsLabel.setBounds(LM.Button.TEXT_X, LM.Button.TEXT_Y, LM.Button.TEXT_W, LM.Button.TEXT_H)
        }
    }

    private fun AdvancedStage.addExit() {
        addActor(exitButton)
        exitButton.apply {
            setBounds(LM.Button.X, LM.EXIT_Y, LM.Button.W, LM.Button.H)
            setOnClickListener { this@MenuScreen.controller.exitHandler() }

            addActor(exitLabel)
            exitLabel.setBounds(LM.Button.TEXT_X, LM.Button.TEXT_Y, LM.Button.TEXT_W, LM.Button.TEXT_H)
        }
    }


}