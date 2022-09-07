package com.veldan.gamebox2d.game.box2d.bodies

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.veldan.gamebox2d.game.box2d.WorldUtil
import com.veldan.gamebox2d.game.utils.LayoutUtil
import com.veldan.gamebox2d.game.utils.advanced.AdvancedStage
import com.veldan.gamebox2d.utils.log

abstract class AbstractBody(
    open val width     : Float,
    open val height    : Float,
    open val layoutUtil: LayoutUtil,
    open val stage     : AdvancedStage,
) {
    abstract val name      : String
    abstract val bodyDef   : BodyDef
    abstract val fixtureDef: FixtureDef

    open val image: Image? = null

    val scale   by lazy { layoutUtil.getScale(width) }
    val body    by lazy { WorldUtil.world.createBody(bodyDef) }
    val center  by lazy { WorldUtil.bodyEditor.getOrigin(name, scale) }




    open fun AdvancedStage.addActorsOnStage() {
        addImage()
    }

    open fun render() {
        renderImage()
    }


    private fun AdvancedStage.addImage() {
        image?.let {
            addActor(it)
            layoutUtil.setBounds(it, body.position.x - center.x, body.position.y - center.y, this@AbstractBody.width, this@AbstractBody.height)
        }
    }

    private fun renderImage() {
        image?.apply {
            x = body.position.x - center.x
            y = body.position.y - center.y
            setOrigin(center.x, center.y)
            rotation = Math.toDegrees(body.angle.toDouble()).toFloat()
        }
    }

    private fun attachFixture() {
        WorldUtil.bodyEditor.attachFixture(body, name, fixtureDef, scale)
    }



    fun setPosition(x: Float, y: Float) {
        bodyDef.position.set(
            layoutUtil.getX(x) + center.x,
            layoutUtil.getY(y) + center.y
        )

        attachFixture()
        stage.addActorsOnStage()
        WorldUtil.abstractBodies.add(this)
    }

}