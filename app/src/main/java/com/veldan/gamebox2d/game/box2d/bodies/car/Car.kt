package com.veldan.gamebox2d.game.box2d.bodies.car

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.CircleShape
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.veldan.gamebox2d.game.actors.checkbox.CheckBox
import com.veldan.gamebox2d.game.actors.checkbox.CheckBoxGroup
import com.veldan.gamebox2d.game.actors.checkbox.CheckBoxStyle
import com.veldan.gamebox2d.game.box2d.bodies.AbstractBody
import com.veldan.gamebox2d.game.box2d.bodies.BodyId
import com.veldan.gamebox2d.game.box2d.bodies.BodyId.*
import com.veldan.gamebox2d.game.box2d.bodies.Collision
import kotlinx.coroutines.flow.MutableStateFlow
import kotlin.experimental.or
import kotlin.math.max
import kotlin.math.min

private val cb = CheckBoxGroup()

class Car: AbstractBody() {
    override var id            = CAR
    override val name          = "Circle"
    override val bodyDef       = BodyDef().apply {
        type = BodyDef.BodyType.DynamicBody
    }
    override val fixtureDef    = FixtureDef().apply {
        density = 1f
        restitution = 0.5f
    }
    override val collisionList = mutableListOf(CAR, BORDERS, LOCATOR)

    override val actor         = CheckBox(CheckBoxStyle.car).apply { checkBoxGroup = cb }

    val stateFlow = MutableStateFlow(State.STOP)
    private val tmpVector = Vector2()



    override fun render() {
        super.render()

        when(stateFlow.value) {
            State.STOP -> {
                tmpVector.x = body.linearVelocity.x * 0.98f
                tmpVector.y = body.linearVelocity.y * 0.98f
            }
            State.UP -> {
                tmpVector.x = body.linearVelocity.x
                tmpVector.y = min(body.linearVelocity.y + 0.1f, 5f)
            }
            State.DOWN -> {
                tmpVector.x = body.linearVelocity.x
                tmpVector.y = max(body.linearVelocity.y - 0.1f, -5f)
            }
            State.LEFT -> {
                tmpVector.x = max(body.linearVelocity.x - 0.1f, -5f)
                tmpVector.y = body.linearVelocity.y
            }
            State.RIGHT -> {
                tmpVector.x = min(body.linearVelocity.x + 0.1f, 5f)
                tmpVector.y = body.linearVelocity.y
            }
        }

        body.linearVelocity = tmpVector

    }



    enum class State {
        STOP, UP, DOWN, LEFT, RIGHT
    }
}