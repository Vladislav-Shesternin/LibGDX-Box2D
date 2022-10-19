package com.veldan.gamebox2d.game.manager.assets

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.TextureLoader
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.veldan.gamebox2d.game.utils.region

object SpriteManager {

    var loadableAtlasList   = mutableListOf<IAtlas>()


    fun load(assetManager: AssetManager) {
        loadableAtlasList.onEach { assetManager.load(it.data.path, TextureAtlas::class.java) }
    }

    fun init(assetManager: AssetManager) {
        loadableAtlasList.onEach { it.data.atlas = assetManager[it.data.path, TextureAtlas::class.java] }
    }



    enum class EnumAtlas(override val data: TextureAtlasData): IAtlas {
        _1(TextureAtlasData("atlas/1.atlas")),
    }

    enum class GameRegion(override val region: TextureRegion): IRegion {
        BACKGROUND(EnumAtlas._1.data.atlas.findRegion("background")),
        BTN_DEFF(  EnumAtlas._1.data.atlas.findRegion("btn_deff")  ),
        BTN_PRESS( EnumAtlas._1.data.atlas.findRegion("btn_press") ),
        BORDERS(   EnumAtlas._1.data.atlas.findRegion("borders")   ),
    }

    enum class ListRegion(override val regionList: List<TextureRegion>): IRegionList {
        //SLOT_ITEM(List(8) { EnumAtlas.SLOT_ITEM.data.atlas.findRegion("${it.inc()}") })
    }



    interface IAtlas {
        val data: TextureAtlasData
    }

    data class TextureAtlasData(
        val path: String,
    ) {
        lateinit var atlas: TextureAtlas
    }

    interface IRegion {
        val region: TextureRegion
    }

    interface IRegionList {
        val regionList: List<TextureRegion>
    }

}