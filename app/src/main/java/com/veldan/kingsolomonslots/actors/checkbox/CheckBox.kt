package com.veldan.kingsolomonslots.actors.checkbox

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.veldan.kingsolomonslots.advanced.group.AbstractAdvancedGroup

class CheckBox(
    style: CheckBoxStyle? = null,
) : AbstractAdvancedGroup() {
    override val controller = CheckBoxController(this)

    val defaultImage = if (style != null)  Image(style.default) else Image()
    val checkImage   = (if (style != null) Image(style.checked) else Image()).apply { isVisible = false }



    init {
        addAndFillActors(getActors())
    }



    private fun getActors() = listOf<Actor>(
        defaultImage,
        checkImage,
    )

}