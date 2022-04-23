package com.veldan.veldanslots.manager.assets

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.BitmapFontLoader
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.veldan.veldanslots.manager.assets.util.FontBPMData

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
}