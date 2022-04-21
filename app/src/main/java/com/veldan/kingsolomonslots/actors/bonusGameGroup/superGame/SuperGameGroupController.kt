package com.veldan.kingsolomonslots.actors.bonusGameGroup.superGame

import com.veldan.kingsolomonslots.manager.assets.util.MusicUtil
import com.veldan.kingsolomonslots.utils.controller.GroupController
import com.veldan.kingsolomonslots.utils.enable

class SuperGameGroupController(override val group: SuperGameGroup) : GroupController {

    suspend fun start(): Int {
        with(MusicUtil) { currentMusic = SUPER_GAME }

        group.enable()
        return group.randomizerGroup.controller.start()
    }


}