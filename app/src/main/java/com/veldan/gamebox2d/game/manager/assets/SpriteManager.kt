package com.veldan.gamebox2d.game.manager.assets

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.TextureLoader
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.veldan.gamebox2d.game.utils.region

object SpriteManager {

    private val parameter = TextureLoader.TextureParameter().apply {
        minFilter = Texture.TextureFilter.Linear
        magFilter = Texture.TextureFilter.Linear
    }

    var loadableAtlasList   = mutableListOf<IAtlas>()
    var loadableTextureList = mutableListOf<ITexture>()



    fun load(assetManager: AssetManager) {
        loadableAtlasList.onEach { assetManager.load(it.data.path, TextureAtlas::class.java) }
        loadableTextureList.onEach { assetManager.load(it.data.path, Texture::class.java, parameter) }
    }

    fun init(assetManager: AssetManager) {
        loadableAtlasList.onEach { it.data.atlas = assetManager[it.data.path, TextureAtlas::class.java] }
        loadableTextureList.onEach { it.data.texture = assetManager[it.data.path, Texture::class.java] }
    }



    enum class EnumAtlas(override val data: TextureAtlasData): IAtlas {
        _1(TextureAtlasData("sprites/atlas/1.atlas")),
    }

    enum class EnumTexture(override val data: TextureData): ITexture {
        BACKGROUND(TextureData("sprites/background/1.png")),
    }



    enum class GameRegion(override val region: TextureRegion): IRegion {
        BACKGROUND(EnumTexture.BACKGROUND.data.texture.region),

        BTN_DEFF( EnumAtlas._1.data.atlas.findRegion("btn_deff") ),
        BTN_PRESS(EnumAtlas._1.data.atlas.findRegion("btn_press")),
        BORDERS(  EnumAtlas._1.data.atlas.findRegion("borders")  ),

        BOX(      EnumAtlas._1.data.atlas.findRegion("box")  ),
        CHECK(    EnumAtlas._1.data.atlas.findRegion("check")),
        DEFF(     EnumAtlas._1.data.atlas.findRegion("deff") ),
    }



    enum class ListRegion(override val regionList: List<TextureRegion>): IRegionList {
        //SLOT_ITEM(List(8) { EnumAtlas.SLOT_ITEM.data.atlas.findRegion("${it.inc()}") })
    }



    interface IAtlas {
        val data: TextureAtlasData
    }

    interface ITexture {
        val data: TextureData
    }

    interface IRegion {
        val region: TextureRegion
    }

    interface IRegionList {
        val regionList: List<TextureRegion>
    }

    data class TextureAtlasData(
        val path: String,
    ) {
        lateinit var atlas: TextureAtlas
    }

    data class TextureData(
        val path: String,
    ) {
        lateinit var texture: Texture
    }

}