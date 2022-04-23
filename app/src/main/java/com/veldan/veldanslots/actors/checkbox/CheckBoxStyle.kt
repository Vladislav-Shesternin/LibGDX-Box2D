package com.veldan.veldanslots.actors.checkbox

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.veldan.veldanslots.manager.assets.SpriteManager

data class CheckBoxStyle(
    val default: TextureRegion,
    val checked: TextureRegion,
) {
    companion object {
        val gold get() = CheckBoxStyle(
            default = SpriteManager.OptionsRegion.CHECK_BOX_DEF.region,
            checked = SpriteManager.OptionsRegion.CHECK_BOX_CHECK.region,
        )
    }
}