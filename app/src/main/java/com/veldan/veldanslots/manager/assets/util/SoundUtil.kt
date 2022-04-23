package com.veldan.veldanslots.manager.assets.util

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Sound
import com.veldan.veldanslots.manager.assets.SoundManager
import com.veldan.veldanslots.manager.AudioManager
import kotlinx.coroutines.flow.MutableStateFlow

object SoundUtil {

    val CHECK         get() = SoundManager.EnumSound.CHECK.data.sound
    val CLICK         get() = SoundManager.EnumSound.CLICK.data.sound
    val FAIL_BOX      get() = SoundManager.EnumSound.FAIL_BOX.data.sound
    val PLUS_MINUS    get() = SoundManager.EnumSound.PLUS_MINUS.data.sound
    val WIN_BOX       get() = SoundManager.EnumSound.WIN_BOX.data.sound
    val WIN_SLOT_ITEM get() = SoundManager.EnumSound.WIN_SLOT_ITEM.data.sound

    val soundList get() = listOf(CHECK, CLICK, FAIL_BOX, PLUS_MINUS, WIN_BOX, WIN_SLOT_ITEM)

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

