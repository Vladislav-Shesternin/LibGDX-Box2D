package com.veldan.gamebox2d.game.box2d.bodies

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.veldan.gamebox2d.game.box2d.WorldUtil
import com.veldan.gamebox2d.game.manager.assets.SpriteManager
import com.veldan.gamebox2d.game.utils.LayoutUtil
import com.veldan.gamebox2d.game.utils.advanced.AdvancedStage
import com.veldan.gamebox2d.utils.log

class Borders(
    override val width     : Float,
    override val height    : Float,
    override val layoutUtil: LayoutUtil,
    override val stage     : AdvancedStage,
): AbstractBody(width, height, layoutUtil, stage) {

    override val name       = "Borders"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.StaticBody
    }
    override val fixtureDef = FixtureDef()
    override val image      = Image(SpriteManager.GameRegion.BORDERS.region)



    override fun render() {
        super.render()
        log("""
            borders - $name    
            scale - $scale
            body - $body
            center - $center
        """.trimIndent())
    }
}