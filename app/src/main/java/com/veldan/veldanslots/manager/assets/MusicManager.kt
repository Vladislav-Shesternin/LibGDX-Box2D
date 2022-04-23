package com.veldan.veldanslots.manager.assets

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.audio.Music
import com.veldan.veldanslots.manager.assets.util.MusicData

object MusicManager {

    var loadListMusic = mutableListOf<IEnumMusic>()

    fun load(assetManager: AssetManager) {
        loadListMusic.onEach { assetManager.load(it.data.path, Music::class.java) }
    }

    fun init(assetManager: AssetManager) {
        loadListMusic.onEach { it.data.music = assetManager[it.data.path, Music::class.java] }
    }



    enum class EnumMusic(override val data: MusicData): IEnumMusic {
        MAIN(      MusicData("music/main.ogg")      ),
        MINI_GAME( MusicData("music/mini_game.ogg") ),
        SUPER_GAME(MusicData("music/super_game.ogg")),
    }



    interface IEnumMusic {
        val data: MusicData
    }

}