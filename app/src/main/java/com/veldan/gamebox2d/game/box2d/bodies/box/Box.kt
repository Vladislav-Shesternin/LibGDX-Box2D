package com.veldan.gamebox2d.game.box2d.bodies.box

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.veldan.gamebox2d.game.actors.checkbox.CheckBoxGroup
import com.veldan.gamebox2d.game.actors.label.LabelStyle
import com.veldan.gamebox2d.game.box2d.bodies.AbstractBody
import com.veldan.gamebox2d.game.box2d.bodies.Collision
import com.veldan.gamebox2d.game.box2d.bodies.box.State.*
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import kotlin.experimental.or
import kotlin.math.max
import kotlin.math.min

class Box(
    val text         : String?    = null,
    checkBoxGroup: CheckBoxGroup? = null,
): AbstractBody() {

    override val name       = "Box"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.DynamicBody
    }
    override val fixtureDef = FixtureDef().apply {
        density     = 1f
        restitution = 0.5f
        filter.apply {
            categoryBits = Collision.Bits.BOX_1.bit
            maskBits = Collision.Bits.BORDERS.bit or Collision.Bits.BOX_2.bit
        }
    }
    override val actor      = ABox(text, LabelStyle.inter_Black_12, checkBoxGroup)

    var stateFlow = MutableSharedFlow<State>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    private var state = STOP


    init {
        collectState()
    }

    override fun render() {
        super.render()

        body.linearVelocity.also { velocity ->
            when (state) {
                STOP  -> velocity.x *= 0.98f
                LEFT  -> velocity.x = max(velocity.x - 0.1f, -5f)
                RIGHT -> velocity.x = min(velocity.x + 0.1f, 5f)
            }
            body.linearVelocity = velocity
        }
    }

    override fun beginContact(contactBody: AbstractBody) {

    }



    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------
    private fun collectState() {
        coroutineMain.launch {
            stateFlow.collect { state ->
                this@Box.state = state

                when(state) {
                    UP -> {
                        body.linearVelocity.apply { y = 0f }
                        body.applyLinearImpulse(Vector2(0f, 500f), body.worldCenter,true)
                    }
                }
            }
        }
    }

}