package com.veldan.pinup.actors.progressBar

import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.veldan.pinup.layout.Layout
import com.veldan.pinup.utils.controller.GroupController
import com.veldan.pinup.layout.Layout.ProgressBar as LP

class ProgressBarController(override val group: ProgressBar) : GroupController {

    private val progressOnePercent = (LP.CONTROLLER_MAX_X - LP.CONTROLLER_MIN_X) / 100f
    private var progress           = 0

    var progressBlock: (Int) -> Unit = { }





    fun getMoveListener() = object : InputListener() {
        override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
            touchDragged(event, x, y, pointer)
            return true
        }

        override fun touchDragged(event: InputEvent?, x: Float, y: Float, pointer: Int) {
            with(group) {
                when {
                    x < LP.CONTROLLER_MIN_X -> {
                        progressImage.x   = LP.CONTROLLER_MIN_X - LP.BORDERS_W
                        controllerImage.x = LP.CONTROLLER_MIN_X
                        progress = 0
                    }
                    x > LP.CONTROLLER_MAX_X -> {
                        progressImage.x   = LP.CONTROLLER_MAX_X - LP.BORDERS_W
                        controllerImage.x = LP.CONTROLLER_MAX_X
                        progress = 100
                    }
                    else                    -> {
                        progressImage.x   = x - LP.BORDERS_W
                        controllerImage.x = x
                        (x / progressOnePercent).toInt().also { progress = if (it < 0) 0 else it }
                    }
                }
            }
            progressBlock(progress)
        }
    }

    fun setProgress(progressValue: Int) {
        with(group) {
            when {
                progressValue <= 0   -> {
                    progressImage.x   = LP.CONTROLLER_MIN_X - LP.BORDERS_W
                    controllerImage.x = LP.CONTROLLER_MIN_X
                    progress          = 0
                }
                progressValue >= 100 -> {
                    progressImage.x   = LP.CONTROLLER_MAX_X - LP.BORDERS_W
                    controllerImage.x = LP.CONTROLLER_MAX_X
                    progress          = 100
                }
                else                 -> {
                    progressImage.x   = (progressOnePercent * progressValue) - LP.BORDERS_W
                    controllerImage.x = (progressOnePercent * progressValue)
                    progress          = progressValue
                }
            }
        }
        progressBlock(progress)
    }

}