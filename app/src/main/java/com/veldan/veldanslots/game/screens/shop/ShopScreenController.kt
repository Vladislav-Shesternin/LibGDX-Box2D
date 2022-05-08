package com.veldan.veldanslots.game.screens.shop

import com.veldan.veldanslots.game.utils.controller.ScreenController

class ShopScreenController(
    override val screen: ShopScreen
) : ScreenController {

    companion object {
        const val MINI_PRICE  = 0.25f
        const val SUPER_PRICE = 0.5f
        const val MEGA_PRICE  = 1f
    }

}