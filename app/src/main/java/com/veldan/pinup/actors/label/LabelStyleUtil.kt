package com.veldan.pinup.actors.label

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.veldan.pinup.manager.assets.FontTTFManager
import com.veldan.pinup.manager.assets.util.FontTTFUtil

object LabelStyleUtil {

    val amaranteWhite96  get() = Label.LabelStyle(FontTTFManager.AmaranteFont.white_96.font, Color.WHITE)
    val amaranteWhite60  get() = Label.LabelStyle(FontTTFManager.AmaranteFont.white_60.font, Color.WHITE)
    val amaranteWhite550 get() = Label.LabelStyle(FontTTFManager.AmaranteFont.white_550.font, Color.WHITE)

    val white96         get() = Label.LabelStyle(FontTTFUtil.FONT.white_96.font, Color.WHITE)
    val white92         get() = Label.LabelStyle(FontTTFUtil.FONT.white_92.font, Color.WHITE)
    val white60         get() = Label.LabelStyle(FontTTFUtil.FONT.white_60.font, Color.WHITE)
    val white50         get() = Label.LabelStyle(FontTTFUtil.FONT.white_50.font, Color.WHITE)
}