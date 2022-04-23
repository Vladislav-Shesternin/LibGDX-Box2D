package com.veldan.veldanslots.actors.label

import com.badlogic.gdx.graphics.Color.GREEN
import com.badlogic.gdx.graphics.Color.WHITE
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.veldan.veldanslots.manager.assets.FontTTFManager
import com.veldan.veldanslots.manager.assets.util.FontTTFUtil
import com.veldan.veldanslots.utils.Color.GOLD
import com.veldan.veldanslots.utils.Color.WHITE_GOLD

object LabelStyle {

    val reggaeOne_white_64 get() = Label.LabelStyle(FontTTFManager.ReggaeOneFont.font_64.font , GREEN)
    val reggaeOne_gold_64  get() = Label.LabelStyle(FontTTFManager.ReggaeOneFont.font_64.font , GOLD)
    val reggaeOne_50       get() = Label.LabelStyle(FontTTFManager.ReggaeOneFont.font_50.font , GOLD)
    val reggaeOne_40       get() = Label.LabelStyle(FontTTFManager.ReggaeOneFont.font_40.font , WHITE_GOLD)
    val reggaeOne_120      get() = Label.LabelStyle(FontTTFManager.ReggaeOneFont.font_120.font, GOLD)
    val reggaeOne_300      get() = Label.LabelStyle(FontTTFManager.ReggaeOneFont.font_300.font, GOLD)

    val gold_40  get() = Label.LabelStyle(FontTTFUtil.FONT.font_40.font, GOLD)
    val gold_64  get() = Label.LabelStyle(FontTTFUtil.FONT.font_64.font, GOLD)
    val white_60 get() = Label.LabelStyle(FontTTFUtil.FONT.font_60.font, WHITE)
    val white_70 get() = Label.LabelStyle(FontTTFUtil.FONT.font_70.font, WHITE)

}