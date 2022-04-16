package com.veldan.kingsolomonslots.manager.assets.util

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Sound
import com.veldan.kingsolomonslots.manager.assets.SoundManager
import com.veldan.kingsolomonslots.manager.AudioManager
import kotlinx.coroutines.flow.MutableStateFlow

object SoundUtil {
    val CLICK      get() = SoundManager.EnumSound.CLICK.data.sound
    val CHECK      get() = SoundManager.EnumSound.CHECK.data.sound
    val WIN        get() = SoundManager.EnumSound.WIN.data.sound
    val FAIL       get() = SoundManager.EnumSound.FAIL.data.sound
    val CLICK_BAG  get() = SoundManager.EnumSound.CLICK_BAG.data.sound
    val PLUS_MINUS get() = SoundManager.EnumSound.PLUS_MINUS.data.sound

    val soundList get() = listOf(CLICK, CHECK, CLICK, WIN, FAIL, CLICK_BAG, PLUS_MINUS)

    val volumeLevel = MutableStateFlow(AudioManager.volumeLevelFrom_0_to_100 / 100f)

    val isPause get() = (volumeLevel.value < 0f)

}

var previousSoundId = 0L

fun Sound.playAdvanced() {
    Gdx.app.postRunnable { with(SoundUtil) {
        if (isPause.not()) {
            if (previousSoundId != 0L) stop(previousSoundId)
            previousSoundId = play(volumeLevel.value)
        }
    } }
}

