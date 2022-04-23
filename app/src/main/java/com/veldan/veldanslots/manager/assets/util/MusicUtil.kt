package com.veldan.veldanslots.manager.assets.util

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.utils.Disposable
import com.veldan.veldanslots.manager.assets.MusicManager
import com.veldan.veldanslots.manager.AudioManager
import com.veldan.veldanslots.utils.cancelCoroutinesAll
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

object MusicUtil: Disposable {

    private val coroutineVolumeLevel = CoroutineScope(Dispatchers.Default)


    val MAIN       get() = MusicManager.EnumMusic.MAIN.data.music
    val MINI_GAME  get() = MusicManager.EnumMusic.MINI_GAME.data.music
    val SUPER_GAME get() = MusicManager.EnumMusic.SUPER_GAME.data.music

    val musicList get() = listOf(MAIN, MINI_GAME, SUPER_GAME)

    val volumeLevel = MutableStateFlow(AudioManager.volumeLevelFrom_0_to_100)

    var isPause = false

    private var previousMusic: Music? = null
    var currentMusic: Music?
        get() = previousMusic
        set(value) {
            Gdx.app.postRunnable {
                if (isPause) return@postRunnable

                if (previousMusic != value) {
                    previousMusic?.stop()
                    previousMusic = value
                    previousMusic?.play()
                }

                previousMusic?.isLooping = true
            }
        }



    init {
        collectVolumeLevel()
    }



    override fun dispose() {
        previousMusic = null
        cancelCoroutinesAll(coroutineVolumeLevel)
    }



    private fun collectVolumeLevel() {
        var volume: Float

        coroutineVolumeLevel.launch { volumeLevel.collect { level ->
            volume = (level / 100f)
            isPause = (volume < 0f)
            musicList.onEach { music -> music.volume = volume }
        } }
    }

}