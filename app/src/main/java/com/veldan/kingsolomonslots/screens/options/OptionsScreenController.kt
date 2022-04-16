package com.veldan.kingsolomonslots.screens.options

import com.veldan.kingsolomonslots.utils.controller.ScreenController

class OptionsScreenController(
    override val screen: OptionsScreen
): ScreenController {

    companion object {
        var progressSoundVolume = -1
        var progressMusicVolume = -1
    }

}