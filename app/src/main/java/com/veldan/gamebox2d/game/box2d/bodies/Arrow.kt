package com.veldan.gamebox2d.game.box2d.bodies

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.veldan.gamebox2d.game.manager.assets.SpriteManager
import com.veldan.gamebox2d.game.utils.advanced.AdvancedGroup

class Arrow : AbstractBody() {

    override val name       = "Arrow"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.DynamicBody
    }
    override val fixtureDef = FixtureDef().apply {
        density = 1f
    }

    override val actor = AdvancedGroup()



    override fun AdvancedGroup.addActorsOnGroup() {
        addAndFillActor(Image(SpriteManager.GameRegion.ARROW.region))
    }

}