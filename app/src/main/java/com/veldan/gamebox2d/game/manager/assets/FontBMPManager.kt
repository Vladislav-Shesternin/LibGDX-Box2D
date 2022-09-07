package com.veldan.gamebox2d.game.manager.assets

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.BitmapFontLoader
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont

object FontBMPManager {

    var loadListFont = mutableListOf<IBMPEnumFont>()



    fun load(assetManager: AssetManager) {
        with(assetManager) { loadListFont.onEach {
                load(it.data.path, BitmapFont::class.java, BitmapFontLoader.BitmapFontParameter().apply {
                    minFilter = Texture.TextureFilter.Linear
                    magFilter = Texture.TextureFilter.Linear
                })
        } }
    }

    fun init(assetManager: AssetManager) {
        loadListFont.onEach { it.data.font = assetManager[it.data.path, BitmapFont::class.java] }
    }



    enum class EnumFont(override val data: FontBPMData): IBMPEnumFont {
    }



    interface IBMPEnumFont {
        val data: FontBPMData
    }

    data class FontBPMData(
        val path: String,
    ) {
        lateinit var font: BitmapFont
    }
}