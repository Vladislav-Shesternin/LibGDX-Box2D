package com.veldan.gamebox2d.game.box2d.bodies.locator

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.veldan.gamebox2d.game.actors.checkbox.CheckBox
import com.veldan.gamebox2d.game.actors.checkbox.CheckBoxStyle
import com.veldan.gamebox2d.game.manager.assets.SpriteManager
import com.veldan.gamebox2d.game.utils.Layout
import com.veldan.gamebox2d.game.utils.LayoutUtil
import com.veldan.gamebox2d.game.utils.advanced.AdvancedGroup

class ALocator: AdvancedGroup() {

    private val figmaSize         = Vector2(324f, 324f)
    private val figmaSensorLayout = Layout.LayoutData(106f, 106f, 112f, 112f)

    private val layoutFigmaToGroup by lazy { LayoutUtil(width, height, figmaSize.x, figmaSize.y) }

    private val locatorImage   = Image(SpriteManager.GameRegion.SENSOR.region)
    private val sensorCheckBox = CheckBox(CheckBoxStyle.locator)



    override fun sizeChanged() {
        if (width > 0 && height > 0) addActorsOnGroup()
    }



    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------
    private fun addActorsOnGroup() {
        addLocatorImage()
        addSensorCheckBox()
    }

    private fun addLocatorImage() {
        addAndFillActor(locatorImage)
    }

    private fun addSensorCheckBox() {
        addActor(sensorCheckBox)
        sensorCheckBox.apply {
            disable()
            layoutFigmaToGroup.setBounds(this, figmaSensorLayout.x, figmaSensorLayout.y, figmaSensorLayout.w, figmaSensorLayout.h)
        }
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------
    fun quietly() {
        sensorCheckBox.uncheck()
    }

    fun discovered() {
        sensorCheckBox.check()
    }

}