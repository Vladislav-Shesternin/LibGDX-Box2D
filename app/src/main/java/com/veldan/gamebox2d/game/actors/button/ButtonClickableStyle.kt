package com.veldan.gamebox2d.game.actors.button

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.veldan.gamebox2d.game.manager.assets.SpriteManager

data class ButtonClickableStyle(
    val default : TextureRegion,
    val pressed : TextureRegion,
    val disabled: TextureRegion? = null,
) {
    
    companion object {
        val btn get() = ButtonClickableStyle(
            default = SpriteManager.GameRegion.BTN_DEFF.region,
            pressed = SpriteManager.GameRegion.BTN_PRESS.region,
        )
    }
    
}