package com.veldan.gamebox2d.game.utils

import com.badlogic.gdx.graphics.Color

object Color {

    val GOLD       = rgba(204, 123, 41, 1)

    fun rgba(r: Int, g: Int, b: Int, a: Int = 1) = Color(r / 255f, g / 255f, b / 255f, a.toFloat())

}