package com.veldan.gamebox2d.game.box2d.bodies.box

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.veldan.gamebox2d.game.actors.checkbox.CheckBoxGroup
import com.veldan.gamebox2d.game.actors.label.LabelStyle
import com.veldan.gamebox2d.game.box2d.bodies.AbstractBody
import com.veldan.gamebox2d.game.box2d.bodies.box.State.*
import com.veldan.gamebox2d.utils.log
import kotlin.math.max
import kotlin.math.min

class Box(
    text         : String?        = null,
    checkBoxGroup: CheckBoxGroup? = null,
): AbstractBody() {

    override val name       = "Box"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.DynamicBody
    }
    override val fixtureDef = FixtureDef().apply {
        density     = 1f
        restitution = 0.5f
    }
    override val actor      = ABox(text, LabelStyle.inter_Black_12, checkBoxGroup)

    var state = STOP



    override fun render() {
        super.render()

        body.linearVelocity.also { velocity ->
            when (state) {
                STOP  -> velocity.x * 0.98f
                LEFT  -> max(velocity.x - 0.1f, -5f)
                RIGHT -> min(velocity.x + 0.1f, 5f)
            }.also {
                body.linearVelocity = velocity.apply { x = it }
            }
        }
    }

}