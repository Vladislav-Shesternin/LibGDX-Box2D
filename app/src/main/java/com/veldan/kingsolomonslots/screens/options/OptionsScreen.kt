package com.veldan.kingsolomonslots.screens.options

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.veldan.kingsolomonslots.actors.button.ButtonClickable
import com.veldan.kingsolomonslots.actors.button.ButtonClickableStyle
import com.veldan.kingsolomonslots.actors.checkbox.CheckBox
import com.veldan.kingsolomonslots.actors.checkbox.CheckBoxController
import com.veldan.kingsolomonslots.actors.checkbox.CheckBoxStyle
import com.veldan.kingsolomonslots.actors.progressBar.ProgressBar
import com.veldan.kingsolomonslots.advanced.AdvancedScreen
import com.veldan.kingsolomonslots.advanced.AdvancedStage
import com.veldan.kingsolomonslots.manager.AudioManager
import com.veldan.kingsolomonslots.manager.DataStoreManager
import com.veldan.kingsolomonslots.manager.NavigationManager
import com.veldan.kingsolomonslots.manager.assets.SpriteManager
import com.veldan.kingsolomonslots.utils.log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.veldan.kingsolomonslots.layout.Layout.Options as LO

class OptionsScreen : AdvancedScreen() {
    override val controller by lazy { OptionsScreenController(this) }

    private val soundProgressBar  = ProgressBar()
    private val musicProgressBar  = ProgressBar()
    private val soundImage        = Image(SpriteManager.OptionsRegion.SOUND.region)
    private val musicImage        = Image(SpriteManager.OptionsRegion.MUSIC.region)
    private val backButton        = ButtonClickable(ButtonClickableStyle.back)
    private val tutorialImage     = Image(SpriteManager.OptionsRegion.TUTORIAL.region)
    private val tutorialCheckBox  = CheckBox(CheckBoxStyle.gold)



    override fun show() {
        super.show()
        setBackgrounds(SpriteManager.OptionsRegion.BACKGROUND.region)
        stage.addActorsOnStage()
    }



    private fun AdvancedStage.addActorsOnStage() {
        addProgressBarMusic()
        addProgressBarSound()
        addSoundImage()
        addMusicImage()
        addBackButton()
        addTutorialImage()
        addTutorialCheckBox()
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
                    //MusicUtil.volumeLevel.value = it
                    log("music p = $it")
                }
            }
        }
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
                    //    SoundUtil.volumeLevel.value = (it / 100f)
                    log("sound p = $it")
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
            controller.setOnClickListener { NavigationManager.back() }
        }
    }

    private fun AdvancedStage.addTutorialImage() {
        addActor(tutorialImage)
        tutorialImage.setBounds(LO.TUTORIAL_IMAGE_X, LO.TUTORIAL_IMAGE_Y, LO.TUTORIAL_IMAGE_W, LO.TUTORIAL_IMAGE_H)
    }

    private fun AdvancedStage.addTutorialCheckBox() {
        addActor(tutorialCheckBox)
        tutorialCheckBox.apply {
            setBounds(LO.TUTORIAL_CHECK_BOX_X, LO.TUTORIAL_CHECK_BOX_Y, LO.TUTORIAL_CHECK_BOX_W, LO.TUTORIAL_CHECK_BOX_H)

            CoroutineScope(Dispatchers.IO).launch {
                DataStoreManager.getTutorial()?.let { isTutorial -> if (isTutorial) controller.check() else controller.uncheck() }
            }
        }
    }

}