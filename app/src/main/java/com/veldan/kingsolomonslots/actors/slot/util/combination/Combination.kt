package com.veldan.kingsolomonslots.actors.slot.util.combination

import com.veldan.kingsolomonslots.actors.slot.util.combination.Matrix3x5.Item.*

interface CombinationMatrixEnum {
    val matrix: Matrix3x5
}

object Combination{

    /**Комбинация Рандомная*/
    enum class Random(
        override val matrix: Matrix3x5,
    ): CombinationMatrixEnum {
        _1(
            Matrix3x5(
                a0 = A, b0 = A, c0 = B, d0 = C, e0 = D,
                a1 = E, b1 = F, c1 = G, d1 = H, e1 = A,
                a2 = B, b2 = C, c2 = D, d2 = E, e2 = F,
            )
        ),
        _2(
            Matrix3x5(
                a0 = B, b0 = A, c0 = B, d0 = C, e0 = D,
                a1 = E, b1 = F, c1 = G, d1 = H, e1 = A,
                a2 = B, b2 = C, c2 = D, d2 = E, e2 = F,
            )
        ),
        _3(
            Matrix3x5(
                a0 = F, b0 = A, c0 = B, d0 = C, e0 = D,
                a1 = E, b1 = F, c1 = G, d1 = H, e1 = A,
                a2 = B, b2 = C, c2 = D, d2 = E, e2 = F,
            )
        ),
        _4(
            Matrix3x5(
                a0 = F, b0 = A, c0 = B, d0 = C, e0 = D,
                a1 = E, b1 = F, c1 = G, d1 = F, e1 = A,
                a2 = F, b2 = C, c2 = D, d2 = E, e2 = F,
            )
        ),
        _5(
            Matrix3x5(
                a0 = F, b0 = A, c0 = D, d0 = C, e0 = D,
                a1 = E, b1 = F, c1 = G, d1 = F, e1 = A,
                a2 = D, b2 = C, c2 = D, d2 = E, e2 = F,
            )
        ),
        _6(
            Matrix3x5(
                a0 = E, b0 = A, c0 = D, d0 = C, e0 = D,
                a1 = E, b1 = F, c1 = G, d1 = E, e1 = A,
                a2 = D, b2 = C, c2 = D, d2 = E, e2 = F,
            )
        ),
        _7(
            Matrix3x5(
                a0 = E, b0 = A, c0 = D, d0 = C, e0 = D,
                a1 = A, b1 = G, c1 = G, d1 = G, e1 = A,
                a2 = D, b2 = C, c2 = D, d2 = E, e2 = F,
            )
        ),
        _8(
            Matrix3x5(
                a0 = E, b0 = A, c0 = D, d0 = C, e0 = D,
                a1 = A, b1 = G, c1 = A, d1 = G, e1 = A,
                a2 = D, b2 = C, c2 = A, d2 = E, e2 = F,
            )
        ),
        _9(
            Matrix3x5(
                a0 = E, b0 = D, c0 = D, d0 = C, e0 = D,
                a1 = B, b1 = G, c1 = A, d1 = G, e1 = A,
                a2 = D, b2 = C, c2 = A, d2 = E, e2 = F,
            )
        ),
        _10(
            Matrix3x5(
                a0 = E, b0 = D, c0 = D, d0 = C, e0 = D,
                a1 = B, b1 = G, c1 = B, d1 = G, e1 = B,
                a2 = D, b2 = C, c2 = A, d2 = E, e2 = F,
            )
        ),
    }

    /**Комбинация Рандомная С WILD*/
    enum class RandomWithWild(
        override val matrix: Matrix3x5,
    ): CombinationMatrixEnum {
        _1(
            Matrix3x5(
                a0 = WILD, b0 = A, c0 = B, d0 = C, e0 = D,
                a1 = E, b1 = F, c1 = G, d1 = H, e1 = A,
                a2 = B, b2 = C, c2 = D, d2 = E, e2 = F,
            )
        ),
        _2(
            Matrix3x5(
                a0 = F, b0 = WILD, c0 = B, d0 = C, e0 = D,
                a1 = E, b1 = F, c1 = G, d1 = H, e1 = A,
                a2 = B, b2 = C, c2 = D, d2 = E, e2 = F,
            )
        ),
        _3(
            Matrix3x5(
                a0 = F, b0 = D, c0 = WILD, d0 = C, e0 = D,
                a1 = E, b1 = F, c1 = G, d1 = H, e1 = A,
                a2 = B, b2 = C, c2 = D, d2 = E, e2 = F,
            )
        ),
        _4(
            Matrix3x5(
                a0 = F, b0 = D, c0 = D, d0 = WILD, e0 = D,
                a1 = E, b1 = F, c1 = G, d1 = H, e1 = A,
                a2 = B, b2 = C, c2 = D, d2 = E, e2 = F,
            )
        ),
        _5(
            Matrix3x5(
                a0 = F, b0 = D, c0 = D, d0 = H, e0 = WILD,
                a1 = E, b1 = F, c1 = G, d1 = H, e1 = A,
                a2 = B, b2 = C, c2 = D, d2 = E, e2 = F,
            )
        ),
        _6(
            Matrix3x5(
                a0 = F, b0 = D, c0 = D, d0 = H, e0 = A,
                a1 = WILD, b1 = F, c1 = G, d1 = H, e1 = A,
                a2 = B, b2 = C, c2 = D, d2 = E, e2 = F,
            )
        ),
        _7(
            Matrix3x5(
                a0 = F, b0 = D, c0 = D, d0 = H, e0 = A,
                a1 = H, b1 = WILD, c1 = G, d1 = H, e1 = A,
                a2 = B, b2 = C, c2 = D, d2 = E, e2 = F,
            )
        ),
        _8(
            Matrix3x5(
                a0 = F, b0 = D, c0 = D, d0 = H, e0 = A,
                a1 = H, b1 = A, c1 = WILD, d1 = H, e1 = A,
                a2 = B, b2 = C, c2 = D, d2 = E, e2 = F,
            )
        ),
        _9(
            Matrix3x5(
                a0 = F, b0 = D, c0 = D, d0 = H, e0 = A,
                a1 = H, b1 = F, c1 = G, d1 = WILD, e1 = A,
                a2 = B, b2 = C, c2 = D, d2 = E, e2 = F,
            )
        ),
        _10(
            Matrix3x5(
                a0 = F, b0 = D, c0 = D, d0 = H, e0 = A,
                a1 = H, b1 = E, c1 = G, d1 = H, e1 = WILD,
                a2 = B, b2 = C, c2 = D, d2 = E, e2 = F,
            )
        ),
        _11(
            Matrix3x5(
                a0 = F, b0 = D, c0 = D, d0 = H, e0 = A,
                a1 = H, b1 = E, c1 = G, d1 = H, e1 = H,
                a2 = WILD, b2 = C, c2 = D, d2 = E, e2 = F,
            )
        ),
        _12(
            Matrix3x5(
                a0 = F, b0 = D, c0 = D, d0 = H, e0 = A,
                a1 = H, b1 = E, c1 = G, d1 = H, e1 = H,
                a2 = E, b2 = WILD, c2 = D, d2 = E, e2 = F,
            )
        ),
        _13(
            Matrix3x5(
                a0 = F, b0 = D, c0 = D, d0 = H, e0 = A,
                a1 = H, b1 = E, c1 = G, d1 = H, e1 = H,
                a2 = E, b2 = D, c2 = WILD, d2 = E, e2 = F,
            )
        ),
        _14(
            Matrix3x5(
                a0 = F, b0 = D, c0 = D, d0 = H, e0 = A,
                a1 = H, b1 = E, c1 = G, d1 = H, e1 = H,
                a2 = E, b2 = D, c2 = A, d2 = WILD, e2 = F,
            )
        ),
        _15(
            Matrix3x5(
                a0 = F, b0 = D, c0 = D, d0 = H, e0 = A,
                a1 = H, b1 = E, c1 = G, d1 = H, e1 = H,
                a2 = E, b2 = D, c2 = D, d2 = E, e2 = WILD,
            )
        ),
    }

}









