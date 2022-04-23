package com.veldan.veldanslots.actors.bonusGameGroup.superGame

import com.veldan.veldanslots.manager.assets.util.MusicUtil
import com.veldan.veldanslots.utils.controller.GroupController
import com.veldan.veldanslots.utils.enable

class SuperGameGroupController(override val group: SuperGameGroup) : GroupController {

    suspend fun start(): Int {
        with(MusicUtil) { currentMusic = SUPER_GAME }

        group.enable()
        return group.randomizerGroup.controller.start()
    }


}