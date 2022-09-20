package com.veldan.gamebox2d.game.box2d.bodies.box

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.badlogic.gdx.utils.Align
import com.veldan.gamebox2d.game.actors.checkbox.CheckBox
import com.veldan.gamebox2d.game.actors.checkbox.CheckBoxGroup
import com.veldan.gamebox2d.game.actors.checkbox.CheckBoxStyle
import com.veldan.gamebox2d.game.manager.assets.SpriteManager
import com.veldan.gamebox2d.game.utils.Layout
import com.veldan.gamebox2d.game.utils.LayoutUtil
import com.veldan.gamebox2d.game.utils.advanced.AdvancedGroup

class ABox(
    val text         : String?           = null,
    val style        : Label.LabelStyle? = null,
    val checkBoxGroup: CheckBoxGroup?    = null,
): AdvancedGroup() {

    private val figmaW = 80f
    private val figmaH = 80f
    private val figmaCheckBoxLayout = Layout.LayoutData(30f, 30f, 20f, 20f)
    private val figmaTextLayout     = Layout.LayoutData(0f, 3f, 80f, 15f)

    private val mainLayoutUtil by lazy { LayoutUtil(width, height, figmaW, figmaH) }

    private val image    = Image(SpriteManager.GameRegion.BOX.region)
    private val checkBox = CheckBox(CheckBoxStyle.box)
    private val textBox: Label? = if(text != null && style != null) Label(text, style) else null



    override fun sizeChanged() {
        if (width > 0 && height > 0) addActorsOnGroup()
    }


    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------
    private fun addActorsOnGroup() {
        addImage()
        addCheckBox()
        addText()
    }

    private fun addImage() {
        addAndFillActor(image)
    }

    private fun addCheckBox() {
        addActor(checkBox)
        checkBox.apply {
            checkBoxGroup = this@ABox.checkBoxGroup
            figmaCheckBoxLayout.also { layout -> mainLayoutUtil.setBounds(this, layout.x, layout.y, layout.w, layout.h) }
        }
    }

    private fun addText() {
        textBox?.apply {
            this@ABox.addActor(this)
            setAlignment(Align.center)
            figmaTextLayout.also { layout -> mainLayoutUtil.setBounds(this, layout.x, layout.y, layout.w, layout.h) }
        }
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------
    fun check() {
        checkBox.check()
    }

    fun beginContact() {
        image.drawable = TextureRegionDrawable(SpriteManager.GameRegion.BOX_CONTACT.region)
    }

    fun endContact() {
        image.drawable = TextureRegionDrawable(SpriteManager.GameRegion.BOX.region)
    }

}