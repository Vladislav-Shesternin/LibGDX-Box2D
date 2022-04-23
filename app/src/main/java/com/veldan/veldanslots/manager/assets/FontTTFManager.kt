package com.veldan.veldanslots.manager.assets

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver
import com.badlogic.gdx.graphics.Texture.TextureFilter
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader
import com.veldan.veldanslots.manager.assets.util.FontTTFData

object FontTTFManager {

    private const val pathReggaeOne = "font/TTF/ReggaeOne.ttf"
    private const val pathNotoSans = "font/TTF/NotoSans.ttf"

    private val resolverInternal = InternalFileHandleResolver()

    var loadListFont = mutableListOf<FontTTFData>()



    private fun AssetManager.setLoaderTTF() {
        setLoader(FreeTypeFontGenerator::class.java, FreeTypeFontGeneratorLoader(resolverInternal))
        setLoader(BitmapFont::class.java, ".ttf", FreetypeFontLoader(resolverInternal))
    }

    private fun getLoaderParameter(
        path: String,
        parameters: FreeTypeFontGenerator.FreeTypeFontParameter.() -> Unit = { }
    ) = FreetypeFontLoader.FreeTypeFontLoaderParameter().apply {
        fontFileName = path
        fontParameters.apply {
            minFilter = TextureFilter.Linear
            magFilter = TextureFilter.Linear
            incremental = true
            parameters()
        }
    }



    fun load(assetManager: AssetManager) {
        with(assetManager) {
            setLoaderTTF()
            loadListFont.onEach { load(it.name  + ".ttf", BitmapFont::class.java, it.parameters) }
        }
    }

    fun init(assetManager: AssetManager) {
        loadListFont.onEach { it.font = assetManager[it.name + ".ttf", BitmapFont::class.java] }
    }



    object ReggaeOneFont: IFont {
        override val font_40 = FontTTFData("ReggaeOne_40", getLoaderParameter(pathReggaeOne) { size = 40 })
        override val font_50 = FontTTFData("ReggaeOne_50", getLoaderParameter(pathReggaeOne) { size = 50 })
        override val font_60 = FontTTFData("ReggaeOne_60", getLoaderParameter(pathReggaeOne) { size = 60 })
        override val font_64 = FontTTFData("ReggaeOne_64", getLoaderParameter(pathReggaeOne) { size = 64 })
        override val font_70 = FontTTFData("ReggaeOne_70", getLoaderParameter(pathReggaeOne) { size = 70 })

        val font_120 = FontTTFData("ReggaeOne_120", getLoaderParameter(pathReggaeOne) { size = 120 })
        val font_300 = FontTTFData("ReggaeOne_300", getLoaderParameter(pathReggaeOne) { size = 300 })

        override val values: List<FontTTFData>
            get() = super.values + font_120 + font_300
    }

    object NotoSansFont: IFont {
        override val font_40  = FontTTFData("NotoSans_40", getLoaderParameter(pathNotoSans) { size = 40 })
        override val font_50  = FontTTFData("NotoSans_50", getLoaderParameter(pathNotoSans) { size = 50 })
        override val font_60  = FontTTFData("NotoSans_60", getLoaderParameter(pathNotoSans) { size = 60 })
        override val font_64  = FontTTFData("NotoSans_64", getLoaderParameter(pathNotoSans) { size = 64 })
        override val font_70  = FontTTFData("NotoSans_70", getLoaderParameter(pathNotoSans) { size = 70 })
    }



    interface IFont {
        val font_40: FontTTFData
        val font_50: FontTTFData
        val font_60: FontTTFData
        val font_64: FontTTFData
        val font_70: FontTTFData

        val values get() = listOf<FontTTFData>(font_40, font_50, font_60, font_64, font_70)
    }
}