package com.veldan.veldanslots.actors.button

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.veldan.veldanslots.advanced.group.AbstractAdvancedGroup

class ButtonClickable(
    val style: ButtonClickableStyle? = null
) : AbstractAdvancedGroup() {
    override val controller = ButtonClickableController(this)
    
    val image        = Image().apply { isVisible = false }
    val defaultImage = if (style != null) Image(style.default) else Image()
    val pressedImage = (if (style != null) Image(style.pressed) else Image()).apply { isVisible = false }

   

    init {
        addAndFillActors(getActors())
    }



    private fun getActors() = listOf<Actor>(
        image,
        defaultImage,
        pressedImage,
    )

}