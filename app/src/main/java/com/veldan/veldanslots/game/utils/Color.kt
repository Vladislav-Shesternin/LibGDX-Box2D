package com.veldan.veldanslots.game.utils

import com.badlogic.gdx.graphics.Color

object Color {

    val GOLD = rgba(204, 123, 41, 1)
    val WHITE_GOLD = rgba(255, 244, 186, 1)

    fun rgba(r: Int, g: Int, b: Int, a: Int = 1) = Color(r / 255f, g / 255f, b / 255f, a.toFloat())

}