package com.veldan.pinup.manager.assets.util

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Sound
import com.veldan.pinup.manager.assets.SoundManager
import com.veldan.pinup.manager.AudioManager
import kotlinx.coroutines.flow.MutableStateFlow

object SoundUtil {
    val CLICK            get() = SoundManager.EnumSound.CLICK.data.sound
    val CHECK            get() = SoundManager.EnumSound.CHECK.data.sound
    val CLICK_BALANCE    get() = SoundManager.EnumSound.CLICK_BALANCE.data.sound
    val CLICK_PLUS_MINUS get() = SoundManager.EnumSound.CLICK_PLUS_MINUS.data.sound
    val WIN              get() = SoundManager.EnumSound.WIN.data.sound

    val soundList get() = listOf(CLICK, CHECK, CLICK_BALANCE, CLICK_PLUS_MINUS, WIN)

    val volumeLevel = MutableStateFlow(AudioManager.volumeLevelFrom_0_to_100 / 100f)

    val isPause get() = (volumeLevel.value < 0f)

}

fun Sound.playAdvanced() {
    Gdx.app.postRunnable { with(SoundUtil) { if (isPause.not()) play(volumeLevel.value) } }
}

