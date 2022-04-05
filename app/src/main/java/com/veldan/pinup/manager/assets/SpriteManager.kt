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
        BACKGROUND(    TextureData("sprites/background/1.png")  ),
        BUTTON_DEF_1(  TextureData("sprites/button/def_1.png")  ),
        BUTTON_PRESS_1(TextureData("sprites/button/press_1.png")),
    }

    enum class OptionsSprite(override val data: TextureData): ISprite {
        BACKGROUND(             TextureData("sprites/background/1.png")          ),
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
//
//    enum class GameSprite(override val data: TextureData): ISprite {
//        BACKGROUND(                 TextureData("sprites/background/1.png")                   ),
//        AUTOSPIN_DEF(               TextureData("sprites/button/autospin_def.png")            ),
//        AUTOSPIN_DIS(               TextureData("sprites/button/autospin_dis.png")            ),
//        AUTOSPIN_PRESS(             TextureData("sprites/button/autospin_press.png")          ),
//        BALANCE_PANEL(              TextureData("sprites/balance_panel.png")                  ),
//        BET_PANEL(                  TextureData("sprites/bet_panel.png")                      ),
//        MENU_DEF(                   TextureData("sprites/button/menu_def.png")                ),
//        MENU_PRESS(                 TextureData("sprites/button/menu_press.png")              ),
//        MINUS_DIS(                  TextureData("sprites/button/minus_dis.png")               ),
//        MINUS_PRESS(                TextureData("sprites/button/minus_press.png")             ),
//        MINUS_DEF(                  TextureData("sprites/button/minus_def.png")               ),
//        PLUS_DEF(                   TextureData("sprites/button/plus_def.png")                ),
//        PLUS_DIS(                   TextureData("sprites/button/plus_dis.png")                ),
//        PLUS_PRESS(                 TextureData("sprites/button/plus_press.png")              ),
//        SCATTER(                    TextureData("sprites/scatter.png")                        ),
//        SEPARATOR(                  TextureData("sprites/separator.png")                      ),
//        SLOT_PANEL(                 TextureData("sprites/slot_panel.png")                     ),
//        SPIN_DEF(                   TextureData("sprites/button/spin_def.png")                ),
//        SPIN_DIS(                   TextureData("sprites/button/spin_dis.png")                ),
//        SPIN_PRESS(                 TextureData("sprites/button/spin_press.png")              ),
//        WILD(                       TextureData("sprites/wild.png")                           ),
//        SLOT_GROUP_MASK(            TextureData("sprites/slot_group_mask.png")                ),
//        GLOW(                       TextureData("sprites/glow.png")                           ),
//        ROULETTE(                   TextureData("sprites/roulette.png")                       ),
//        INDICATOR(                  TextureData("sprites/indicator.png")                      ),
//        EQUALLY_WILD(               TextureData("sprites/equally_wild.png")                   ),
//        DIALOG_PANEL(               TextureData("sprites/dialog_panel.png")                   ),
//        DONE_DEF(                   TextureData("sprites/button/done_def.png")                ),
//        DONE_PRESS(                 TextureData("sprites/button/done_press.png")              ),
//        BACK_ARROW(                 TextureData("sprites/back_arrow.png")                     ),
//        BALANCE_DEF(                TextureData("sprites/button/balance_def.png")             ),
//        BALANCE_PRESS(              TextureData("sprites/button/balance_press.png")           ),
//        STARTING_BALANCE_BACKGROUND(TextureData("sprites/background/2.png")                   ),
//        TRAINING_BACKGROUND(        TextureData("sprites/background/2.png")                   ),
//        NEXT_ARROW_DEF(             TextureData("sprites/tutorial/next_arrow_def.png")        ),
//        NEXT_ARROW_PRESS(           TextureData("sprites/tutorial/next_arrow_press.png")      ),
//        FRAME_AUTOSPIN(             TextureData("sprites/tutorial/frame/frame_autospin.png")  ),
//        FRAME_BET(                  TextureData("sprites/tutorial/frame/frame_bet.png")       ),
//        FRAME_BALANCE(              TextureData("sprites/tutorial/frame/frame_balance.png")   ),
//        FRAME_MENU(                 TextureData("sprites/tutorial/frame/frame_menu.png")      ),
//        FRAME_PLUS_MINUS(           TextureData("sprites/tutorial/frame/frame_plus_minus.png")),
//        FRAME_SLOT(                 TextureData("sprites/tutorial/frame/frame_slot.png")      ),
//        FRAME_SLOT_ITEM(            TextureData("sprites/tutorial/frame/frame_slot_item.png") ),
//        FRAME_SPIN(                 TextureData("sprites/tutorial/frame/frame_spin.png")      ),
//        FRAME_WIN(                  TextureData("sprites/tutorial/frame/frame_win.png")       ),
//        DIALOG_1(                   TextureData("sprites/tutorial/dialog/dialog_1.png")       ),
//        DIALOG_2(                   TextureData("sprites/tutorial/dialog/dialog_2.png")       ),
//        SLOT_PANEL_TUTORIAL(        TextureData("sprites/slot_panel_tutorial.png")            ),
//        GLOW_TUTORIAL(              TextureData("sprites/glow_tutorial.png")                  ),
//        TUTORIAL_SKIP(              TextureData("sprites/tutorial_skip.png")                  ),
//    }
//
//
//
//    enum class SlotItemSpriteList(override val dataList: List<TextureData>): ISpriteList {
//        SLOT_ITEM_LIST(List(10) { TextureData("sprites/list/slot_item/${it.inc()}.png") })
//    }



    interface ISprite {
        val data: TextureData
    }

    interface ISpriteList {
        val dataList: List<TextureData>
    }

}