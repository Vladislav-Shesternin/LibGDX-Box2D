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
    }
    
}