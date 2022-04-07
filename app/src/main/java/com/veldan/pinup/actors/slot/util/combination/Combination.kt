package com.veldan.pinup.actors.slot.util.combination

import com.veldan.pinup.actors.slot.util.combination.Matrix3x3.Item.*

enum class CombinationRandom(
    val matrix: Matrix3x3,
) {
    _1(Matrix3x3(
            winItemList = listOf(A, B),
            a0 = B   , b0 = B   , c0 = C,
            a1 = WILD, b1 = C   , c1 = C,
            a2 = A   , b2 = C   , c2 = B,
    ))
}