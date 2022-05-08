package com.veldan.veldanslots.game.actors.bonusGameGroup.superGame

import com.veldan.veldanslots.game.manager.assets.util.MusicUtil
import com.veldan.veldanslots.game.utils.controller.GroupController
import com.veldan.veldanslots.game.utils.enable

class SuperGameGroupController(override val group: SuperGameGroup) : GroupController {

    suspend fun start(): Int {
        with(MusicUtil) { currentMusic = SUPER_GAME }

        group.enable()
        return group.randomizerGroup.controller.start()
    }


}