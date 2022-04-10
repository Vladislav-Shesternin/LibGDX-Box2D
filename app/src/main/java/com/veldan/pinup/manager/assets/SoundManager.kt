package com.veldan.pinup.manager.assets

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.audio.Sound
import com.veldan.pinup.manager.assets.util.SoundData

object SoundManager {

    var loadListSound = mutableListOf<IEnumSound>()

    fun load(assetManager: AssetManager) {
        loadListSound.onEach { assetManager.load(it.data.path, Sound::class.java) }
    }

    fun init(assetManager: AssetManager) {
        loadListSound.onEach { it.data.sound = assetManager[it.data.path, Sound::class.java] }
    }



    enum class EnumSound(override val data: SoundData): IEnumSound {
        CHECK(     SoundData("sound/check.mp3")     ),
        CLICK(     SoundData("sound/click.mp3")     ),
        CLICK_BAG( SoundData("sound/click_bag.mp3") ),
        FAIL(      SoundData("sound/fail.mp3")      ),
        PLUS_MINUS(SoundData("sound/plus_minus.mp3")),
        WIN(       SoundData("sound/win.mp3")       ),
    }



    interface IEnumSound {
        val data: SoundData
    }

}