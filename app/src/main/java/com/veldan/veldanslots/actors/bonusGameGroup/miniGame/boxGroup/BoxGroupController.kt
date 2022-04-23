package com.veldan.veldanslots.actors.bonusGameGroup.miniGame.boxGroup

import com.veldan.veldanslots.actors.bonusGameGroup.miniGame.boxGroup.util.BoxPrize
import com.veldan.veldanslots.utils.controller.GroupController

class BoxGroupController(override val group: BoxGroup) : GroupController {
    companion object {
        const val BOX_COUNT = 3

        const val TIME_OPEN_BOX = 1.5f
    }



    var doAfterGetResult: (BoxPrize) -> Unit = { }

}