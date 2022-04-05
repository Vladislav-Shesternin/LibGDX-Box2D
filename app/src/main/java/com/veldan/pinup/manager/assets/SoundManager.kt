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
        CHECK(           SoundData("sound/check.ogg")           ),
        CLICK(           SoundData("sound/click.ogg")           ),
        CLICK_BALANCE(   SoundData("sound/click_balance.ogg")   ),
        CLICK_PLUS_MINUS(SoundData("sound/click_plus_minus.ogg")),
        WIN(             SoundData("sound/win.ogg")             ),
    }



    interface IEnumSound {
        val data: SoundData
    }

}