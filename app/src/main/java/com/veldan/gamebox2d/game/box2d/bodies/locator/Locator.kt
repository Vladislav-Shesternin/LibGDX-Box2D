package com.veldan.gamebox2d.game.box2d.bodies.locator

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.veldan.gamebox2d.game.box2d.bodies.AbstractBody
import com.veldan.gamebox2d.game.box2d.bodies.BodyId
import com.veldan.gamebox2d.game.box2d.bodies.Collision

class Locator: AbstractBody() {
    override var id            = BodyId.LOCATOR
    override val name          = "Circle"
    override val bodyDef       = BodyDef().apply {
        type = BodyDef.BodyType.StaticBody
    }
    override val fixtureDef    = FixtureDef().apply {
        isSensor = true
    }
    override val collisionList = mutableListOf(BodyId.CAR)

    override val actor = ALocator()

    private var enemyCount = 0



    override fun beginContact(contactBody: AbstractBody) {
        actor.discovered()
        enemyCount++
    }

    override fun endContact(contactBody: AbstractBody) {
        if (--enemyCount == 0) actor.quietly()
    }

}