package com.veldan.gamebox2d.game.box2d.bodies

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.badlogic.gdx.utils.Disposable
import com.veldan.gamebox2d.game.box2d.WorldUtil
import com.veldan.gamebox2d.game.utils.LayoutUtil
import com.veldan.gamebox2d.game.utils.advanced.AdvancedGroup
import com.veldan.gamebox2d.game.utils.advanced.AdvancedStage
import com.veldan.gamebox2d.utils.cancelCoroutinesAll
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

abstract class AbstractBody: Disposable {
    abstract val name      : String
    abstract val bodyDef   : BodyDef
    abstract val fixtureDef: FixtureDef

    open val actor: AdvancedGroup? = null

    lateinit var stage                : AdvancedStage
    lateinit var layoutUtilFigmaToGame: LayoutUtil
    lateinit var layoutUtilGameToFigma: LayoutUtil

    val size          = Vector2()
    val coroutineMain = CoroutineScope(Dispatchers.Main)

    val scale  by lazy { layoutUtilFigmaToGame.getScale(size.x) }
    val body   by lazy { WorldUtil.world.createBody(bodyDef).apply { userData = this@AbstractBody } }
    val center by lazy { WorldUtil.bodyEditor.getOrigin(name, scale) }



    override fun dispose() {
        cancelCoroutinesAll(coroutineMain)
    }



    open fun AdvancedGroup.addActorsOnGroup() {}

    open fun render() {
        renderGroup()
    }



    private fun AdvancedStage.addGroup() {
        actor?.let {
            addActor(it)
            layoutUtilGameToFigma.setPosition(it, body.position.x - center.x, body.position.y - center.y)
            it.setSize(size.x, size.y)
            it.addActorsOnGroup()
        }
    }

    private fun renderGroup() {
        actor?.apply {
            layoutUtilGameToFigma.apply {
                x = getX(body.position.x - center.x)
                y = getY(body.position.y - center.y)
                setOrigin(getX(center.x), getY(center.y))
            }
            rotation = Math.toDegrees(body.angle.toDouble()).toFloat()
        }
    }

    private fun attachFixture() {
        WorldUtil.bodyEditor.attachFixture(body, name, fixtureDef, scale)
    }



    fun initialize(
        stage     : AdvancedStage,
        layoutUtil: LayoutUtil,
        x         : Float,
        y         : Float,
        width     : Float,
        height    : Float
    ) {

        this.stage            = stage
        layoutUtilFigmaToGame = layoutUtil.apply {
            layoutUtilGameToFigma = LayoutUtil(figmaW, figmaH, gameW, gameH)
        }
        size.set(width, height)

        bodyDef.position.set(
            layoutUtil.getX(x) + center.x,
            layoutUtil.getY(y) + center.y
        )

        attachFixture()
        WorldUtil.abstractBodies.add(this)

        actor?.let { stage.addGroup() }
    }

}