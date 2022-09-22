package com.veldan.gamebox2d.game.box2d.bodies

object Collision {
    enum class Index(val index: Short) {
    }

    enum class Bits(val bit: Short) {
        LOCATOR(0x0001),
        BORDERS(0x0002),
        CAR(0x0004),
        CAR_ENEMY(0x0008),
    }
}