package com.veldan.gamebox2d.game.utils

import com.badlogic.gdx.scenes.scene2d.Actor

class LayoutUtil(
    val gameW: Float,
    val gameH: Float,
    val figmaW: Float,
    val figmaH: Float,
) {

    companion object {
        const val PHYSIC_EDITOR_W = 1f
    }

    val gameOnePercentX get() = gameW / 100f
    val gameOnePercentY get() = gameH / 100f

    val figmaOnePercentX get() = figmaW / 100f
    val figmaOnePercentY get() = figmaH / 100f



    fun getFigmaPercentX(x: Float) = x / figmaOnePercentX

    fun getFigmaPercentY(y: Float) = y / figmaOnePercentY

    fun getX(x: Float) = getFigmaPercentX(x) * gameOnePercentX

    fun getY(y: Float) = getFigmaPercentY(y) * gameOnePercentY

    fun getScale(width: Float): Float {
        val figmaPercentX = getFigmaPercentX(width)
        val bodyPercentX  = PHYSIC_EDITOR_W / gameOnePercentX
        return figmaPercentX / bodyPercentX
    }

    fun setSize(actor: Actor, width: Float, height: Float) {
        actor.width = getX(width)
        actor.height = getY(height)
    }

    fun setPosition(actor: Actor, x: Float, y: Float) {
        actor.x = getX(x)
        actor.y = getY(y)
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