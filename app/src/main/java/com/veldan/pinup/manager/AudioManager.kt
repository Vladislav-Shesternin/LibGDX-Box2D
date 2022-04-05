package com.veldan.pinup.manager

import android.content.Context
import android.media.AudioManager
import com.veldan.pinup.main.game

object AudioManager {

    val audioManager      = game.activity.getSystemService(Context.AUDIO_SERVICE) as AudioManager
    val maxVolumeLevel    = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)
    val volumeLevel get() = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)

    val onePercentVolumeLevel = (maxVolumeLevel / 100f)

    val volumeLevelFrom_0_to_100 get() = (volumeLevel / onePercentVolumeLevel).toInt()

}