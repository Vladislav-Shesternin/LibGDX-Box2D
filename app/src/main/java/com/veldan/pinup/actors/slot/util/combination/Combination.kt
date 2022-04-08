package com.veldan.pinup.actors.slot.util.combination

import com.veldan.pinup.actors.slot.util.combination.Matrix3x3.Item.*

interface CombinationMatrixEnum {
    val matrix: Matrix3x3
}


/**Комбинация Рандомная С WILD*/
enum class CombinationRandomWithWild(
    override val matrix: Matrix3x3,
): CombinationMatrixEnum {
    _1(
        Matrix3x3(
            a0 = WILD, b0 = A, c0 = B,
            a1 = C, b1 = D, c1 = E,
            a2 = F, b2 = G, c2 = H,
        )
    ),
    _2(
        Matrix3x3(
            a0 = A, b0 = WILD, c0 = B,
            a1 = C, b1 = D, c1 = E,
            a2 = F, b2 = G, c2 = H,
        )
    ),
    _3(
        Matrix3x3(
            a0 = A, b0 = B, c0 = WILD,
            a1 = C, b1 = D, c1 = E,
            a2 = F, b2 = G, c2 = H,
        )
    ),
    _4(
        Matrix3x3(
            a0 = A, b0 = B, c0 = C,
            a1 = WILD, b1 = D, c1 = E,
            a2 = F, b2 = G, c2 = H,
        )
    ),
    _5(
        Matrix3x3(
            a0 = A, b0 = B, c0 = C,
            a1 = D, b1 = WILD, c1 = E,
            a2 = F, b2 = G, c2 = H,
        )
    ),
    _6(
        Matrix3x3(
            a0 = A, b0 = B, c0 = C,
            a1 = D, b1 = E, c1 = WILD,
            a2 = F, b2 = G, c2 = H,
        )
    ),
    _7(
        Matrix3x3(
            a0 = A, b0 = B, c0 = C,
            a1 = D, b1 = E, c1 = F,
            a2 = WILD, b2 = G, c2 = H,
        )
    ),
    _8(
        Matrix3x3(
            a0 = A, b0 = B, c0 = C,
            a1 = D, b1 = E, c1 = F,
            a2 = G, b2 = WILD, c2 = H,
        )
    ),
    _9(
        Matrix3x3(
            a0 = A, b0 = B, c0 = C,
            a1 = D, b1 = E, c1 = F,
            a2 = G, b2 = H, c2 = WILD,
        )
    ),
}

/**Комбинация Рандомная*/
enum class CombinationRandom(
    override val matrix: Matrix3x3,
): CombinationMatrixEnum {
    _1(
        Matrix3x3(
            a0 = A, b0 = B, c0 = C,
            a1 = D, b1 = E, c1 = F,
            a2 = G, b2 = H, c2 = A,
        )
    ),
    _2(
        Matrix3x3(
            a0 = B, b0 = A, c0 = C,
            a1 = D, b1 = E, c1 = F,
            a2 = G, b2 = H, c2 = A,
        )
    ),
    _3(
        Matrix3x3(
            a0 = C, b0 = B, c0 = B,
            a1 = D, b1 = E, c1 = F,
            a2 = G, b2 = H, c2 = A,
        )
    ),
    _4(
        Matrix3x3(
            a0 = D, b0 = B, c0 = C,
            a1 = A, b1 = E, c1 = F,
            a2 = G, b2 = H, c2 = A,
        )
    ),
    _5(
        Matrix3x3(
            a0 = E, b0 = B, c0 = C,
            a1 = D, b1 = A, c1 = F,
            a2 = G, b2 = H, c2 = A,
        )
    ),
    _6(
        Matrix3x3(
            a0 = F, b0 = B, c0 = C,
            a1 = D, b1 = E, c1 = A,
            a2 = G, b2 = H, c2 = A,
        )
    ),
    _7(
        Matrix3x3(
            a0 = G, b0 = B, c0 = C,
            a1 = D, b1 = E, c1 = F,
            a2 = A, b2 = H, c2 = A,
        )
    ),
    _8(
        Matrix3x3(
            a0 = H, b0 = B, c0 = C,
            a1 = D, b1 = E, c1 = F,
            a2 = G, b2 = A, c2 = A,
        )
    ),
    _9(
        Matrix3x3(
            a0 = A, b0 = A, c0 = C,
            a1 = A, b1 = E, c1 = F,
            a2 = G, b2 = H, c2 = A,
        )
    ),
    _10(
        Matrix3x3(
            a0 = A, b0 = B, c0 = C,
            a1 = D, b1 = E, c1 = A,
            a2 = A, b2 = H, c2 = A,
        )
    ),
    _11(
        Matrix3x3(
            a0 = A, b0 = B, c0 = A,
            a1 = D, b1 = E, c1 = F,
            a2 = F, b2 = H, c2 = A,
        )
    ),
    _12(
        Matrix3x3(
            a0 = A, b0 = B, c0 = F,
            a1 = D, b1 = D, c1 = F,
            a2 = F, b2 = H, c2 = A,
        )
    ),
    _13(
        Matrix3x3(
            a0 = A, b0 = B, c0 = C,
            a1 = D, b1 = E, c1 = F,
            a2 = G, b2 = B, c2 = B,
        )
    ),
    _14(
        Matrix3x3(
            a0 = A, b0 = G, c0 = C,
            a1 = D, b1 = E, c1 = F,
            a2 = G, b2 = G, c2 = B,
        )
    ),
    _15(
        Matrix3x3(
            a0 = A, b0 = G, c0 = C,
            a1 = A, b1 = E, c1 = G,
            a2 = A, b2 = G, c2 = C,
        )
    ),
    _16(
        Matrix3x3(
            a0 = G, b0 = G, c0 = C,
            a1 = D, b1 = E, c1 = A,
            a2 = G, b2 = G, c2 = B,
        )
    ),
    _17(
        Matrix3x3(
            a0 = A, b0 = G, c0 = C,
            a1 = F, b1 = E, c1 = F,
            a2 = G, b2 = G, c2 = B,
        )
    ),
    _18(
        Matrix3x3(
            a0 = C, b0 = G, c0 = C,
            a1 = D, b1 = E, c1 = F,
            a2 = C, b2 = G, c2 = B,
        )
    ),
    _19(
        Matrix3x3(
            a0 = A, b0 = G, c0 = C,
            a1 = F, b1 = E, c1 = F,
            a2 = G, b2 = G, c2 = A,
        )
    ),
    _20(
        Matrix3x3(
            a0 = A, b0 = D, c0 = B,
            a1 = D, b1 = A, c1 = B,
            a2 = A, b2 = D, c2 = B,
        )
    ),
}

/**Комбинация Победная Не Пересекающаяся*/
enum class CombinationWinNonIntersectingColorful(
    override val matrix: Matrix3x3,
): CombinationMatrixEnum {
    _1(
        Matrix3x3(
            winItemList = listOf(A, B, C),

            a0 = A, b0 = A, c0 = A,
            a1 = B, b1 = B, c1 = B,
            a2 = C, b2 = C, c2 = C,
        )
    ),
    _2(
        Matrix3x3(
            winItemList = listOf(A, B),

            a0 = A, b0 = A, c0 = C,
            a1 = D, b1 = E, c1 = A,
            a2 = B, b2 = B, c2 = B,
        )
    ),
    _3(
        Matrix3x3(
            winItemList = listOf(A, B),

            a0 = B, b0 = B, c0 = B,
            a1 = D, b1 = E, c1 = A,
            a2 = A, b2 = A, c2 = H,
        )
    ),
    _4(
        Matrix3x3(
            winItemList = listOf(A, B),

            a0 = A, b0 = A, c0 = A,
            a1 = B, b1 = B, c1 = C,
            a2 = D, b2 = E, c2 = B,
        )
    ),
    _5(
        Matrix3x3(
            winItemList = listOf(A, B),

            a0 = D, b0 = E, c0 = A,
            a1 = A, b1 = A, c1 = F,
            a2 = B, b2 = B, c2 = B,
        )
    ),
    _6(
        Matrix3x3(
            winItemList = listOf(A, B),

            a0 = A, b0 = A, c0 = A,
            a1 = D, b1 = E, c1 = C,
            a2 = B, b2 = B, c2 = B,
        )
    ),
    _7(
        Matrix3x3(
            winItemList = listOf(A, B),

            a0 = F, b0 = C, c0 = C,
            a1 = A, b1 = A, c1 = A,
            a2 = B, b2 = B, c2 = B,
        )
    ),
    _8(
        Matrix3x3(
            winItemList = listOf(A, B),

            a0 = A, b0 = A, c0 = A,
            a1 = B, b1 = B, c1 = B,
            a2 = G, b2 = H, c2 = D,
        )
    ),
    _9(
        Matrix3x3(
            winItemList = listOf(A, B),

            a0 = D, b0 = B, c0 = A,
            a1 = A, b1 = A, c1 = F,
            a2 = G, b2 = H, c2 = A,
        )
    ),
    _10(
        Matrix3x3(
            winItemList = listOf(A),

            a0 = A, b0 = A, c0 = A,
            a1 = D, b1 = E, c1 = F,
            a2 = G, b2 = H, c2 = B,
        )
    ),
    _11(
        Matrix3x3(
            winItemList = listOf(A),

            a0 = D, b0 = B, c0 = C,
            a1 = A, b1 = A, c1 = A,
            a2 = G, b2 = H, c2 = F,
        )
    ),
    _12(
        Matrix3x3(
            winItemList = listOf(A),

            a0 = G, b0 = B, c0 = C,
            a1 = D, b1 = E, c1 = F,
            a2 = A, b2 = A, c2 = A,
        )
    ),
    _13(
        Matrix3x3(
            winItemList = listOf(A, B),

            a0 = A, b0 = B, c0 = C,
            a1 = D, b1 = A, c1 = F,
            a2 = G, b2 = H, c2 = A,
        )
    ),
    _14(
        Matrix3x3(
            winItemList = listOf(A, B),

            a0 = G, b0 = B, c0 = A,
            a1 = D, b1 = A, c1 = F,
            a2 = A, b2 = H, c2 = C,
        )
    ),
    _15(
        Matrix3x3(
            winItemList = listOf(A),

            a0 = A, b0 = A, c0 = B,
            a1 = D, b1 = D, c1 = A,
            a2 = C, b2 = H, c2 = C,
        )
    ),
    _16(
        Matrix3x3(
            winItemList = listOf(A),

            a0 = B, b0 = D, c0 = B,
            a1 = A, b1 = A, c1 = D,
            a2 = C, b2 = H, c2 = A,
        )
    ),
    _17(
        Matrix3x3(
            winItemList = listOf(B),

            a0 = G, b0 = D, c0 = D,
            a1 = A, b1 = A, c1 = B,
            a2 = B, b2 = B, c2 = A,
        )
    ),

    _18(
        Matrix3x3(
            winItemList = listOf(A, B),

            a0 = B, b0 = B, c0 = A,
            a1 = A, b1 = A, c1 = B,
            a2 = D, b2 = C, c2 = D,
        )
    ),

    _19(
        Matrix3x3(
            winItemList = listOf(A),

            a0 = B, b0 = C, c0 = A,
            a1 = A, b1 = A, c1 = B,
            a2 = D, b2 = C, c2 = D,
        )
    ),
    _20(
        Matrix3x3(
            winItemList = listOf(A),

            a0 = B, b0 = C, c0 = B,
            a1 = F, b1 = E, c1 = A,
            a2 = A, b2 = A, c2 = D,
        )
    ),

}

/**Комбинация Победная Пересекающаяся Одноцветная*/
enum class CombinationWinIntersectingMonochrome(
    override val matrix: Matrix3x3,
): CombinationMatrixEnum {
    _1(
        Matrix3x3(
            winItemList = listOf(A),

            a0 = A, b0 = A, c0 = C,
            a1 = A, b1 = A, c1 = A,
            a2 = G, b2 = H, c2 = F,
        )
    ),
    _2(
        Matrix3x3(
            winItemList = listOf(A),

            a0 = D, b0 = B, c0 = C,
            a1 = A, b1 = A, c1 = A,
            a2 = A, b2 = A, c2 = G,
        )
    ),
    _3(
        Matrix3x3(
            winItemList = listOf(A),

            a0 = A, b0 = A, c0 = A,
            a1 = A, b1 = A, c1 = F,
            a2 = A, b2 = A, c2 = A,
        )
    ),
    _4(
        Matrix3x3(
            winItemList = listOf(A),

            a0 = A, b0 = A, c0 = C,
            a1 = D, b1 = E, c1 = A,
            a2 = A, b2 = A, c2 = F,
        )
    ),
    _5(
        Matrix3x3(
            winItemList = listOf(A),

            a0 = A, b0 = A, c0 = C,
            a1 = A, b1 = A, c1 = A,
            a2 = A, b2 = A, c2 = G,
        )
    ),
    _6(
        Matrix3x3(
            winItemList = listOf(A),

            a0 = A, b0 = B, c0 = A,
            a1 = D, b1 = A, c1 = F,
            a2 = A, b2 = H, c2 = A,
        )
    ),
    _7(
        Matrix3x3(
            winItemList = listOf(A),

            a0 = A, b0 = A, c0 = A,
            a1 = D, b1 = A, c1 = F,
            a2 = G, b2 = H, c2 = A,
        )
    ),
    _8(
        Matrix3x3(
            winItemList = listOf(A),

            a0 = A, b0 = A, c0 = A,
            a1 = D, b1 = A, c1 = F,
            a2 = A, b2 = H, c2 = E,
        )
    ),
    _9(
        Matrix3x3(
            winItemList = listOf(A),

            a0 = E, b0 = B, c0 = A,
            a1 = D, b1 = A, c1 = F,
            a2 = A, b2 = A, c2 = A,
        )
    ),
    _10(
        Matrix3x3(
            winItemList = listOf(A),

            a0 = A, b0 = A, c0 = A,
            a1 = D, b1 = A, c1 = F,
            a2 = A, b2 = A, c2 = A,
        )
    ),
    _11(
        Matrix3x3(
            winItemList = listOf(A),

            a0 = A, b0 = B, c0 = C,
            a1 = D, b1 = A, c1 = F,
            a2 = A, b2 = A, c2 = A,
        )
    ),
    _12(
        Matrix3x3(
            winItemList = listOf(A),

            a0 = A, b0 = A, c0 = A,
            a1 = D, b1 = A, c1 = F,
            a2 = A, b2 = A, c2 = A,
        )
    ),
    _13(
        Matrix3x3(
            winItemList = listOf(A),

            a0 = A, b0 = B, c0 = C,
            a1 = A, b1 = A, c1 = A,
            a2 = G, b2 = H, c2 = A,
        )
    ),
    _14(
        Matrix3x3(
            winItemList = listOf(A),

            a0 = E, b0 = B, c0 = A,
            a1 = A, b1 = A, c1 = A,
            a2 = A, b2 = H, c2 = D,
        )
    ),
    _15(
        Matrix3x3(
            winItemList = listOf(A),

            a0 = A, b0 = A, c0 = A,
            a1 = A, b1 = A, c1 = A,
            a2 = G, b2 = H, c2 = A,
        )
    ),
    _16(
        Matrix3x3(
            winItemList = listOf(A),

            a0 = A, b0 = A, c0 = A,
            a1 = A, b1 = A, c1 = A,
            a2 = A, b2 = H, c2 = F,
        )
    ),
    _17(
        Matrix3x3(
            winItemList = listOf(A),

            a0 = C, b0 = B, c0 = A,
            a1 = A, b1 = A, c1 = A,
            a2 = A, b2 = A, c2 = A,
        )
    ),
    _18(
        Matrix3x3(
            winItemList = listOf(A),

            a0 = A, b0 = A, c0 = A,
            a1 = A, b1 = A, c1 = A,
            a2 = A, b2 = A, c2 = A,
        )
    ),
    _19(
        Matrix3x3(
            winItemList = listOf(A),

            a0 = A, b0 = B, c0 = C,
            a1 = A, b1 = A, c1 = A,
            a2 = A, b2 = A, c2 = A,
        )
    ),
    _20(
        Matrix3x3(
            winItemList = listOf(A),

            a0 = A, b0 = A, c0 = A,
            a1 = D, b1 = A, c1 = A,
            a2 = A, b2 = H, c2 = F,
        )
    ),
    _21(
        Matrix3x3(
            winItemList = listOf(A),

            a0 = G, b0 = B, c0 = A,
            a1 = D, b1 = A, c1 = A,
            a2 = A, b2 = A, c2 = F,
        )
    ),
    _22(
        Matrix3x3(
            winItemList = listOf(A),

            a0 = A, b0 = B, c0 = C,
            a1 = D, b1 = A, c1 = A,
            a2 = A, b2 = A, c2 = A,
        )
    ),
    _23(
        Matrix3x3(
            winItemList = listOf(A),

            a0 = A, b0 = A, c0 = C,
            a1 = D, b1 = A, c1 = A,
            a2 = G, b2 = H, c2 = A,
        )
    ),
    _24(
        Matrix3x3(
            winItemList = listOf(A),

            a0 = A, b0 = B, c0 = A,
            a1 = A, b1 = A, c1 = F,
            a2 = A, b2 = H, c2 = A,
        )
    ),
}

/**Комбинация Победная Пересекающаяся Одноцветная С WILD*/
enum class CombinationWinIntersectingMonochromeWithWild(
    override val matrix: Matrix3x3,
): CombinationMatrixEnum {
    _1(
        Matrix3x3(
            winItemList = listOf(A),

            a0 = A, b0 = A, c0 = C,
            a1 = A, b1 = A, c1 = WILD,
            a2 = G, b2 = H, c2 = F,
        )
    ),
    _2(
        Matrix3x3(
            winItemList = listOf(A),

            a0 = D, b0 = B, c0 = C,
            a1 = A, b1 = A, c1 = WILD,
            a2 = A, b2 = A, c2 = G,
        )
    ),
    _3(
        Matrix3x3(
            winItemList = listOf(A),

            a0 = A, b0 = A, c0 = A,
            a1 = A, b1 = WILD, c1 = F,
            a2 = A, b2 = A, c2 = A,
        )
    ),
    _4(
        Matrix3x3(
            winItemList = listOf(A),

            a0 = A, b0 = A, c0 = C,
            a1 = D, b1 = E, c1 = WILD,
            a2 = A, b2 = A, c2 = F,
        )
    ),
    _5(
        Matrix3x3(
            winItemList = listOf(A),

            a0 = A, b0 = A, c0 = C,
            a1 = A, b1 = A, c1 = WILD,
            a2 = A, b2 = A, c2 = B,
        )
    ),
    _6(
        Matrix3x3(
            winItemList = listOf(A),

            a0 = A, b0 = B, c0 = A,
            a1 = E, b1 = WILD, c1 = C,
            a2 = A, b2 = D, c2 = A,
        )
    ),
    _7(
        Matrix3x3(
            winItemList = listOf(A),

            a0 = WILD, b0 = A, c0 = A,
            a1 = D, b1 = A, c1 = F,
            a2 = G, b2 = H, c2 = A,
        )
    ),
    _8(
        Matrix3x3(
            winItemList = listOf(A),

            a0 = A, b0 = A, c0 = WILD,
            a1 = D, b1 = A, c1 = F,
            a2 = A, b2 = H, c2 = E,
        )
    ),
    _9(
        Matrix3x3(
            winItemList = listOf(A),

            a0 = E, b0 = B, c0 = A,
            a1 = D, b1 = A, c1 = F,
            a2 = WILD, b2 = A, c2 = A,
        )
    ),
    _10(
        Matrix3x3(
            winItemList = listOf(A),

            a0 = A, b0 = A, c0 = A,
            a1 = D, b1 = WILD, c1 = F,
            a2 = A, b2 = A, c2 = A,
        )
    ),
    _11(
        Matrix3x3(
            winItemList = listOf(A),

            a0 = A, b0 = B, c0 = C,
            a1 = D, b1 = A, c1 = F,
            a2 = A, b2 = A, c2 = WILD,
        )
    ),
    _12(
        Matrix3x3(
            winItemList = listOf(A),

            a0 = A, b0 = A, c0 = A,
            a1 = D, b1 = WILD, c1 = F,
            a2 = A, b2 = A, c2 = A,
        )
    ),
    _13(
        Matrix3x3(
            winItemList = listOf(A),

            a0 = WILD, b0 = B, c0 = C,
            a1 = A, b1 = A, c1 = A,
            a2 = G, b2 = H, c2 = A,
        )
    ),
    _14(
        Matrix3x3(
            winItemList = listOf(A),

            a0 = E, b0 = B, c0 = A,
            a1 = A, b1 = A, c1 = WILD,
            a2 = A, b2 = H, c2 = D,
        )
    ),
    _15(
        Matrix3x3(
            winItemList = listOf(A),

            a0 = A, b0 = A, c0 = A,
            a1 = WILD, b1 = A, c1 = A,
            a2 = G, b2 = H, c2 = A,
        )
    ),
    _16(
        Matrix3x3(
            winItemList = listOf(A),

            a0 = A, b0 = A, c0 = A,
            a1 = A, b1 = A, c1 = A,
            a2 = WILD, b2 = H, c2 = F,
        )
    ),
    _17(
        Matrix3x3(
            winItemList = listOf(A),

            a0 = C, b0 = B, c0 = A,
            a1 = A, b1 = A, c1 = WILD,
            a2 = A, b2 = A, c2 = A,
        )
    ),
    _18(
        Matrix3x3(
            winItemList = listOf(A),

            a0 = A, b0 = A, c0 = A,
            a1 = A, b1 = A, c1 = A,
            a2 = A, b2 = WILD, c2 = A,
        )
    ),
    _19(
        Matrix3x3(
            winItemList = listOf(A),

            a0 = A, b0 = B, c0 = C,
            a1 = A, b1 = A, c1 = A,
            a2 = A, b2 = A, c2 = WILD,
        )
    ),
    _20(
        Matrix3x3(
            winItemList = listOf(A),

            a0 = A, b0 = A, c0 = A,
            a1 = D, b1 = WILD, c1 = A,
            a2 = A, b2 = H, c2 = F,
        )
    ),
    _21(
        Matrix3x3(
            winItemList = listOf(A),

            a0 = G, b0 = B, c0 = WILD,
            a1 = D, b1 = A, c1 = A,
            a2 = A, b2 = A, c2 = F,
        )
    ),
    _22(
        Matrix3x3(
            winItemList = listOf(A),

            a0 = A, b0 = B, c0 = C,
            a1 = D, b1 = A, c1 = A,
            a2 = WILD, b2 = A, c2 = A,
        )
    ),
    _23(
        Matrix3x3(
            winItemList = listOf(A),

            a0 = A, b0 = WILD, c0 = C,
            a1 = D, b1 = A, c1 = A,
            a2 = G, b2 = H, c2 = A,
        )
    ),
    _24(
        Matrix3x3(
            winItemList = listOf(A),

            a0 = A, b0 = B, c0 = A,
            a1 = WILD, b1 = A, c1 = F,
            a2 = A, b2 = H, c2 = A,
        )
    ),
}

/**Комбинация Победная Пересекающаяся Разноцветная С WILD*/
enum class CombinationWinIntersectingColorfulWithWild(
    override val matrix: Matrix3x3,
): CombinationMatrixEnum {
    _1(
        Matrix3x3(
            winItemList = listOf(A, B),

            a0 = A, b0 = A, c0 = C,
            a1 = B, b1 = B, c1 = WILD,
            a2 = G, b2 = H, c2 = F,
        )
    ),
    _2(
        Matrix3x3(
            winItemList = listOf(A, B),

            a0 = D, b0 = E, c0 = C,
            a1 = A, b1 = A, c1 = WILD,
            a2 = B, b2 = B, c2 = G,
        )
    ),
    _3(
        Matrix3x3(
            winItemList = listOf(A, B, C),

            a0 = A, b0 = A, c0 = A,
            a1 = B, b1 = WILD, c1 = B,
            a2 = C, b2 = C, c2 = WILD,
        )
    ),
    _4(
        Matrix3x3(
            winItemList = listOf(A, B),

            a0 = A, b0 = A, c0 = C,
            a1 = D, b1 = E, c1 = WILD,
            a2 = B, b2 = B, c2 = F,
        )
    ),
    _5(
        Matrix3x3(
            winItemList = listOf(A, B, C),

            a0 = A, b0 = A, c0 = E,
            a1 = B, b1 = B, c1 = WILD,
            a2 = C, b2 = C, c2 = G,
        )
    ),
    _6(
        Matrix3x3(
            winItemList = listOf(A, B),

            a0 = A, b0 = F, c0 = B,
            a1 = E, b1 = WILD, c1 = C,
            a2 = B, b2 = D, c2 = A,
        )
    ),
    _7(
        Matrix3x3(
            winItemList = listOf(A, B),

            a0 = WILD, b0 = A, c0 = A,
            a1 = D, b1 = B, c1 = F,
            a2 = G, b2 = H, c2 = B,
        )
    ),
    _8(
        Matrix3x3(
            winItemList = listOf(A, B),

            a0 = A, b0 = A, c0 = WILD,
            a1 = D, b1 = B, c1 = F,
            a2 = B, b2 = H, c2 = E,
        )
    ),
    _9(
        Matrix3x3(
            winItemList = listOf(A, B),

            a0 = E, b0 = G, c0 = A,
            a1 = D, b1 = A, c1 = F,
            a2 = WILD, b2 = B, c2 = B,
        )
    ),
    _10(
        Matrix3x3(
            winItemList = listOf(A, B),

            a0 = WILD, b0 = A, c0 = A,
            a1 = D, b1 = B, c1 = F,
            a2 = B, b2 = B, c2 = B,
        )
    ),
    _11(
        Matrix3x3(
            winItemList = listOf(A, B),

            a0 = A, b0 = D, c0 = C,
            a1 = D, b1 = A, c1 = F,
            a2 = B, b2 = B, c2 = WILD,
        )
    ),
    _12(
        Matrix3x3(
            winItemList = listOf(A, B),

            a0 = WILD, b0 = G, c0 = B,
            a1 = A, b1 = WILD, c1 = A,
            a2 = B, b2 = B, c2 = B,
        )
    ),
    _13(
        Matrix3x3(
            winItemList = listOf(A, B),

            a0 = B, b0 = G, c0 = C,
            a1 = A, b1 = WILD, c1 = A,
            a2 = G, b2 = H, c2 = B,
        )
    ),
    _14(
        Matrix3x3(
            winItemList = listOf(A, B),

            a0 = E, b0 = G, c0 = A,
            a1 = B, b1 = WILD, c1 = B,
            a2 = A, b2 = H, c2 = D,
        )
    ),
    _15(
        Matrix3x3(
            winItemList = listOf(A, B, C),

            a0 = WILD, b0 = A, c0 = A,
            a1 = B, b1 = WILD, c1 = B,
            a2 = G, b2 = H, c2 = C,
        )
    ),
    _16(
        Matrix3x3(
            winItemList = listOf(A, B, C),

            a0 = C, b0 = C, c0 = WILD,
            a1 = A, b1 = WILD, c1 = A,
            a2 = B, b2 = H, c2 = F,
        )
    ),
    _17(
        Matrix3x3(
            winItemList = listOf(A, B),

            a0 = C, b0 = D, c0 = B,
            a1 = A, b1 = WILD, c1 = WILD,
            a2 = B, b2 = B, c2 = B
        )
    ),
    _18(
        Matrix3x3(
            winItemList = listOf(A, B, C),

            a0 = A, b0 = WILD, c0 = A,
            a1 = B, b1 = WILD, c1 = B,
            a2 = C, b2 = WILD, c2 = C,
        )
    ),
    _19(
        Matrix3x3(
            winItemList = listOf(A, B),

            a0 = A, b0 = D, c0 = C,
            a1 = A, b1 = WILD, c1 = A,
            a2 = B, b2 = B, c2 = WILD,
        )
    ),
    _20(
        Matrix3x3(
            winItemList = listOf(A, B),

            a0 = A, b0 = A, c0 = A,
            a1 = D, b1 = E, c1 = WILD,
            a2 = B, b2 = B, c2 = F,
        )
    ),
    _21(
        Matrix3x3(
            winItemList = listOf(A, B),

            a0 = G, b0 = E, c0 = WILD,
            a1 = D, b1 = A, c1 = B,
            a2 = WILD, b2 = B, c2 = F,
        )
    ),
    _22(
        Matrix3x3(
            winItemList = listOf(A, B),

            a0 = A, b0 = D, c0 = C,
            a1 = D, b1 = A, c1 = B,
            a2 = WILD, b2 = B, c2 = A,
        )
    ),
    _23(
        Matrix3x3(
            winItemList = listOf(A, B),

            a0 = WILD, b0 = B, c0 = C,
            a1 = D, b1 = A, c1 = B,
            a2 = G, b2 = H, c2 = A,
        )
    ),
    _24(
        Matrix3x3(
            winItemList = listOf(A, B),

            a0 = B, b0 = D, c0 = A,
            a1 = B, b1 = WILD, c1 = F,
            a2 = A, b2 = H, c2 = B,
        )
    ),
}







