package com.veldan.kingsolomonslots.manager.assets

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.audio.Sound
import com.veldan.kingsolomonslots.manager.assets.util.SoundData

object SoundManager {

    var loadListSound = mutableListOf<IEnumSound>()

    fun load(assetManager: AssetManager) {
        loadListSound.onEach { assetManager.load(it.data.path, Sound::class.java) }
    }

    fun init(assetManager: AssetManager) {
        loadListSound.onEach { it.data.sound = assetManager[it.data.path, Sound::class.java] }
    }



    enum class EnumSound(override val data: SoundData): IEnumSound {
        CHECK(        SoundData("sound/check.mp3")        ),
        CLICK(        SoundData("sound/click.mp3")        ),
        FAIL_BOX(     SoundData("sound/fail_box.mp3")     ),
        PLUS_MINUS(   SoundData("sound/plus_minus.mp3")   ),
        WIN_BOX(      SoundData("sound/win_box.mp3")      ),
        WIN_SLOT_ITEM(SoundData("sound/win_slot_item.mp3")),
    }



    interface IEnumSound {
        val data: SoundData
    }

}