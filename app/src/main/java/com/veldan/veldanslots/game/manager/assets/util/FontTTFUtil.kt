package com.veldan.veldanslots.game.manager.assets.util

import com.veldan.veldanslots.game.manager.assets.FontTTFManager
import com.veldan.veldanslots.game.utils.language.Language

object FontTTFUtil {

    val FONT: FontTTFManager.IFont get() = when(Language.locale.language) {
        "us" -> FontTTFManager.ReggaeOneFont
        else -> FontTTFManager.NotoSansFont
    }

}