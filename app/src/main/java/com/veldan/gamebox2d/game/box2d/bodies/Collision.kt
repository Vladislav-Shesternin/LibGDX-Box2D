package com.veldan.gamebox2d.game.box2d.bodies

object Collision {
    enum class Index(val index: Short) {
        BORDERS(1)
    }

    enum class Bits(val bit: Short) {
        BOX_1(0x0001),
        BOX_2(0x0002),
        BORDERS(0x0004),
    }
}