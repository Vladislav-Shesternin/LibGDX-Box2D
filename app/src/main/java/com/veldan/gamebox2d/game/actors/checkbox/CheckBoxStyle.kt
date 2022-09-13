package com.veldan.gamebox2d.game.actors.checkbox

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.veldan.gamebox2d.game.manager.assets.SpriteManager

data class CheckBoxStyle(
    val default: TextureRegion,
    val checked: TextureRegion,
) {
    companion object {
        val box get() = CheckBoxStyle(
            default = SpriteManager.GameRegion.DEFF.region,
            checked = SpriteManager.GameRegion.CHECK.region,
        )
    }
}