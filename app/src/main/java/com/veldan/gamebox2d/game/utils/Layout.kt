package com.veldan.gamebox2d.game.utils

object Layout {

    object Splash {
        val progress = LayoutData(0f,600f,170f,100f)
    }

    object Game {
        val up    = LayoutData(140f,580f,100f,100f)
        val down  = LayoutData(1160f,20f,100f,100f)
        val left  = LayoutData(20f,460f,100f,100f)
        val right = LayoutData(1280f,140f,100f,100f)
        val next  = LayoutData(1280f,580f,100f,100f)

        val borders = LayoutData(0f, 0f, 1400f, 700f)
        val car     = LayoutData(40f, 40f, 61f, 61f, hs = 20f)
        val locator = LayoutData(538f, 188f, 324f, 324f)
    }

    data class LayoutData(
        val x: Float = 0f,
        val y: Float = 0f,
        val w: Float = 0f,
        val h: Float = 0f,
        val hs: Float = 0f,
        val vs: Float = 0f,
    )

}












