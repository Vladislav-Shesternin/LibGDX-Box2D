package com.veldan.gamebox2d.game.box2d.bodies

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.badlogic.gdx.utils.Disposable
import com.veldan.gamebox2d.game.box2d.WorldUtil
import com.veldan.gamebox2d.game.utils.Size
import com.veldan.gamebox2d.game.utils.SizeConverter
import com.veldan.gamebox2d.game.utils.advanced.AdvancedGroup
import com.veldan.gamebox2d.game.utils.advanced.AdvancedStage
import com.veldan.gamebox2d.utils.cancelCoroutinesAll
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

abstract class AbstractBody: Disposable {
    abstract var id           : BodyId
    abstract val name         : String
    abstract val bodyDef      : BodyDef
    abstract val fixtureDef   : FixtureDef
    abstract val collisionList: MutableList<BodyId>

    open val actor: AdvancedGroup? = null

    lateinit var stageUI             : AdvancedStage
    lateinit var sizeConverterUIToBox: SizeConverter
    lateinit var sizeConverterBoxToUI: SizeConverter

    val size          = Size()
    val position      = Vector2()
    val coroutineMain = CoroutineScope(Dispatchers.Main)

    val scale  by lazy { sizeConverterUIToBox.getScale(size.width) }
    val center by lazy { WorldUtil.bodyEditor.getOrigin(name, scale) }
    val body   by lazy { WorldUtil.world.createBody(bodyDef).apply { userData = this@AbstractBody } }



    override fun dispose() {
        cancelCoroutinesAll(coroutineMain)
    }

    open fun render() {
        renderActor()
    }

    open fun beginContact(contactBody: AbstractBody) {}

    open fun endContact(contactBody: AbstractBody) {}



    private fun addActor() {
        actor?.apply {
            stageUI.addActor(this)
            setBounds(position.x, position.y, size.width, size.height)
        }
    }

    private fun renderActor() {
        actor?.apply {
            sizeConverterBoxToUI.apply {
                x = getSizeX(body.position.x - center.x)
                y = getSizeY(body.position.y - center.y)
                setOrigin(getSizeX(center.x), getSizeY(center.y))
            }
            rotation = Math.toDegrees(body.angle.toDouble()).toFloat()
        }
    }

    private fun createBody() {
        WorldUtil.bodyEditor.attachFixture(body, name, fixtureDef, scale)
        WorldUtil.abstractBodies.add(this)
        actor?.let { addActor() }
    }



    fun initialize(
        stageUI             : AdvancedStage,
        sizeConverterUIToBox: SizeConverter,
        sizeConverterBoxToUI: SizeConverter,
        position            : Vector2,
        size                : Size,
    ) {
        this.stageUI              = stageUI
        this.sizeConverterUIToBox = sizeConverterUIToBox
        this.sizeConverterBoxToUI = sizeConverterBoxToUI
        this.position.set(position)
        this.size.set(size)

        with(sizeConverterUIToBox) {
            bodyDef.position.set(
                getSizeX(position.x) + center.x,
                getSizeY(position.y) + center.y
            )
        }

        createBody()
    }

}