package com.veldan.gamebox2d.game.actors.checkbox

import com.badlogic.gdx.graphics.g2d.TextureRegion

data class CheckBoxStyle(
    val default: TextureRegion,
    val checked: TextureRegion,
) {
    companion object {
//        val timer get() = CheckBoxStyle(
//            default = SpriteManager.WorkoutRegion.TIME_DEFF.region,
//            checked = SpriteManager.WorkoutRegion.TIME_CHECK.region,
//        )
    }
}