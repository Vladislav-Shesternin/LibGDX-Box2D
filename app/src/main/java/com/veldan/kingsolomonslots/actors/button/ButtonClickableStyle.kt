package com.veldan.kingsolomonslots.actors.button

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.veldan.kingsolomonslots.manager.assets.SpriteManager

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
    }
    
}