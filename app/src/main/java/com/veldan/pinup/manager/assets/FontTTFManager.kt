package com.veldan.pinup.manager.assets

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader
import com.veldan.pinup.manager.assets.util.FontTTFData

object FontTTFManager {

    private const val pathAmarante = "font/TTF/Amarante.ttf"
    private const val pathNotoSans = "font/TTF/NotoSans.ttf"

    private val resolverInternal = InternalFileHandleResolver()

    var loadListFont = mutableListOf<FontTTFData>()



    private fun AssetManager.setLoaderTTF() {
        setLoader(FreeTypeFontGenerator::class.java, FreeTypeFontGeneratorLoader(resolverInternal))
        setLoader(BitmapFont::class.java, ".ttf", FreetypeFontLoader(resolverInternal))
    }

    private fun getLoaderParameter(
        fileName: String,
        parameters: FreeTypeFontGenerator.FreeTypeFontParameter.() -> Unit = { }
    ) = FreetypeFontLoader.FreeTypeFontLoaderParameter().apply {
        fontFileName = fileName
        fontParameters.apply {
            minFilter = com.badlogic.gdx.graphics.Texture.TextureFilter.Linear
            magFilter = com.badlogic.gdx.graphics.Texture.TextureFilter.Linear
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



    object AmaranteFont: IFont {
        override val white_92 = FontTTFData("Amarante_92", getLoaderParameter(pathAmarante) {
            size = 92
        })
        override val white_96 = FontTTFData("Amarante_96", getLoaderParameter(pathAmarante) {
            size = 96
        })
    }

    object NotoSansFont: IFont {
        override val white_92 = FontTTFData("NotoSans_92", getLoaderParameter(pathNotoSans) {
            size = 92
        })
        override val white_96 = FontTTFData("NotoSans_96", getLoaderParameter(pathNotoSans) {
            size = 96
        })
    }



    interface IFont {
        val white_92: FontTTFData
        val white_96: FontTTFData

        val values get() = listOf<FontTTFData>(white_92, white_96)
    }
}