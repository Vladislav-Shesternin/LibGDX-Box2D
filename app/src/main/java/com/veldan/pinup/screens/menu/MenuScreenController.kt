package com.veldan.pinup.screens.menu

import com.veldan.pinup.manager.NavigationManager
import com.veldan.pinup.screens.menu.MenuScreen
import com.veldan.pinup.screens.options.OptionsScreen
import com.veldan.pinup.utils.controller.ScreenController

class MenuScreenController(
    override val screen: MenuScreen
) : ScreenController {

    fun playHandler() {
       // NavigationManager.navigate(GameScreen(), MenuScreen())
    }

    fun optionsHandler() {
        NavigationManager.navigate(OptionsScreen(), MenuScreen())
    }

    fun exitHandler() {
        NavigationManager.exit()
    }

}