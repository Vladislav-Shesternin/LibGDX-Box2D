package com.veldan.gamebox2d.game.actors.label

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.veldan.gamebox2d.game.manager.assets.FontTTFManager

object LabelStyle {

    val notoSans_Green_40 get() = Label.LabelStyle(FontTTFManager.NotoSansFont.font_40.font, Color.GREEN)

    val inter_Black_12 get() = Label.LabelStyle(FontTTFManager.InterFont.font_12.font, Color.BLACK)

//     val fontWhite_35  get() = Label.LabelStyle(FontTTFManager.fontText.font_35.font , Color.WHITE)

}