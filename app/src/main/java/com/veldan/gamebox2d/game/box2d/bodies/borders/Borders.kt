package com.veldan.gamebox2d.game.box2d.bodies.borders

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.veldan.gamebox2d.game.box2d.bodies.AbstractBody
import com.veldan.gamebox2d.game.box2d.bodies.BodyId
import com.veldan.gamebox2d.game.box2d.bodies.BodyId.*
import com.veldan.gamebox2d.game.box2d.bodies.Collision
import com.veldan.gamebox2d.game.manager.assets.SpriteManager
import com.veldan.gamebox2d.game.utils.advanced.AdvancedGroup
import kotlin.experimental.or

class Borders: AbstractBody() {
    override var id            = BORDERS
    override val name          = "Borders"
    override val bodyDef       = BodyDef().apply {
        type = BodyDef.BodyType.StaticBody
    }
    override val fixtureDef    = FixtureDef()
    override val collisionList = mutableListOf(CAR, CAR_2)

    override val actor      = AdvancedGroup()



    override fun AdvancedGroup.addActorsOnGroup() {
        addAndFillActor(Image(SpriteManager.GameRegion.BORDERS.region))
    }

}