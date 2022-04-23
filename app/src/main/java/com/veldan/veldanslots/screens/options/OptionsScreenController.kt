package com.veldan.veldanslots.screens.options

import com.veldan.veldanslots.utils.controller.ScreenController

class OptionsScreenController(
    override val screen: OptionsScreen
): ScreenController {

    companion object {
        var progressSoundVolume = -1
        var progressMusicVolume = -1
    }

}