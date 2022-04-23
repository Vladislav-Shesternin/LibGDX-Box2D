package com.veldan.veldanslots.actors.progressBar

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.veldan.veldanslots.actors.masks.normal.Mask
import com.veldan.veldanslots.advanced.group.AbstractAdvancedGroup
import com.veldan.veldanslots.manager.assets.SpriteManager
import com.veldan.veldanslots.utils.disable
import com.veldan.veldanslots.layout.Layout.ProgressBar as LP


class ProgressBar: AbstractAdvancedGroup() {
    override val controller = ProgressBarController(this)

    val panelImage      = Image(SpriteManager.OptionsRegion.PB_PANEL.region        )
    val progressImage   = Image(SpriteManager.OptionsRegion.PB_PROGRESS.region     )
    val controllerImage = Image(SpriteManager.OptionsRegion.PB_CONTROLLER.region   )
    val progressMask    = Mask( SpriteManager.OptionsRegion.PB_PROGRESS_MASK.region)



    init {
        setSize(LP.W, LP.H)
        addListener(controller.getMoveListener())
        addActorsOnGroup()
        children.onEach { it.disable() }
    }



    private fun addActorsOnGroup() {
        addPanel()
        addProgressMask()
        addController()
    }


    private fun addPanel() {
        addAndFillActor(panelImage)
    }
    
    private fun addProgressMask() {
        addActor(progressMask)
        progressMask.setBounds(LP.PROGRESS_X, LP.PROGRESS_Y, LP.PROGRESS_W, LP.PROGRESS_H)

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