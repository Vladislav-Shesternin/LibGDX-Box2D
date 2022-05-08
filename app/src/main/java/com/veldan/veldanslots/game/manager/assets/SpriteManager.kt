package com.veldan.veldanslots.game.manager.assets

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.TextureLoader
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.veldan.veldanslots.game.manager.assets.util.TextureAtlasData
import com.veldan.veldanslots.game.manager.assets.util.TextureData
import com.veldan.veldanslots.game.utils.region

object SpriteManager {

    private val parameter = TextureLoader.TextureParameter().apply {
        minFilter = Texture.TextureFilter.Linear
        magFilter = Texture.TextureFilter.Linear
    }

    var loadableAtlasList       = mutableListOf<IAtlas>()
    var loadableTextureList     = mutableListOf<ITexture>()



    fun load(assetManager: AssetManager) {
        loadableAtlasList.onEach { assetManager.load(it.data.path, TextureAtlas::class.java) }
        loadableTextureList.onEach { assetManager.load(it.data.path, Texture::class.java) }
    }

    fun init(assetManager: AssetManager) {
        loadableAtlasList.onEach { it.data.atlas = assetManager[it.data.path, TextureAtlas::class.java] }
        loadableTextureList.onEach { it.data.texture = assetManager[it.data.path, Texture::class.java] }
    }



    enum class EnumAtlas(override val data: TextureAtlasData): IAtlas {
        SPLASH(   TextureAtlasData("sprites/atlas/splash.atlas")   ),
        _1(       TextureAtlasData("sprites/atlas/1.atlas")        ),
        SHOP_ITEM(TextureAtlasData("sprites/atlas/shop_item.atlas")),
        SLOT_ITEM(TextureAtlasData("sprites/atlas/slot_item.atlas")),
        TUTORIAL( TextureAtlasData("sprites/atlas/tutorial.atlas") ),
    }

    enum class EnumTexture(override val data: TextureData): ITexture {
        BACKGROUND(TextureData("sprites/background/1.png")),
    }


    enum class SplashRegion(override val region: TextureRegion): IRegion {
        BACKGROUND(EnumTexture.BACKGROUND.data.texture.region      ),
        PANEL(EnumAtlas.SPLASH.data.atlas.findRegion("panel")),
    }

    enum class GameRegion(override val region: TextureRegion): IRegion {
        BACKGROUND(        EnumTexture.BACKGROUND.data.texture.region                    ),
        OPTIONS_DEF(       EnumAtlas._1.data.atlas.findRegion("options_def")       ),
        OPTIONS_PRESS(     EnumAtlas._1.data.atlas.findRegion("options_press")     ),
        AUTOSPIN_DEF(      EnumAtlas._1.data.atlas.findRegion("autospin_def")      ),
        AUTOSPIN_DIS(      EnumAtlas._1.data.atlas.findRegion("autospin_dis")      ),
        AUTOSPIN_PRESS(    EnumAtlas._1.data.atlas.findRegion("autospin_press")    ),
        BALANCE_PANEL(     EnumAtlas._1.data.atlas.findRegion("balance_panel")     ),
        BET_PANEL(         EnumAtlas._1.data.atlas.findRegion("bet_panel")         ),
        GLOW(              EnumAtlas._1.data.atlas.findRegion("glow")              ),
        MINUS_DEF(         EnumAtlas._1.data.atlas.findRegion("minus_def")         ),
        MINUS_DIS(         EnumAtlas._1.data.atlas.findRegion("minus_dis")         ),
        MINUS_PRESS(       EnumAtlas._1.data.atlas.findRegion("minus_press")       ),
        PLUS_DEF(          EnumAtlas._1.data.atlas.findRegion("plus_def")          ),
        PLUS_DIS(          EnumAtlas._1.data.atlas.findRegion("plus_dis")          ),
        PLUS_PRESS(        EnumAtlas._1.data.atlas.findRegion("plus_press")        ),
        SLOT_GROUP_IMAGE(  EnumAtlas._1.data.atlas.findRegion("slot_group_image")  ),
        SLOT_GROUP_MASK(   EnumAtlas._1.data.atlas.findRegion("slot_group_mask")   ),
        SPIN_DEF(          EnumAtlas._1.data.atlas.findRegion("spin_def")          ),
        SPIN_DIS(          EnumAtlas._1.data.atlas.findRegion("spin_dis")          ),
        SPIN_PRESS(        EnumAtlas._1.data.atlas.findRegion("spin_press")        ),
        BOX(               EnumAtlas._1.data.atlas.findRegion("box")               ),
        WIN_20(            EnumAtlas._1.data.atlas.findRegion("win 20")            ),
        X(                 EnumAtlas._1.data.atlas.findRegion("x")                 ),
        EQUALS(            EnumAtlas._1.data.atlas.findRegion("equals")            ),
        BIG_BALANCE_PANEL( EnumAtlas._1.data.atlas.findRegion("big_balance_panel") ),
        BIG_CIRCLE_PANEL(  EnumAtlas._1.data.atlas.findRegion("big_circle_panel")  ),
        SMALL_CIRCLE_PANEL(EnumAtlas._1.data.atlas.findRegion("small_circle_panel")),
        SHOP_DEF(          EnumAtlas._1.data.atlas.findRegion("shop_def")          ),
        SHOP_PRESS(        EnumAtlas._1.data.atlas.findRegion("shop_press")        ),

        SCATTER(         EnumAtlas.SLOT_ITEM.data.atlas.findRegion("scatter")      ),
        WILD(            EnumAtlas.SLOT_ITEM.data.atlas.findRegion("wild")         ),
    }

    enum class OptionsRegion(override val region: TextureRegion): IRegion {
        BACKGROUND(      EnumTexture.BACKGROUND.data.texture.region                  ),
        PB_CONTROLLER(   EnumAtlas._1.data.atlas.findRegion("pb_controller")   ),
        PB_PANEL(        EnumAtlas._1.data.atlas.findRegion("pb_panel")        ),
        PB_PROGRESS(     EnumAtlas._1.data.atlas.findRegion("pb_progress")     ),
        PB_PROGRESS_MASK(EnumAtlas._1.data.atlas.findRegion("pb_progress_mask")),
        BACK_DEF(        EnumAtlas._1.data.atlas.findRegion("back_def")        ),
        BACK_PRESS(      EnumAtlas._1.data.atlas.findRegion("back_press")      ),
        CHECK_BOX_CHECK( EnumAtlas._1.data.atlas.findRegion("check_box_check") ),
        CHECK_BOX_DEF(   EnumAtlas._1.data.atlas.findRegion("check_box_def")   ),
        MUSIC(           EnumAtlas._1.data.atlas.findRegion("music")           ),
        SOUND(           EnumAtlas._1.data.atlas.findRegion("sound")           ),
        TUTORIAL(        EnumAtlas._1.data.atlas.findRegion("tutorial")        ),
    }

    enum class ShopRegion(override val region: TextureRegion): IRegion {
        MEGA_DEF(   EnumAtlas.SHOP_ITEM.data.atlas.findRegion("mega_def")   ),
        MEGA_PRESS( EnumAtlas.SHOP_ITEM.data.atlas.findRegion("mega_press") ),
        MINI_DEF(   EnumAtlas.SHOP_ITEM.data.atlas.findRegion("mini_def")   ),
        MINI_PRESS( EnumAtlas.SHOP_ITEM.data.atlas.findRegion("mini_press") ),
        SUPER_PRESS(EnumAtlas.SHOP_ITEM.data.atlas.findRegion("super_press")),
        SUPER_DEF(  EnumAtlas.SHOP_ITEM.data.atlas.findRegion("super_def")  ),
        FREE_DEF(   EnumAtlas.SHOP_ITEM.data.atlas.findRegion("free_def")   ),
        FREE_PRESS( EnumAtlas.SHOP_ITEM.data.atlas.findRegion("free_press") ),
    }

    enum class TutorialRegion(override val region: TextureRegion):IRegion {
        SKIP(         EnumAtlas.TUTORIAL.data.atlas.findRegion("skip")         ),

        TD_BALANCE(   EnumAtlas.TUTORIAL.data.atlas.findRegion("td_balance")   ),
        TD_SLOT_GROUP(EnumAtlas.TUTORIAL.data.atlas.findRegion("td_slotGroup") ),

        TF_BALANCE(   EnumAtlas.TUTORIAL.data.atlas.findRegion("tf_balance")   ),
        TF_BET(       EnumAtlas.TUTORIAL.data.atlas.findRegion("tf_bet")       ),
        TF_PLUS_MINUS(EnumAtlas.TUTORIAL.data.atlas.findRegion("tf_plus_minus")),
        TF_SLOT_GROUP(EnumAtlas.TUTORIAL.data.atlas.findRegion("tf_slotGroup") ),
        TF_SPIN(      EnumAtlas.TUTORIAL.data.atlas.findRegion("tf_spin")      ),
    }

    enum class ListRegion(override val regionList: List<TextureRegion>): IRegionList {
        SLOT_ITEM(List(8) { EnumAtlas.SLOT_ITEM.data.atlas.findRegion("${it.inc()}") })
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

}