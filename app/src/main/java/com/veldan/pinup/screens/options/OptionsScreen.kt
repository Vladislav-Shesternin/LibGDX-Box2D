package com.veldan.pinup.screens.options

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.veldan.pinup.actors.button.ButtonClickable
import com.veldan.pinup.actors.checkbox.CheckBox
import com.veldan.pinup.actors.progressBar.ProgressBar
import com.veldan.pinup.advanced.group.AdvancedGroup
import com.veldan.pinup.advanced.AdvancedScreen
import com.veldan.pinup.advanced.AdvancedStage
import com.veldan.pinup.manager.AudioManager
import com.veldan.pinup.manager.NavigationManager
import com.veldan.pinup.manager.assets.SpriteManager
import com.veldan.pinup.utils.log
import java.util.*
import com.veldan.pinup.layout.Layout.Options as LO

class OptionsScreen : AdvancedScreen() {
    override val controller by lazy { OptionsScreenController(this) }

    private val soundProgressBar  = ProgressBar()
    private val musicProgressBar  = ProgressBar()
    private val soundImage        = Image(SpriteManager.OptionsSprite.SOUND.data.texture)
    private val musicImage        = Image(SpriteManager.OptionsSprite.MUSIC.data.texture)
    private val backButton        = ButtonClickable(ButtonClickable.Style.back)
    private val flagGroup         = AdvancedGroup()
    private val flagCheckBoxGroup = AdvancedGroup()

    val usImage                   = Image(SpriteManager.OptionsSprite.US.data.texture)
    val ruImage                   = Image(SpriteManager.OptionsSprite.RU.data.texture)
    val ukImage                   = Image(SpriteManager.OptionsSprite.UK.data.texture)
    val usCheckBox                = CheckBox(CheckBox.Style.style_1)
    val ruCheckBox                = CheckBox(CheckBox.Style.style_1)
    val ukCheckBox                = CheckBox(CheckBox.Style.style_1)



    override fun show() {
        super.show()
        setBackgrounds(SpriteManager.OptionsSprite.BACKGROUND.data.texture)
        stage.addActorsOnStage()
    }



    private fun AdvancedStage.addActorsOnStage() {
        addProgressBarSound()
        addProgressBarMusic()
        addSoundImage()
        addMusicImage()
        addBackButton()
        addFlagGroup()
        addCheckBoxFlagGroup()
    }



    private fun AdvancedStage.addProgressBarSound() {
        addActor(soundProgressBar)
        soundProgressBar.apply {
            setPosition(LO.PROGRESS_BAR_SOUND_X, LO.PROGRESS_BAR_SOUND_Y)
            with(OptionsScreenController) {
                if (progressSoundVolume == -1) progressSoundVolume = AudioManager.volumeLevelFrom_0_to_100
                controller.setProgress(progressSoundVolume)
                controller.progressBlock = {
                    progressSoundVolume = it
                    //SoundUtil.volumeLevel.value = (it / 100f)
                    log("sound p = $it")
                }
            }
        }
    }

    private fun AdvancedStage.addProgressBarMusic() {
        addActor(musicProgressBar)
        musicProgressBar.apply {
            setPosition(LO.PROGRESS_BAR_MUSIC_X, LO.PROGRESS_BAR_MUSIC_Y)
            with(OptionsScreenController) {
                if (progressMusicVolume == -1) progressMusicVolume = AudioManager.volumeLevelFrom_0_to_100
                controller.setProgress(progressMusicVolume)
                controller.progressBlock = {
                    progressMusicVolume = it
                    // MusicUtil.volumeLevel.value = it
                    log("music p = $it")
                }
            }
        }
    }

    private fun AdvancedStage.addSoundImage() {
        addActor(soundImage)
        soundImage.setBounds(LO.SOUND_X, LO.SOUND_Y, LO.SOUND_W, LO.SOUND_H)
    }

    private fun AdvancedStage.addMusicImage() {
        addActor(musicImage)
        musicImage.setBounds(LO.MUSIC_X, LO.MUSIC_Y, LO.MUSIC_W, LO.MUSIC_H)
    }

    private fun AdvancedStage.addBackButton() {
        addActor(backButton)
        backButton.apply {
            setBounds(LO.BACK_X, LO.BACK_Y, LO.BACK_W, LO.BACK_H)
            setOnClickListener { NavigationManager.back() }
        }
    }

    private fun AdvancedStage.addFlagGroup() {
        addActor(flagGroup)
        flagGroup.apply {
            setBounds(LO.FlagGroup.X, LO.FlagGroup.Y, LO.FlagGroup.W, LO.FlagGroup.H)

            var newX = LO.FlagGroup.FLAG_FIRST_X
            controller.flagList.map { it.image }.onEach { flag ->
                addActor(flag)
                flag.setBounds(newX, LO.FlagGroup.FLAG_Y, LO.FlagGroup.FLAG_W, LO.FlagGroup.FLAG_H)
                newX += LO.FlagGroup.FLAG_W + LO.FlagGroup.FLAG_SPACE_HORIZONTAL
            }
        }
    }

    private fun AdvancedStage.addCheckBoxFlagGroup() {
        addActor(flagCheckBoxGroup)
        flagCheckBoxGroup.apply {
            setBounds(LO.FlagCheckBoxGroup.X, LO.FlagCheckBoxGroup.Y, LO.FlagCheckBoxGroup.W, LO.FlagCheckBoxGroup.H)

            var newX = LO.FlagCheckBoxGroup.CHECK_BOX_FIRST_X
            controller.flagList.onEach { flag ->
                with(flag.checkBox) {
                    this@apply.addActor(this)
                    setBounds(newX, LO.FlagCheckBoxGroup.CHECK_BOX_Y, LO.FlagCheckBoxGroup.CHECK_BOX_W, LO.FlagCheckBoxGroup.CHECK_BOX_H)
                    newX += LO.FlagCheckBoxGroup.CHECK_BOX_W + LO.FlagCheckBoxGroup.CHECK_BOX_SPACE_HORIZONTAL
                    checkBoxGroup = this@OptionsScreen.controller.checkBoxGroup
                    setOnCheckListener { isCheck -> if (isCheck) flag.block() }

                    if (flag.tag == OptionsScreenController.checkedFlag) flag.checkBox.check()
                }
            }
        }
    }

}