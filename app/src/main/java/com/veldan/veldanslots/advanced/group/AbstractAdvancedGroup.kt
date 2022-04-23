package com.veldan.veldanslots.advanced.group

import com.badlogic.gdx.utils.Disposable
import com.veldan.veldanslots.utils.controller.GroupController

abstract class AbstractAdvancedGroup: AdvancedGroup() {

    abstract val controller: GroupController



    override fun dispose() {
        super.dispose()
        if (controller is Disposable) (controller as Disposable).dispose()
    }

}