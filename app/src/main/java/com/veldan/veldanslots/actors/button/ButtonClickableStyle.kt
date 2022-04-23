package com.veldan.veldanslots.actors.button

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.veldan.veldanslots.manager.assets.SpriteManager

data class ButtonClickableStyle(
    val default : TextureRegion,
    val pressed : TextureRegion,
    val disabled: TextureRegion? = null,
) {
    
    companion object {
        val options get() = ButtonClickableStyle(
            default = SpriteManager.GameRegion.OPTIONS_DEF.region,
            pressed = SpriteManager.GameRegion.OPTIONS_PRESS.region,
        )
        val back get() = ButtonClickableStyle(
            default = SpriteManager.OptionsRegion.BACK_DEF.region,
            pressed = SpriteManager.OptionsRegion.BACK_PRESS.region,
        )
        val plus get() = ButtonClickableStyle(
            default  = SpriteManager.GameRegion.PLUS_DEF.region,
            pressed  = SpriteManager.GameRegion.PLUS_PRESS.region,
            disabled = SpriteManager.GameRegion.PLUS_DIS.region,
        )
        val minus get() = ButtonClickableStyle(
            default  = SpriteManager.GameRegion.MINUS_DEF.region,
            pressed  = SpriteManager.GameRegion.MINUS_PRESS.region,
            disabled = SpriteManager.GameRegion.MINUS_DIS.region,
        )
        val autoSpin get() = ButtonClickableStyle(
            default  = SpriteManager.GameRegion.AUTOSPIN_DEF.region,
            pressed  = SpriteManager.GameRegion.AUTOSPIN_PRESS.region,
            disabled = SpriteManager.GameRegion.AUTOSPIN_DIS.region,
        )
        val spin get() = ButtonClickableStyle(
            default  = SpriteManager.GameRegion.SPIN_DEF.region,
            pressed  = SpriteManager.GameRegion.SPIN_PRESS.region,
            disabled = SpriteManager.GameRegion.SPIN_DIS.region,
        )
        val shop get() = ButtonClickableStyle(
            default = SpriteManager.GameRegion.SHOP_DEF.region,
            pressed = SpriteManager.GameRegion.SHOP_PRESS.region,
        )
        val shop_mini get() = ButtonClickableStyle(
            default = SpriteManager.ShopRegion.MINI_DEF.region,
            pressed = SpriteManager.ShopRegion.MINI_PRESS.region,
        )
        val shop_super get() = ButtonClickableStyle(
            default = SpriteManager.ShopRegion.SUPER_DEF.region,
            pressed = SpriteManager.ShopRegion.SUPER_PRESS.region,
        )
        val shop_mega get() = ButtonClickableStyle(
            default = SpriteManager.ShopRegion.MEGA_DEF.region,
            pressed = SpriteManager.ShopRegion.MEGA_PRESS.region,
        )
        val free get() = ButtonClickableStyle(
            default = SpriteManager.ShopRegion.FREE_DEF.region,
            pressed = SpriteManager.ShopRegion.FREE_PRESS.region,
        )
    }
    
}