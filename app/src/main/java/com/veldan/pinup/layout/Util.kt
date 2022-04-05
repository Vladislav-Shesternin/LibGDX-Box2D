package com.veldan.pinup.layout

import com.badlogic.gdx.scenes.scene2d.Actor
import com.veldan.pinup.utils.HEIGHT

fun getFigmaY(y: Float, height: Float, parentH: Float = HEIGHT) = parentH - (y + height)

fun Actor.setBoundsFigmaY(x: Float, y: Float, width: Float, height: Float, parentH: Float = HEIGHT){
    val reverseY = getFigmaY(y, height, parentH)
    setBounds(x, reverseY, width, height)
}

fun Actor.setPositionFigmaY(x: Float, y: Float, height: Float, parentH: Float = HEIGHT){
    val reverseY = getFigmaY(y, height, parentH)
    setPosition(x, reverseY)
}