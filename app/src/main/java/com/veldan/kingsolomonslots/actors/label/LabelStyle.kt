package com.veldan.kingsolomonslots.actors.label

import com.badlogic.gdx.graphics.Color.WHITE
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.veldan.kingsolomonslots.manager.assets.FontTTFManager
import com.veldan.kingsolomonslots.manager.assets.util.FontTTFUtil
import com.veldan.kingsolomonslots.utils.Color.GOLD
import com.veldan.kingsolomonslots.utils.Color.WHITE_GOLD

object LabelStyle {

    val reggaeOne_64 get() = Label.LabelStyle(FontTTFManager.ReggaeOneFont.font_64.font, GOLD)
    val reggaeOne_50 get() = Label.LabelStyle(FontTTFManager.ReggaeOneFont.font_50.font, GOLD)
    val reggaeOne_40 get() = Label.LabelStyle(FontTTFManager.ReggaeOneFont.font_40.font, WHITE_GOLD)

    val gold_64  get() = Label.LabelStyle(FontTTFUtil.FONT.font_64.font, GOLD)
    val white_60 get() = Label.LabelStyle(FontTTFUtil.FONT.font_60.font, WHITE)

}