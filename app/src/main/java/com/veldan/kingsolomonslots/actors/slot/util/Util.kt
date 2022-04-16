//package com.veldan.pinup2.actors.slot.util
//
//import com.badlogic.gdx.graphics.Texture
//import com.badlogic.gdx.graphics.g2d.TextureRegion
//import com.veldan.pinup2.actors.slot.util.combination.Matrix3x3
//
//data class SpinResult(
//    val winSlotItemSet: Set<SlotItem>?,
//    val bonus         : Bonus?,
//)
//
//data class FillResult(
//    val winSlotItemList : List<SlotItem>,
//    val intersectionList: List<Matrix3x3.Intersection>,
//)
//
//data class SlotItem(
//    val id         : Int,
//    var priceFactor: Float,
//    val region     : TextureRegion,
//)
//
//
//
//enum class Bonus {
//    MINI_GAME, SUPER_GAME
//}
//
//enum class FillStrategy {
//    RANDOM, WIN, MINI, SUPER,
//}