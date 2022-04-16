package com.veldan.kingsolomonslots.manager

import android.content.Context
import android.media.AudioManager
import com.veldan.kingsolomonslots.main.game

object AudioManager {

    val audioManager      = game.activity.getSystemService(Context.AUDIO_SERVICE) as AudioManager
    val maxVolumeLevel    = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)
    val volumeLevel get() = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)

    val onePercentVolumeLevel = (maxVolumeLevel / 100f)

    val volumeLevelFrom_0_to_100 get() = (volumeLevel / onePercentVolumeLevel).toInt()

}