package com.veldan.veldanslots.actors.bonusGameGroup.miniGame.boxGroup.util

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.veldan.veldanslots.manager.assets.SpriteManager

enum class BoxPrize(
    val region: TextureRegion,
    val prize : Int,
) {
    WIN( SpriteManager.GameRegion.WIN_20.region, 20),
    FAIL(SpriteManager.GameRegion.X.region     , 0),
}