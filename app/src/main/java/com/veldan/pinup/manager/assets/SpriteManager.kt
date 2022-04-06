package com.veldan.pinup.manager.assets

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.TextureLoader
import com.badlogic.gdx.graphics.Texture
import com.veldan.pinup.manager.assets.util.TextureData

object SpriteManager {

    var loadListSprite = mutableListOf<ISprite>()
    var loadListSpriteList = mutableListOf<ISpriteList>()

    private val parameter = TextureLoader.TextureParameter().apply {
        minFilter = Texture.TextureFilter.Linear
        magFilter = Texture.TextureFilter.Linear
    }



    fun load(assetManager: AssetManager) {
        loadListSprite.onEach { assetManager.load(it.data.path, Texture::class.java, parameter) }
        loadListSpriteList.onEach { listTextureData -> listTextureData.dataList.onEach { textureData -> assetManager.load(textureData.path, Texture::class.java, parameter) } }
    }

    fun init(assetManager: AssetManager) {
        loadListSprite.onEach { it.data.texture = assetManager[it.data.path, Texture::class.java] }
        loadListSpriteList.onEach { listTextureData -> listTextureData.dataList.onEach { textureData -> textureData.texture = assetManager[textureData.path, Texture::class.java] } }
    }



    enum class SplashSprite(override val data: TextureData): ISprite {
        BACKGROUND(TextureData("sprites/background/1.png")),
    }

    enum class MenuSprite(override val data: TextureData): ISprite {
        BACKGROUND(    TextureData("sprites/background/2.png")  ),
        BUTTON_DEF_1(  TextureData("sprites/button/def_1.png")  ),
        BUTTON_PRESS_1(TextureData("sprites/button/press_1.png")),
    }

    enum class OptionsSprite(override val data: TextureData): ISprite {
        BACKGROUND(             TextureData("sprites/background/2.png")          ),
        BACK_DEF(               TextureData("sprites/button/back_def.png")       ),
        BACK_PRESS(             TextureData("sprites/button/back_press.png")     ),
        CHECK_BOX_DEF_1(        TextureData("sprites/checkBox/def_1.png")        ),
        CHECK_BOX_CHECK_1(      TextureData("sprites/checkBox/check_1.png")      ),
        PROGRESS_BAR_BORDERS(   TextureData("sprites/progressBar/borders.png")   ),
        PROGRESS_BAR_CONTROLLER(TextureData("sprites/progressBar/controller.png")),
        PROGRESS_BAR_PANEL(     TextureData("sprites/progressBar/panel.png")     ),
        PROGRESS_BAR_PROGRESS(  TextureData("sprites/progressBar/progress.png")  ),
        MUSIC(                  TextureData("sprites/music.png")                 ),
        SOUND(                  TextureData("sprites/sound.png")                 ),
        RU(                     TextureData("sprites/ru.png")                    ),
        UK(                     TextureData("sprites/uk.png")                    ),
        US(                     TextureData("sprites/us.png")                    ),
    }

    enum class GameSprite(override val data: TextureData): ISprite {
        BACKGROUND(      TextureData("sprites/background/2.png")          ),
        BALANCE_PANEL(   TextureData("sprites/balance_panel.png")         ),
        BET_PANEL(       TextureData("sprites/bet_panel.png")             ),
        GLOW(            TextureData("sprites/glow.png")                  ),
        SLOT_GROUP_PANEL(TextureData("sprites/slot_group_panel.png")      ),
        WILD(            TextureData("sprites/wild.png")                  ),
        AUTO_SPIN_DEF(   TextureData("sprites/button/auto_spin_def.png")  ),
        AUTO_SPIN_DIS(   TextureData("sprites/button/auto_spin_dis.png")  ),
        AUTO_SPIN_PRESS( TextureData("sprites/button/auto_spin_press.png")),
        MENU_DEF(        TextureData("sprites/button/menu_def.png")       ),
        PLUS_DEF(        TextureData("sprites/button/plus_def.png")       ),
        MINUS_DEF(       TextureData("sprites/button/minus_def.png")      ),
        PLUS_PRESS(      TextureData("sprites/button/plus_press.png")     ),
        PLUS_DIS(        TextureData("sprites/button/plus_dis.png")       ),
        MENU_PRESS(      TextureData("sprites/button/menu_press.png")     ),
        MINUS_PRESS(     TextureData("sprites/button/minus_press.png")    ),
        MINUS_DIS(       TextureData("sprites/button/minus_dis.png")      ),
        SPIN_DEF(        TextureData("sprites/button/spin_def.png")       ),
        SPIN_PRESS(      TextureData("sprites/button/spin_press.png")     ),
        SPIN_DIS(        TextureData("sprites/button/spin_dis.png")       ),
    }

    enum class SpriteList(override val dataList: List<TextureData>): ISpriteList {
        SLOT_ITEM(List(8) { TextureData("sprites/list/slot_item/${it.inc()}.png") })
    }



    interface ISprite {
        val data: TextureData
    }

    interface ISpriteList {
        val dataList: List<TextureData>
    }

}