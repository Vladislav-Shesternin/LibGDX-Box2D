package com.veldan.veldanslots.game.actors.slot.util

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.veldan.veldanslots.game.actors.slot.util.combination.Matrix3x5

data class SpinResult(
    val winSlotItemSet: Set<SlotItem>?,
    val bonus         : Bonus?,
)

data class FillResult(
    val winSlotItemList : List<SlotItem>,
    val intersectionList: List<Matrix3x5.Intersection>,
)

data class SlotItem(
    val id         : Int,
    var priceFactor: Float,
    val region     : TextureRegion,
)



enum class Bonus {
    MINI_GAME, SUPER_GAME
}

sealed class FillStrategy {
    object MIX  :FillStrategy()
    object WIN  :FillStrategy()
    object MINI :FillStrategy()
    object SUPER:FillStrategy()

    data class WIN_SUPER_GAME(val slotNumber: Int): FillStrategy()
    data class FAIL_SUPER_GAME(val slotNumber: Int): FillStrategy()
}