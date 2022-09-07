package com.veldan.gamebox2d.game.manager.assets

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver
import com.badlogic.gdx.graphics.Texture.TextureFilter
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader
import com.veldan.gamebox2d.game.utils.language.Language

object FontTTFManager {

    private const val pathNotoSans = "font/TTF/NotoSans.ttf"

    private val resolverInternal = InternalFileHandleResolver()

    var loadListFont = mutableListOf<FontTTFData>()

//    val fontText: IFont get() = when(Language.locale.language) {
//        "ru" -> NotoSansFont
//        else -> FontTTFManager.ReggaeOneFont
//    }



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



    object NotoSansFont: IFont {
        override val font_40 = FontTTFData("NotoSans_40", getLoaderParameter(pathNotoSans) { size = 40 })
    }



    interface IFont {
        val font_40: FontTTFData

        val values get() = listOf<FontTTFData>(font_40)
    }

    data class FontTTFData(
        val name: String,
        val parameters: FreetypeFontLoader.FreeTypeFontLoaderParameter,
    ) {
        lateinit var font: BitmapFont
    }
}