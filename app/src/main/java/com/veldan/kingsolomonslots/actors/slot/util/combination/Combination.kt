package com.veldan.kingsolomonslots.actors.slot.util.combination

import com.veldan.kingsolomonslots.actors.slot.util.combination.Matrix3x5.Item.*

interface CombinationMatrixEnum {
    val matrix: Matrix3x5
}

object Combination{

    enum class Mix(
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

    enum class MixWithWild(
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

    enum class WinMonochrome(
        override val matrix: Matrix3x5,
    ): CombinationMatrixEnum {
        _1(
            Matrix3x5(
                scheme = """
                    + + + - -
                    - - - - -
                    - - - - -
                """,
                winItemList = listOf(A),
                a0 = A, b0 = A, c0 = A, d0 = C, e0 = D,
                a1 = E, b1 = F, c1 = G, d1 = H, e1 = F,
                a2 = B, b2 = C, c2 = D, d2 = E, e2 = F,
            )
        ),
        _2(
            Matrix3x5(
                winItemList = listOf(A),
                a0 = A, b0 = A, c0 = A, d0 = A, e0 = D,
                a1 = E, b1 = F, c1 = G, d1 = H, e1 = D,
                a2 = B, b2 = C, c2 = D, d2 = E, e2 = F,
            )
        ),
        _3(
            Matrix3x5(
                winItemList = listOf(A),
                a0 = A, b0 = A, c0 = A, d0 = A, e0 = A,
                a1 = E, b1 = F, c1 = G, d1 = H, e1 = D,
                a2 = B, b2 = C, c2 = D, d2 = E, e2 = F,
            )
        ),
        _4(
            Matrix3x5(
                winItemList = listOf(A),
                a0 = E, b0 = F, c0 = G, d0 = G, e0 = E,
                a1 = A, b1 = A, c1 = A, d1 = H, e1 = D,
                a2 = B, b2 = C, c2 = D, d2 = E, e2 = F,
            )
        ),
        _5(
            Matrix3x5(
                winItemList = listOf(A),
                a0 = E, b0 = F, c0 = G, d0 = G, e0 = E,
                a1 = A, b1 = A, c1 = A, d1 = A, e1 = D,
                a2 = B, b2 = C, c2 = D, d2 = E, e2 = F,
            )
        ),
        _6(
            Matrix3x5(
                winItemList = listOf(A),
                a0 = E, b0 = F, c0 = G, d0 = G, e0 = E,
                a1 = A, b1 = A, c1 = A, d1 = A, e1 = A,
                a2 = B, b2 = C, c2 = D, d2 = E, e2 = F,
            )
        ),
        _7(
            Matrix3x5(
                winItemList = listOf(A),
                a0 = E, b0 = F, c0 = G, d0 = G, e0 = E,
                a1 = B, b1 = C, c1 = D, d1 = H, e1 = D,
                a2 = A, b2 = A, c2 = A, d2 = E, e2 = F,
            )
        ),
        _8(
            Matrix3x5(
                winItemList = listOf(A),
                a0 = E, b0 = F, c0 = G, d0 = G, e0 = E,
                a1 = B, b1 = C, c1 = D, d1 = E, e1 = D,
                a2 = A, b2 = A, c2 = A, d2 = A, e2 = F,
            )
        ),
        _9(
            Matrix3x5(
                winItemList = listOf(A),
                a0 = E, b0 = F, c0 = G, d0 = G, e0 = E,
                a1 = B, b1 = C, c1 = D, d1 = E, e1 = F,
                a2 = A, b2 = A, c2 = A, d2 = A, e2 = A,
            )
        ),
        _10(
            Matrix3x5(
                winItemList = listOf(A),
                a0 = A, b0 = B, c0 = C, d0 = G, e0 = E,
                a1 = B, b1 = A, c1 = D, d1 = E, e1 = F,
                a2 = D, b2 = C, c2 = A, d2 = F, e2 = E,
            )
        ),
        _11(
            Matrix3x5(
                winItemList = listOf(A),
                a0 = A, b0 = B, c0 = C, d0 = G, e0 = E,
                a1 = B, b1 = A, c1 = D, d1 = A, e1 = F,
                a2 = D, b2 = C, c2 = A, d2 = F, e2 = E,
            )
        ),
        _12(
            Matrix3x5(
                winItemList = listOf(A),
                a0 = A, b0 = B, c0 = C, d0 = G, e0 = A,
                a1 = B, b1 = A, c1 = D, d1 = A, e1 = F,
                a2 = D, b2 = C, c2 = A, d2 = F, e2 = E,
            )
        ),
        _13(
            Matrix3x5(
                winItemList = listOf(A),
                a0 = A, b0 = B, c0 = C, d0 = G, e0 = E,
                a1 = B, b1 = A, c1 = A, d1 = G, e1 = F,
                a2 = D, b2 = C, c2 = A, d2 = F, e2 = E,
            )
        ),
        _14(
            Matrix3x5(
                winItemList = listOf(A),
                a0 = A, b0 = B, c0 = A, d0 = G, e0 = E,
                a1 = B, b1 = A, c1 = A, d1 = G, e1 = F,
                a2 = D, b2 = C, c2 = A, d2 = F, e2 = E,
            )
        ),
        _15(
            Matrix3x5(
                winItemList = listOf(A),
                a0 = A, b0 = B, c0 = G, d0 = G, e0 = E,
                a1 = B, b1 = A, c1 = F, d1 = G, e1 = F,
                a2 = D, b2 = C, c2 = A, d2 = A, e2 = E,
            )
        ),
        _16(
            Matrix3x5(
                winItemList = listOf(A),
                a0 = A, b0 = B, c0 = G, d0 = G, e0 = E,
                a1 = B, b1 = A, c1 = F, d1 = G, e1 = F,
                a2 = D, b2 = C, c2 = A, d2 = A, e2 = A,
            )
        ),
        _17(
            Matrix3x5(
                winItemList = listOf(A),
                a0 = A, b0 = B, c0 = G, d0 = G, e0 = E,
                a1 = B, b1 = A, c1 = A, d1 = A, e1 = F,
                a2 = D, b2 = C, c2 = A, d2 = G, e2 = F,
            )
        ),
        _18(
            Matrix3x5(
                winItemList = listOf(A),
                a0 = A, b0 = B, c0 = G, d0 = G, e0 = E,
                a1 = B, b1 = A, c1 = A, d1 = A, e1 = A,
                a2 = D, b2 = C, c2 = A, d2 = G, e2 = F,
            )
        ),
        _19(
            Matrix3x5(
                winItemList = listOf(A),
                a0 = E, b0 = B, c0 = A, d0 = G, e0 = E,
                a1 = F, b1 = A, c1 = G, d1 = D, e1 = C,
                a2 = A, b2 = C, c2 = H, d2 = G, e2 = F,
            )
        ),
        _20(
            Matrix3x5(
                winItemList = listOf(A),
                a0 = E, b0 = B, c0 = A, d0 = G, e0 = E,
                a1 = F, b1 = A, c1 = G, d1 = A, e1 = C,
                a2 = A, b2 = C, c2 = H, d2 = G, e2 = F,
            )
        ),
        _21(
            Matrix3x5(
                winItemList = listOf(A),
                a0 = E, b0 = B, c0 = A, d0 = G, e0 = E,
                a1 = F, b1 = A, c1 = G, d1 = A, e1 = C,
                a2 = A, b2 = C, c2 = H, d2 = G, e2 = A,
            )
        ),
        _22(
            Matrix3x5(
                winItemList = listOf(A),
                a0 = E, b0 = B, c0 = A, d0 = G, e0 = E,
                a1 = F, b1 = A, c1 = A, d1 = D, e1 = C,
                a2 = A, b2 = C, c2 = H, d2 = G, e2 = F,
            )
        ),
        _23(
            Matrix3x5(
                winItemList = listOf(A),
                a0 = E, b0 = B, c0 = A, d0 = G, e0 = E,
                a1 = F, b1 = A, c1 = A, d1 = D, e1 = C,
                a2 = A, b2 = C, c2 = A, d2 = G, e2 = F,
            )
        ),
        _24(
            Matrix3x5(
                winItemList = listOf(A),
                a0 = E, b0 = B, c0 = A, d0 = A, e0 = E,
                a1 = F, b1 = A, c1 = D, d1 = D, e1 = C,
                a2 = A, b2 = C, c2 = D, d2 = G, e2 = F,
            )
        ),
        _25(
            Matrix3x5(
                winItemList = listOf(A),
                a0 = E, b0 = B, c0 = A, d0 = A, e0 = A,
                a1 = F, b1 = A, c1 = F, d1 = D, e1 = C,
                a2 = A, b2 = C, c2 = D, d2 = G, e2 = F,
            )
        ),
        _26(
            Matrix3x5(
                winItemList = listOf(A),
                a0 = E, b0 = B, c0 = A, d0 = B, e0 = C,
                a1 = F, b1 = A, c1 = A, d1 = A, e1 = C,
                a2 = A, b2 = C, c2 = D, d2 = G, e2 = F,
            )
        ),
        _27(
            Matrix3x5(
                winItemList = listOf(A),
                a0 = E, b0 = B, c0 = A, d0 = B, e0 = C,
                a1 = F, b1 = A, c1 = A, d1 = A, e1 = A,
                a2 = A, b2 = C, c2 = D, d2 = G, e2 = F,
            )
        ),
        _28(
            Matrix3x5(
                winItemList = listOf(A),
                a0 = E, b0 = B, c0 = A, d0 = B, e0 = C,
                a1 = F, b1 = A, c1 = A, d1 = A, e1 = A,
                a2 = A, b2 = C, c2 = D, d2 = G, e2 = F,
            )
        ),
        _29(
            Matrix3x5(
                winItemList = listOf(A),
                a0 = A, b0 = A, c0 = C, d0 = B, e0 = C,
                a1 = F, b1 = F, c1 = A, d1 = D, e1 = E,
                a2 = H, b2 = C, c2 = D, d2 = G, e2 = F,
            )
        ),
        _30(
            Matrix3x5(
                winItemList = listOf(A),
                a0 = A, b0 = A, c0 = A, d0 = B, e0 = C,
                a1 = F, b1 = F, c1 = C, d1 = A, e1 = E,
                a2 = H, b2 = C, c2 = D, d2 = G, e2 = F,
            )
        ),
        _31(
            Matrix3x5(
                winItemList = listOf(A),
                a0 = A, b0 = A, c0 = A, d0 = A, e0 = C,
                a1 = F, b1 = F, c1 = C, d1 = E, e1 = A,
                a2 = H, b2 = C, c2 = D, d2 = G, e2 = F,
            )
        ),
        _32(
            Matrix3x5(
                winItemList = listOf(A),
                a0 = A, b0 = A, c0 = B, d0 = D, e0 = C,
                a1 = F, b1 = F, c1 = A, d1 = E, e1 = H,
                a2 = H, b2 = C, c2 = D, d2 = A, e2 = F,
            )
        ),
        _33(
            Matrix3x5(
                winItemList = listOf(A),
                a0 = A, b0 = A, c0 = A, d0 = D, e0 = C,
                a1 = F, b1 = F, c1 = E, d1 = A, e1 = H,
                a2 = H, b2 = C, c2 = D, d2 = B, e2 = A,
            )
        ),
        _34(
            Matrix3x5(
                winItemList = listOf(A),
                a0 = A, b0 = A, c0 = E, d0 = D, e0 = C,
                a1 = F, b1 = F, c1 = A, d1 = B, e1 = A,
                a2 = H, b2 = C, c2 = D, d2 = A, e2 = H,
            )
        ),
        _35(
            Matrix3x5(
                winItemList = listOf(A),
                a0 = A, b0 = A, c0 = A, d0 = D, e0 = C,
                a1 = F, b1 = F, c1 = A, d1 = B, e1 = H,
                a2 = H, b2 = C, c2 = A, d2 = E, e2 = H,
            )
        ),
        _36(
            Matrix3x5(
                winItemList = listOf(A),
                a0 = A, b0 = A, c0 = A, d0 = D, e0 = C,
                a1 = F, b1 = F, c1 = A, d1 = B, e1 = H,
                a2 = H, b2 = C, c2 = A, d2 = A, e2 = A,
            )
        ),
        _37(
            Matrix3x5(
                winItemList = listOf(A),
                a0 = A, b0 = A, c0 = A, d0 = A, e0 = C,
                a1 = F, b1 = F, c1 = A, d1 = B, e1 = H,
                a2 = H, b2 = A, c2 = B, d2 = D, e2 = E,
            )
        ),
        _38(
            Matrix3x5(
                winItemList = listOf(A),
                a0 = A, b0 = A, c0 = A, d0 = B, e0 = C,
                a1 = F, b1 = A, c1 = H, d1 = B, e1 = H,
                a2 = A, b2 = E, c2 = B, d2 = D, e2 = E,
            )
        ),
        _39(
            Matrix3x5(
                winItemList = listOf(A),
                a0 = A, b0 = A, c0 = A, d0 = A, e0 = C,
                a1 = F, b1 = E, c1 = A, d1 = B, e1 = H,
                a2 = H, b2 = A, c2 = A, d2 = A, e2 = A,
            )
        ),
        _40(
            Matrix3x5(
                winItemList = listOf(A),
                a0 = A, b0 = A, c0 = A, d0 = A, e0 = A,
                a1 = F, b1 = E, c1 = A, d1 = B, e1 = H,
                a2 = A, b2 = A, c2 = A, d2 = A, e2 = A,
            )
        ),
        _41(
            Matrix3x5(
                winItemList = listOf(A),
                a0 = A, b0 = A, c0 = C, d0 = A, e0 = A,
                a1 = F, b1 = E, c1 = A, d1 = B, e1 = H,
                a2 = H, b2 = D, c2 = E, d2 = G, e2 = F,
            )
        ),
        _42(
            Matrix3x5(
                winItemList = listOf(A),
                a0 = A, b0 = A, c0 = C, d0 = D, e0 = G,
                a1 = F, b1 = E, c1 = A, d1 = B, e1 = H,
                a2 = H, b2 = D, c2 = E, d2 = A, e2 = A,
            )
        ),
        _43(
            Matrix3x5(
                winItemList = listOf(A),
                a0 = A, b0 = A, c0 = C, d0 = D, e0 = G,
                a1 = F, b1 = E, c1 = A, d1 = B, e1 = H,
                a2 = A, b2 = A, c2 = E, d2 = G, e2 = B,
            )
        ),
        _44(
            Matrix3x5(
                winItemList = listOf(A),
                a0 = A, b0 = A, c0 = C, d0 = D, e0 = G,
                a1 = A, b1 = A, c1 = A, d1 = B, e1 = H,
                a2 = B, b2 = F, c2 = E, d2 = G, e2 = B,
            )
        ),
        _45(
            Matrix3x5(
                winItemList = listOf(A),
                a0 = A, b0 = B, c0 = C, d0 = D, e0 = G,
                a1 = A, b1 = A, c1 = D, d1 = B, e1 = H,
                a2 = A, b2 = A, c2 = A, d2 = G, e2 = B,
            )
        ),
        _46(
            Matrix3x5(
                winItemList = listOf(A),
                a0 = A, b0 = B, c0 = C, d0 = D, e0 = A,
                a1 = A, b1 = A, c1 = H, d1 = A, e1 = A,
                a2 = A, b2 = A, c2 = A, d2 = A, e2 = A,
            )
        ),
        _47(
            Matrix3x5(
                winItemList = listOf(A),
                a0 = F, b0 = B, c0 = A, d0 = D, e0 = H,
                a1 = A, b1 = A, c1 = H, d1 = B, e1 = F,
                a2 = G, b2 = E, c2 = G, d2 = D, e2 = E,
            )
        ),
        _48(
            Matrix3x5(
                winItemList = listOf(A),
                a0 = F, b0 = B, c0 = H, d0 = A, e0 = H,
                a1 = A, b1 = A, c1 = A, d1 = B, e1 = F,
                a2 = G, b2 = E, c2 = G, d2 = D, e2 = E,
            )
        ),
        _49(
            Matrix3x5(
                winItemList = listOf(A),
                a0 = F, b0 = B, c0 = H, d0 = B, e0 = A,
                a1 = A, b1 = A, c1 = A, d1 = A, e1 = F,
                a2 = G, b2 = E, c2 = G, d2 = D, e2 = E,
            )
        ),
        _50(
            Matrix3x5(
                winItemList = listOf(A),
                a0 = F, b0 = B, c0 = A, d0 = B, e0 = C,
                a1 = A, b1 = A, c1 = G, d1 = D, e1 = F,
                a2 = G, b2 = E, c2 = A, d2 = D, e2 = E,
            )
        ),
        _51(
            Matrix3x5(
                winItemList = listOf(A),
                a0 = F, b0 = B, c0 = D, d0 = B, e0 = C,
                a1 = A, b1 = A, c1 = G, d1 = D, e1 = F,
                a2 = G, b2 = E, c2 = A, d2 = D, e2 = E,
            )
        ),
        _52(
            Matrix3x5(
                winItemList = listOf(A),
                a0 = F, b0 = B, c0 = D, d0 = B, e0 = C,
                a1 = A, b1 = A, c1 = A, d1 = D, e1 = F,
                a2 = G, b2 = E, c2 = B, d2 = A, e2 = E,
            )
        ),
        _53(
            Matrix3x5(
                winItemList = listOf(A),
                a0 = F, b0 = B, c0 = D, d0 = B, e0 = C,
                a1 = A, b1 = A, c1 = A, d1 = A, e1 = F,
                a2 = G, b2 = E, c2 = B, d2 = D, e2 = A,
            )
        ),
        _54(
            Matrix3x5(
                winItemList = listOf(A),
                a0 = F, b0 = B, c0 = D, d0 = A, e0 = C,
                a1 = A, b1 = A, c1 = A, d1 = D, e1 = F,
                a2 = G, b2 = E, c2 = B, d2 = A, e2 = G,
            )
        ),
        _55(
            Matrix3x5(
                winItemList = listOf(A),
                a0 = F, b0 = B, c0 = D, d0 = C, e0 = A,
                a1 = A, b1 = A, c1 = A, d1 = A, e1 = F,
                a2 = G, b2 = E, c2 = B, d2 = G, e2 = A,
            )
        ),
        _56(
            Matrix3x5(
                winItemList = listOf(A),
                a0 = F, b0 = B, c0 = A, d0 = A, e0 = A,
                a1 = A, b1 = A, c1 = D, d1 = B, e1 = F,
                a2 = G, b2 = E, c2 = B, d2 = G, e2 = C,
            )
        ),
        _57(
            Matrix3x5(
                winItemList = listOf(A),
                a0 = F, b0 = B, c0 = A, d0 = H, e0 = C,
                a1 = A, b1 = A, c1 = D, d1 = A, e1 = F,
                a2 = G, b2 = E, c2 = B, d2 = G, e2 = A,
            )
        ),
        _58(
            Matrix3x5(
                winItemList = listOf(A),
                a0 = F, b0 = B, c0 = H, d0 = H, e0 = A,
                a1 = A, b1 = A, c1 = D, d1 = A, e1 = F,
                a2 = G, b2 = E, c2 = A, d2 = G, e2 = C,
            )
        ),
        _59(
            Matrix3x5(
                winItemList = listOf(A),
                a0 = F, b0 = B, c0 = A, d0 = H, e0 = A,
                a1 = A, b1 = A, c1 = A, d1 = A, e1 = F,
                a2 = G, b2 = E, c2 = A, d2 = G, e2 = A,
            )
        ),
        _60(
            Matrix3x5(
                winItemList = listOf(A),
                a0 = F, b0 = B, c0 = A, d0 = H, e0 = A,
                a1 = A, b1 = A, c1 = D, d1 = A, e1 = F,
                a2 = G, b2 = E, c2 = A, d2 = G, e2 = F,
            )
        ),
        _61(
            Matrix3x5(
                winItemList = listOf(A),
                a0 = A, b0 = A, c0 = B, d0 = H, e0 = F,
                a1 = A, b1 = A, c1 = D, d1 = A, e1 = A,
                a2 = G, b2 = E, c2 = A, d2 = G, e2 = F,
            )
        ),
        _62(
            Matrix3x5(
                winItemList = listOf(A),
                a0 = G, b0 = E, c0 = B, d0 = A, e0 = A,
                a1 = A, b1 = A, c1 = A, d1 = H, e1 = F,
                a2 = A, b2 = A, c2 = A, d2 = G, e2 = F,
            )
        ),
        _63(
            Matrix3x5(
                winItemList = listOf(A),
                a0 = G, b0 = E, c0 = A, d0 = A, e0 = A,
                a1 = A, b1 = A, c1 = B, d1 = H, e1 = F,
                a2 = B, b2 = D, c2 = A, d2 = A, e2 = A,
            )
        ),
        _64(
            Matrix3x5(
                winItemList = listOf(A),
                a0 = G, b0 = E, c0 = B, d0 = A, e0 = A,
                a1 = A, b1 = A, c1 = A, d1 = A, e1 = A,
                a2 = B, b2 = D, c2 = G, d2 = A, e2 = A,
            )
        ),
        _65(
            Matrix3x5(
                winItemList = listOf(A),
                a0 = G, b0 = E, c0 = B, d0 = E, e0 = D,
                a1 = H, b1 = B, c1 = A, d1 = C, e1 = E,
                a2 = A, b2 = A, c2 = G, d2 = D, e2 = F,
            )
        ),
        _66(
            Matrix3x5(
                winItemList = listOf(A),
                a0 = G, b0 = E, c0 = B, d0 = E, e0 = D,
                a1 = H, b1 = B, c1 = G, d1 = A, e1 = E,
                a2 = A, b2 = A, c2 = A, d2 = D, e2 = F,
            )
        ),
        _67(
            Matrix3x5(
                winItemList = listOf(A),
                a0 = G, b0 = E, c0 = B, d0 = E, e0 = D,
                a1 = H, b1 = B, c1 = G, d1 = E, e1 = A,
                a2 = A, b2 = A, c2 = A, d2 = A, e2 = F,
            )
        ),
        _68(
            Matrix3x5(
                winItemList = listOf(A),
                a0 = G, b0 = E, c0 = B, d0 = A, e0 = D,
                a1 = H, b1 = B, c1 = A, d1 = E, e1 = G,
                a2 = A, b2 = A, c2 = D, d2 = B, e2 = F,
            )
        ),
        _69(
            Matrix3x5(
                winItemList = listOf(A),
                a0 = G, b0 = E, c0 = B, d0 = A, e0 = D,
                a1 = H, b1 = B, c1 = A, d1 = E, e1 = A,
                a2 = A, b2 = A, c2 = D, d2 = B, e2 = F,
            )
        ),
        _70(
            Matrix3x5(
                winItemList = listOf(A),
                a0 = G, b0 = E, c0 = B, d0 = A, e0 = D,
                a1 = H, b1 = B, c1 = D, d1 = A, e1 = E,
                a2 = A, b2 = A, c2 = A, d2 = A, e2 = F,
            )
        ),
        _71(
            Matrix3x5(
                winItemList = listOf(A),
                a0 = G, b0 = A, c0 = B, d0 = D, e0 = D,
                a1 = H, b1 = B, c1 = A, d1 = B, e1 = E,
                a2 = A, b2 = A, c2 = A, d2 = A, e2 = F,
            )
        ),
        _72(
            Matrix3x5(
                winItemList = listOf(A),
                a0 = G, b0 = E, c0 = B, d0 = A, e0 = A,
                a1 = H, b1 = B, c1 = D, d1 = A, e1 = E,
                a2 = A, b2 = A, c2 = A, d2 = A, e2 = F,
            )
        ),
        _73(
            Matrix3x5(
                winItemList = listOf(A),
                a0 = G, b0 = E, c0 = A, d0 = B, e0 = F,
                a1 = H, b1 = A, c1 = D, d1 = A, e1 = E,
                a2 = A, b2 = A, c2 = A, d2 = A, e2 = A,
            )
        ),
        _74(
            Matrix3x5(
                winItemList = listOf(A),
                a0 = G, b0 = E, c0 = C, d0 = B, e0 = F,
                a1 = H, b1 = G, c1 = A, d1 = A, e1 = A,
                a2 = A, b2 = A, c2 = D, d2 = E, e2 = A,
            )
        ),
        _75(
            Matrix3x5(
                winItemList = listOf(A),
                a0 = G, b0 = E, c0 = A, d0 = B, e0 = F,
                a1 = H, b1 = G, c1 = A, d1 = A, e1 = A,
                a2 = A, b2 = A, c2 = A, d2 = E, e2 = H,
            )
        ),
    }

    enum class WinColorful(
        override val matrix: Matrix3x5,
    ): CombinationMatrixEnum {
        _1(
            Matrix3x5(
                scheme = """
                    + + + - -
                    - - - - -
                    + + + - -
                """,
                winItemList = listOf(A, B),
                a0 = A, b0 = A, c0 = A, d0 = C, e0 = D,
                a1 = E, b1 = F, c1 = G, d1 = H, e1 = F,
                a2 = B, b2 = B, c2 = B, d2 = E, e2 = F,
            )
        ),
        _2(
            Matrix3x5(
                scheme = """
                    + + + - -
                    + + + - -
                    - - - - -
                """,
                winItemList = listOf(A, B),
                a0 = A, b0 = A, c0 = A, d0 = C, e0 = D,
                a1 = B, b1 = B, c1 = B, d1 = H, e1 = F,
                a2 = D, b2 = G, c2 = E, d2 = E, e2 = F,
            )
        ),
        _3(
            Matrix3x5(
                scheme = """
                    - - - - -
                    + + + - -
                    + + + - -
                """,
                winItemList = listOf(A, B),
                a0 = G, b0 = E, c0 = H, d0 = C, e0 = D,
                a1 = B, b1 = B, c1 = B, d1 = H, e1 = F,
                a2 = A, b2 = A, c2 = A, d2 = E, e2 = F,
            )
        ),
        _4(
            Matrix3x5(
                scheme = """
                    + + + - -
                    + + + - -
                    + + + - -
                """,
                winItemList = listOf(A, B, C),
                a0 = C, b0 = C, c0 = C, d0 = G, e0 = D,
                a1 = B, b1 = B, c1 = B, d1 = H, e1 = F,
                a2 = A, b2 = A, c2 = A, d2 = E, e2 = F,
            )
        ),
        _5(
            Matrix3x5(
                scheme = """
                    + + + + -
                    - - + - -
                    + + - - -
                """,
                winItemList = listOf(A, B),
                a0 = A, b0 = A, c0 = A, d0 = A, e0 = D,
                a1 = C, b1 = H, c1 = B, d1 = H, e1 = F,
                a2 = B, b2 = B, c2 = D, d2 = E, e2 = F,
            )
        ),
        _6(
            Matrix3x5(
                scheme = """
                    + + - - -
                    - - + - -
                    + + + - -
                """,
                winItemList = listOf(A, B),
                a0 = A, b0 = A, c0 = D, d0 = G, e0 = D,
                a1 = C, b1 = H, c1 = A, d1 = H, e1 = F,
                a2 = B, b2 = B, c2 = B, d2 = E, e2 = F,
            )
        ),
        _7(
            Matrix3x5(
                scheme = """
                    + + + + -
                    + + + - +
                    - - - - -
                """,
                winItemList = listOf(A, B),
                a0 = A, b0 = A, c0 = A, d0 = A, e0 = D,
                a1 = B, b1 = B, c1 = B, d1 = H, e1 = A,
                a2 = C, b2 = H, c2 = D, d2 = E, e2 = F,
            )
        ),
        _8(
            Matrix3x5(
                scheme = """
                    + + + - -
                    - - - + +
                    + + + + +
                """,
                winItemList = listOf(A, B),
                a0 = A, b0 = A, c0 = A, d0 = G, e0 = D,
                a1 = C, b1 = H, c1 = D, d1 = A, e1 = A,
                a2 = B, b2 = B, c2 = B, d2 = B, e2 = B,
            )
        ),
        _9(
            Matrix3x5(
                scheme = """
                    + + + + +
                    - - + + +
                    + + + + -
                """,
                winItemList = listOf(A, B),
                a0 = A, b0 = A, c0 = B, d0 = B, e0 = B,
                a1 = C, b1 = H, c1 = A, d1 = B, e1 = A,
                a2 = B, b2 = B, c2 = B, d2 = A, e2 = D,
            )
        ),
        _10(
            Matrix3x5(
                scheme = """
                    A - - B -
                    - A B B -
                    B B A B -
                """,
                winItemList = listOf(A, B),
                a0 = A, b0 = D, c0 = D, d0 = B, e0 = F,
                a1 = C, b1 = A, c1 = B, d1 = B, e1 = E,
                a2 = B, b2 = B, c2 = A, d2 = B, e2 = D,
            )
        ),
        _11(
            Matrix3x5(
                scheme = """
                    A A B B -
                    - B A A B
                    B - A - -
                """,
                winItemList = listOf(A, B),
                a0 = A, b0 = A, c0 = B, d0 = B, e0 = F,
                a1 = C, b1 = B, c1 = A, d1 = A, e1 = B,
                a2 = B, b2 = D, c2 = A, d2 = E, e2 = D,
            )
        ),
        _12(
            Matrix3x5(
                scheme = """
                    A A A A A
                    - - B - -
                    B B - B B
                """,
                winItemList = listOf(A, B),
                a0 = A, b0 = A, c0 = A, d0 = A, e0 = A,
                a1 = G, b1 = D, c1 = B, d1 = E, e1 = D,
                a2 = B, b2 = B, c2 = C, d2 = B, e2 = B,
            )
        ),
        _13(
            Matrix3x5(
                scheme = """
                    A A - - B
                    - - A A B
                    B B B B -
                """,
                winItemList = listOf(A, B),
                a0 = A, b0 = A, c0 = E, d0 = F, e0 = B,
                a1 = G, b1 = D, c1 = A, d1 = A, e1 = B,
                a2 = B, b2 = B, c2 = B, d2 = B, e2 = D,
            )
        ),
        _14(
            Matrix3x5(
                scheme = """
                    A A A - -
                    B B B - -
                    C C C - -
                """,
                winItemList = listOf(A, B, C),
                a0 = A, b0 = A, c0 = A, d0 = F, e0 = H,
                a1 = B, b1 = B, c1 = B, d1 = F, e1 = G,
                a2 = C, b2 = C, c2 = C, d2 = E, e2 = D,
            )
        ),
        _15(
            Matrix3x5(
                scheme = """
                    A A B - -
                    - B A B -
                    B - - A B
                """,
                winItemList = listOf(A, B),
                a0 = A, b0 = A, c0 = B, d0 = F, e0 = C,
                a1 = G, b1 = B, c1 = A, d1 = B, e1 = H,
                a2 = B, b2 = D, c2 = C, d2 = A, e2 = B,
            )
        ),
        _16(
            Matrix3x5(
                scheme = """
                    A A A - C
                    B B B A C
                    C C C C A
                """,
                winItemList = listOf(A, B, C),
                a0 = A, b0 = A, c0 = A, d0 = F, e0 = C,
                a1 = B, b1 = B, c1 = B, d1 = A, e1 = C,
                a2 = C, b2 = C, c2 = C, d2 = C, e2 = A,
            )
        ),
        _17(
            Matrix3x5(
                scheme = """
                    A A A A A
                    B B B - C
                    C C C C -
                """,
                winItemList = listOf(A, B, C),
                a0 = A, b0 = A, c0 = A, d0 = A, e0 = A,
                a1 = B, b1 = B, c1 = B, d1 = D, e1 = C,
                a2 = C, b2 = C, c2 = C, d2 = C, e2 = H,
            )
        ),
        _18(
            Matrix3x5(
                scheme = """
                    A A A A A
                    B B B C A
                    C C C B A
                """,
                winItemList = listOf(A, B, C),
                a0 = A, b0 = A, c0 = A, d0 = A, e0 = A,
                a1 = B, b1 = B, c1 = B, d1 = C, e1 = A,
                a2 = C, b2 = C, c2 = C, d2 = B, e2 = A,
            )
        ),
        _19(
            Matrix3x5(
                scheme = """
                    A A - - -
                    B B A B B
                    - - B - -
                """,
                winItemList = listOf(A, B),
                a0 = A, b0 = A, c0 = D, d0 = E, e0 = G,
                a1 = B, b1 = B, c1 = A, d1 = B, e1 = B,
                a2 = C, b2 = C, c2 = B, d2 = E, e2 = F,
            )
        ),
        _20(
            Matrix3x5(
                scheme = """
                    A A B B B
                    B B A - -
                    - - - A -
                """,
                winItemList = listOf(A, B),
                a0 = A, b0 = A, c0 = B, d0 = B, e0 = B,
                a1 = B, b1 = B, c1 = A, d1 = E, e1 = H,
                a2 = C, b2 = G, c2 = H, d2 = A, e2 = F,
            )
        ),
        _21(
            Matrix3x5(
                scheme = """
                    - - - - -
                    B B A A A
                    A A B B B
                """,
                winItemList = listOf(A, B),
                a0 = C, b0 = G, c0 = H, d0 = E, e0 = F,
                a1 = B, b1 = B, c1 = A, d1 = A, e1 = A,
                a2 = A, b2 = A, c2 = B, d2 = B, e2 = B,
            )
        ),
        _22(
            Matrix3x5(
                scheme = """
                    A A A - -
                    B B B B B
                    C C C - -
                """,
                winItemList = listOf(A, B, C),
                a0 = A, b0 = A, c0 = A, d0 = E, e0 = F,
                a1 = B, b1 = B, c1 = B, d1 = B, e1 = B,
                a2 = C, b2 = C, c2 = C, d2 = H, e2 = D,
            )
        ),
        _23(
            Matrix3x5(
                scheme = """
                    A A B - -
                    - B A - -
                    B - - - -
                """,
                winItemList = listOf(A, B),
                a0 = A, b0 = A, c0 = B, d0 = E, e0 = F,
                a1 = C, b1 = B, c1 = A, d1 = H, e1 = E,
                a2 = B, b2 = D, c2 = E, d2 = H, e2 = D,
            )
        ),
        _24(
            Matrix3x5(
                scheme = """
                    A A A A -
                    B B B - A
                    B B - - -
                """,
                winItemList = listOf(A, B),
                a0 = A, b0 = A, c0 = A, d0 = A, e0 = F,
                a1 = B, b1 = B, c1 = B, d1 = H, e1 = A,
                a2 = B, b2 = B, c2 = E, d2 = H, e2 = D,
            )
        ),
        _25(
            Matrix3x5(
                scheme = """
                    A - - - -
                    - A B - -
                    B B A - -
                """,
                winItemList = listOf(A, B),
                a0 = A, b0 = C, c0 = D, d0 = E, e0 = F,
                a1 = D, b1 = A, c1 = B, d1 = H, e1 = C,
                a2 = B, b2 = B, c2 = A, d2 = D, e2 = D,
            )
        ),
        _26(
            Matrix3x5(
                scheme = """
                    A A A - -
                    - - - A -
                    B B B - -
                """,
                winItemList = listOf(A, B),
                a0 = A, b0 = A, c0 = A, d0 = E, e0 = F,
                a1 = D, b1 = D, c1 = C, d1 = A, e1 = C,
                a2 = B, b2 = B, c2 = B, d2 = H, e2 = D,
            )
        ),
        _27(
            Matrix3x5(
                scheme = """
                    A A A - -
                    B B - - -
                    - - B - -
                """,
                winItemList = listOf(A, B),
                a0 = A, b0 = A, c0 = A, d0 = E, e0 = F,
                a1 = B, b1 = B, c1 = C, d1 = E, e1 = C,
                a2 = D, b2 = C, c2 = B, d2 = H, e2 = D,
            )
        ),
        _28(
            Matrix3x5(
                scheme = """
                    A A A - -
                    B B B B -
                    C C C - -
                """,
                winItemList = listOf(A, B, C),
                a0 = A, b0 = A, c0 = A, d0 = E, e0 = F,
                a1 = B, b1 = B, c1 = B, d1 = B, e1 = H,
                a2 = C, b2 = C, c2 = C, d2 = H, e2 = D,
            )
        ),
        _29(
            Matrix3x5(
                scheme = """
                    A A A A A
                    - - - A A
                    B B B - -
                """,
                winItemList = listOf(A, B),
                a0 = A, b0 = A, c0 = A, d0 = A, e0 = A,
                a1 = E, b1 = F, c1 = G, d1 = A, e1 = A,
                a2 = B, b2 = B, c2 = B, d2 = H, e2 = D,
            )
        ),
        _30(
            Matrix3x5(
                scheme = """
                    A A - - -
                    A A A B -
                    B B B - B
                """,
                winItemList = listOf(A, B),
                a0 = A, b0 = A, c0 = C, d0 = D, e0 = E,
                a1 = A, b1 = A, c1 = A, d1 = B, e1 = G,
                a2 = B, b2 = B, c2 = B, d2 = H, e2 = B,
            )
        ),
    }

    enum class WinWithWild(
        override val matrix: Matrix3x5,
    ): CombinationMatrixEnum {
        _1(
            Matrix3x5(
                scheme = """
                    A A - - -
                    - - W - -
                    B B - - -
                """,
                winItemList = listOf(A, B),
                a0 = A, b0 = A, c0 = F, d0 = C, e0 = D,
                a1 = E, b1 = F, c1 = WILD, d1 = H, e1 = F,
                a2 = B, b2 = B, c2 = D, d2 = E, e2 = F,
            )
        ),
        _2(
            Matrix3x5(
                scheme = """
                    A A - B -
                    - - W - B
                    B B - - -
                """,
                winItemList = listOf(A, B),
                a0 = A, b0 = A, c0 = F,    d0 = B, e0 = D,
                a1 = E, b1 = F, c1 = WILD, d1 = H, e1 = B,
                a2 = B, b2 = B, c2 = D,    d2 = E, e2 = F,
            )
        ),
        _3(
            Matrix3x5(
                scheme = """
                    W - - - -
                    - A - - -
                    B B W B B
                """,
                winItemList = listOf(A, B),
                a0 = WILD, b0 = D, c0 = F,    d0 = F, e0 = D,
                a1 = E,    b1 = A, c1 = E,    d1 = H, e1 = G,
                a2 = B,    b2 = B, c2 = WILD, d2 = B, e2 = B,
            )
        ),
        _4(
            Matrix3x5(
                scheme = """
                    A A A W -
                    - - B B -
                    B B - - -
                """,
                winItemList = listOf(A, B),
                a0 = A, b0 = A, c0 = A, d0 = WILD, e0 = D,
                a1 = E, b1 = D, c1 = B, d1 = B,    e1 = G,
                a2 = B, b2 = B, c2 = G, d2 = E,    e2 = C,
            )
        ),
        _5(
            Matrix3x5(
                scheme = """
                    A A A A A
                    - - W - -
                    B B B B B
                """,
                winItemList = listOf(A, B),
                a0 = A, b0 = A, c0 = A,    d0 = A, e0 = A,
                a1 = E, b1 = D, c1 = WILD, d1 = C, e1 = G,
                a2 = B, b2 = B, c2 = B,    d2 = B, e2 = B,
            )
        ),
        _6(
            Matrix3x5(
                scheme = """
                    A - B - -
                    - W - - -
                    B - A - -
                """,
                winItemList = listOf(A, B),
                a0 = A, b0 = D,    c0 = B, d0 = H, e0 = E,
                a1 = E, b1 = WILD, c1 = G, d1 = C, e1 = G,
                a2 = B, b2 = C,    c2 = A, d2 = C, e2 = E,
            )
        ),
        _7(
            Matrix3x5(
                scheme = """
                    A A - C -
                    B B W B B
                    C C - A -
                """,
                winItemList = listOf(A, B, C),
                a0 = A, b0 = A, c0 = G,    d0 = C, e0 = E,
                a1 = B, b1 = B, c1 = WILD, d1 = B, e1 = B,
                a2 = C, b2 = C, c2 = H,    d2 = A, e2 = E,
            )
        ),
        _8(
            Matrix3x5(
                scheme = """
                    A A W - -
                    B B B - -
                    C C W - -
                """,
                winItemList = listOf(A, B, C),
                a0 = A, b0 = A, c0 = WILD, d0 = D, e0 = E,
                a1 = B, b1 = B, c1 = B,    d1 = G, e1 = H,
                a2 = C, b2 = C, c2 = WILD, d2 = F, e2 = E,
            )
        ),
        _9(
            Matrix3x5(
                scheme = """
                    - - A - -
                    - W - - -
                    A - - - -
                """,
                winItemList = listOf(A),
                a0 = B, b0 = D,    c0 = A, d0 = D, e0 = E,
                a1 = B, b1 = WILD, c1 = B, d1 = G, e1 = H,
                a2 = A, b2 = C,    c2 = G, d2 = F, e2 = E,
            )
        ),
        _10(
            Matrix3x5(
                scheme = """
                    A W A - -
                    - - - - -
                    - - - - -
                """,
                winItemList = listOf(A),
                a0 = A, b0 = WILD, c0 = A, d0 = D, e0 = E,
                a1 = B, b1 = D,    c1 = B, d1 = G, e1 = H,
                a2 = E, b2 = C,    c2 = G, d2 = F, e2 = E,
            )
        ),
        _11(
            Matrix3x5(
                scheme = """
                    - - - - A
                    A A W A -
                    - - - - A
                """,
                winItemList = listOf(A),
                a0 = B, b0 = D, c0 = E,    d0 = G, e0 = A,
                a1 = A, b1 = A, c1 = WILD, d1 = A, e1 = H,
                a2 = E, b2 = C, c2 = G,    d2 = F, e2 = A,
            )
        ),
        _12(
            Matrix3x5(
                scheme = """
                    - - - - -
                    W A A - -
                    - - - - -
                """,
                winItemList = listOf(A),
                a0 = B, b0 = D,    c0 = E, d0 = G, e0 = E,
                a1 = WILD, b1 = A, c1 = A, d1 = B, e1 = H,
                a2 = E, b2 = C,    c2 = G, d2 = F, e2 = D,
            )
        ),
        _13(
            Matrix3x5(
                scheme = """
                    - - - - -
                    - - - - -
                    W A A - -
                """,
                winItemList = listOf(A),
                a0 = B,    b0 = D, c0 = E, d0 = G, e0 = E,
                a1 = D,    b1 = B, c1 = E, d1 = B, e1 = H,
                a2 = WILD, b2 = A, c2 = A, d2 = F, e2 = D,
            )
        ),
        _14(
            Matrix3x5(
                scheme = """
                    W A A - -
                    - - - - -
                    - - - - -
                """,
                winItemList = listOf(A),
                a0 = WILD, b0 = A, c0 = A, d0 = G, e0 = E,
                a1 = D,    b1 = B, c1 = E, d1 = B, e1 = H,
                a2 = G,    b2 = F, c2 = C, d2 = F, e2 = D,
            )
        ),
        _15(
            Matrix3x5(
                scheme = """
                    - - - A -
                    W A A - A
                    - - - A -
                """,
                winItemList = listOf(A),
                a0 = B,    b0 = D, c0 = G, d0 = A, e0 = E,
                a1 = WILD, b1 = A, c1 = A, d1 = B, e1 = A,
                a2 = G,    b2 = F, c2 = C, d2 = A, e2 = D,
            )
        ),
        _16(
            Matrix3x5(
                scheme = """
                    - - - - -
                    - - W A A
                    A A - - -
                """,
                winItemList = listOf(A),
                a0 = B, b0 = D, c0 = G,    d0 = G, e0 = E,
                a1 = D, b1 = E, c1 = WILD, d1 = A, e1 = A,
                a2 = A, b2 = A, c2 = C,    d2 = B, e2 = D,
            )
        ),
        _17(
            Matrix3x5(
                scheme = """
                    - - - - -
                    A A - - -
                    B B W - -
                """,
                winItemList = listOf(A, B),
                a0 = D, b0 = D, c0 = G,    d0 = G, e0 = E,
                a1 = A, b1 = A, c1 = E,    d1 = E, e1 = H,
                a2 = B, b2 = B, c2 = WILD, d2 = C, e2 = D,
            )
        ),
        _18(
            Matrix3x5(
                scheme = """
                    A A - - -
                    - - A - -
                    - - W - -
                """,
                winItemList = listOf(A),
                a0 = A, b0 = A, c0 = G,    d0 = G, e0 = E,
                a1 = D, b1 = C, c1 = A,    d1 = E, e1 = H,
                a2 = G, b2 = B, c2 = WILD, d2 = C, e2 = D,
            )
        ),
        _19(
            Matrix3x5(
                scheme = """
                    A - - - A
                    - A - A -
                    - - W - -
                """,
                winItemList = listOf(A),
                a0 = A, b0 = B, c0 = G,    d0 = G, e0 = A,
                a1 = D, b1 = A, c1 = D,    d1 = A, e1 = H,
                a2 = G, b2 = B, c2 = WILD, d2 = C, e2 = D,
            )
        ),
        _20(
            Matrix3x5(
                scheme = """
                    A - C C C
                    B W B - -
                    C - A - -
                """,
                winItemList = listOf(A, B, C),
                a0 = A, b0 = E,    c0 = C, d0 = C, e0 = C,
                a1 = B, b1 = WILD, c1 = B, d1 = D, e1 = H,
                a2 = C, b2 = D,    c2 = A, d2 = C, e2 = F,
            )
        ),
        _21(
            Matrix3x5(
                scheme = """
                    - - - - -
                    A A A W -
                    - - - - -
                """,
                winItemList = listOf(A),
                a0 = B, b0 = E, c0 = C, d0 = C,    e0 = C,
                a1 = A, b1 = A, c1 = A, d1 = WILD, e1 = H,
                a2 = C, b2 = D, c2 = B, d2 = C,    e2 = F,
            )
        ),
        _22(
            Matrix3x5(
                scheme = """
                    - - - - A
                    A A A A W
                    - - - - A
                """,
                winItemList = listOf(A),
                a0 = B, b0 = E, c0 = G, d0 = H, e0 = A,
                a1 = A, b1 = A, c1 = A, d1 = A, e1 = WILD,
                a2 = C, b2 = D, c2 = F, d2 = C, e2 = A,
            )
        ),
        _23(
            Matrix3x5(
                scheme = """
                    A A W A A
                    - B - B -
                    B - - - B
                """,
                winItemList = listOf(A, B),
                a0 = A, b0 = A, c0 = WILD, d0 = A, e0 = A,
                a1 = H, b1 = B, c1 = E,    d1 = B, e1 = D,
                a2 = B, b2 = D, c2 = F,    d2 = C, e2 = B,
            )
        ),
        _24(
            Matrix3x5(
                scheme = """
                    W - - - -
                    - A - - -
                    - - W - -
                """,
                winItemList = listOf(A),
                a0 = WILD, b0 = D, c0 = H,    d0 = E, e0 = C,
                a1 = H,    b1 = A, c1 = E,    d1 = B, e1 = D,
                a2 = B,    b2 = D, c2 = WILD, d2 = C, e2 = B,
            )
        ),
        _25(
            Matrix3x5(
                scheme = """
                    A A A - -
                    B B B W A
                    - - - - B
                """,
                winItemList = listOf(A, B),
                a0 = A, b0 = A, c0 = A, d0 = E,    e0 = C,
                a1 = B, b1 = B, c1 = B, d1 = WILD, e1 = A,
                a2 = E, b2 = C, c2 = D, d2 = C,    e2 = B,
            )
        ),
        _26(
            Matrix3x5(
                scheme = """
                    A W A - -
                    - - - A -
                    - - - - A
                """,
                winItemList = listOf(A),
                a0 = A, b0 = WILD, c0 = A, d0 = E, e0 = C,
                a1 = B, b1 = H,    c1 = G, d1 = A, e1 = D,
                a2 = E, b2 = C,    c2 = D, d2 = C, e2 = A,
            )
        ),
        _27(
            Matrix3x5(
                scheme = """
                    A - C - -
                    B W B - -
                    C - A - -
                """,
                winItemList = listOf(A, B, C),
                a0 = A, b0 = E,    c0 = C, d0 = E, e0 = C,
                a1 = B, b1 = WILD, c1 = B, d1 = H, e1 = D,
                a2 = C, b2 = D,    c2 = A, d2 = G, e2 = E,
            )
        ),
        _28(
            Matrix3x5(
                scheme = """
                    A - - - A
                    - A - - A
                    - - A W W
                """,
                winItemList = listOf(A),
                a0 = A, b0 = E, c0 = C, d0 = E,    e0 = A,
                a1 = B, b1 = A, c1 = B, d1 = H,    e1 = A,
                a2 = C, b2 = D, c2 = A, d2 = WILD, e2 = WILD,
            )
        ),
        _29(
            Matrix3x5(
                scheme = """
                    - - A - -
                    A A - W A
                    - - A - -
                """,
                winItemList = listOf(A),
                a0 = B, b0 = E, c0 = A, d0 = E,    e0 = D,
                a1 = A, b1 = A, c1 = B, d1 = WILD, e1 = A,
                a2 = C, b2 = D, c2 = A, d2 = C,    e2 = B,
            )
        ),
        _30(
            Matrix3x5(
                scheme = """
                    - - A W -
                    - A - A -
                    A - - A A
                """,
                winItemList = listOf(A),
                a0 = B, b0 = E, c0 = A, d0 = WILD, e0 = D,
                a1 = B, b1 = A, c1 = B, d1 = A,    e1 = B,
                a2 = A, b2 = D, c2 = H, d2 = A,    e2 = A,
            )
        ),
        _31(
            Matrix3x5(
                scheme = """
                    W - - - W
                    - A - A -
                    - - W - -
                """,
                winItemList = listOf(A),
                a0 = WILD, b0 = E, c0 = F,    d0 = D, e0 = WILD,
                a1 = B,    b1 = A, c1 = B,    d1 = A, e1 = B,
                a2 = D,    b2 = H, c2 = WILD, d2 = G, e2 = D,
            )
        ),
        _32(
            Matrix3x5(
                scheme = """
                    A - - - A
                    - W - W -
                    - - A - -
                """,
                winItemList = listOf(A),
                a0 = A, b0 = E,    c0 = F, d0 = D,    e0 = A,
                a1 = E, b1 = WILD, c1 = G, d1 = WILD, e1 = B,
                a2 = D, b2 = H,    c2 = A, d2 = C,    e2 = D,
            )
        ),
        _33(
            Matrix3x5(
                scheme = """
                    A A - B B
                    - - W - -
                    B B - A A
                """,
                winItemList = listOf(A, B),
                a0 = A, b0 = A, c0 = F,    d0 = B, e0 = B,
                a1 = E, b1 = F, c1 = WILD, d1 = E, e1 = C,
                a2 = B, b2 = B, c2 = F,    d2 = A, e2 = A,
            )
        ),
        _34(
            Matrix3x5(
                scheme = """
                    A A W C C
                    B W B A -
                    C - - - A
                """,
                winItemList = listOf(A, B, C),
                a0 = A, b0 = A,    c0 = WILD, d0 = C, e0 = C,
                a1 = B, b1 = WILD, c1 = B,    d1 = A, e1 = D,
                a2 = C, b2 = E,    c2 = F,    d2 = H, e2 = A,
            )
        ),
        _35(
            Matrix3x5(
                scheme = """
                    - - - W -
                    W A A - A
                    B B B W -
                """,
                winItemList = listOf(A, B),
                a0 = C,    b0 = D, c0 = E, d0 = WILD, e0 = C,
                a1 = WILD, b1 = A, c1 = A, d1 = H,    e1 = A,
                a2 = B,    b2 = B, c2 = B, d2 = WILD, e2 = D,
            )
        ),
        _36(
            Matrix3x5(
                scheme = """
                    W - A - A
                    - A - W -
                    W - A - A
                """,
                winItemList = listOf(A, B),
                a0 = WILD, b0 = D, c0 = A, d0 = G,    e0 = A,
                a1 = E,    b1 = A, c1 = C, d1 = WILD, e1 = D,
                a2 = WILD, b2 = B, c2 = A, d2 = H,    e2 = A,
            )
        ),
    }
}