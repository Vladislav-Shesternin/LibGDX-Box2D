package com.veldan.gamebox2d.game.utils

import com.badlogic.gdx.scenes.scene2d.Actor

class SizeConverter(
    val fromSize: Size,
    val toSize  : Size,
) {

    companion object {
        private const val PHYSIC_EDITOR_W = 1f
    }

    private val fromOnePercentX get() = fromSize.width / 100f
    private val fromOnePercentY get() = fromSize.height / 100f

    private val toOnePercentX get() = toSize.width / 100f
    private val toOnePercentY get() = toSize.height / 100f



    private fun getPercentX(x: Float) = x / fromOnePercentX
    private fun getPercentY(y: Float) = y / fromOnePercentY

    fun getSizeX(x: Float) = getPercentX(x) * toOnePercentX
    fun getSizeY(y: Float) = getPercentY(y) * toOnePercentY

    fun getScale(width: Float): Float {
        val figmaPercentX = getPercentX(width)
        val bodyPercentX  = PHYSIC_EDITOR_W / toOnePercentX
        return figmaPercentX / bodyPercentX
    }

    fun setSize(actor: Actor, width: Float, height: Float) {
        actor.width = getSizeX(width)
        actor.height = getSizeY(height)
    }

    fun setPosition(actor: Actor, x: Float, y: Float) {
        actor.x = getSizeX(x)
        actor.y = getSizeY(y)
    }

    fun setBounds(
        actor: Actor,
        x: Float,
        y: Float,
        width: Float,
        height: Float,
    ) {
        setPosition(actor, x, y)
        setSize(actor, width, height)
    }


}



fun Actor.getSizeByPercentX(percent: Float) = (width / 100f) * percent

fun Actor.getSizeByPercentY(percent: Float) = (height / 100f) * percent