package com.veldan.pinup.actors.progressBar

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.veldan.pinup.actors.masks.normal.Mask
import com.veldan.pinup.advanced.group.AbstractAdvancedGroup
import com.veldan.pinup.manager.assets.SpriteManager
import com.veldan.pinup.utils.disable
import com.veldan.pinup.layout.Layout.ProgressBar as LP


class ProgressBar: AbstractAdvancedGroup() {
    override val controller = ProgressBarController(this)

    val panelImage      = Image(SpriteManager.OptionsSprite.PROGRESS_BAR_PANEL.data.texture     )
    val progressImage   = Image(SpriteManager.OptionsSprite.PROGRESS_BAR_PROGRESS.data.texture  )
    val controllerImage = Image(SpriteManager.OptionsSprite.PROGRESS_BAR_CONTROLLER.data.texture)
    val bordersImage    = Image(SpriteManager.OptionsSprite.PROGRESS_BAR_BORDERS.data.texture   )
    val progressMask    = Mask( SpriteManager.OptionsSprite.PROGRESS_BAR_PROGRESS.data.texture  )



    init {
        setSize(LP.W, LP.H)
        addListener(controller.getMoveListener())
        addActorsOnGroup()
        children.onEach { it.disable() }
    }



    private fun addActorsOnGroup() {
        addPanel()
        addBorders()
        addProgressMask()
        addController()
    }


    private fun addPanel() {
        addAndFillActor(panelImage)
    }

    private fun addBorders() {
        addActor(bordersImage)
        bordersImage.setBounds(LP.BORDERS_X, LP.BORDERS_Y, LP.BORDERS_W, LP.BORDERS_H)
    }

    private fun addProgressMask() {
        addActor(progressMask)
        progressMask.setBounds(LP.BORDERS_X, LP.BORDERS_Y, LP.BORDERS_W, LP.BORDERS_H)

        addProgress()
    }

    private fun addProgress() {
        progressMask.addAndFillActor(progressImage)
    }

    private fun addController() {
        addActor(controllerImage)
        controllerImage.setBounds(LP.CONTROLLER_MIN_X, LP.CONTROLLER_Y, LP.CONTROLLER_W, LP.CONTROLLER_H)
    }

}