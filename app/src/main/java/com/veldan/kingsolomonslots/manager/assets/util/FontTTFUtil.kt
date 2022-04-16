package com.veldan.kingsolomonslots.manager.assets.util

import com.veldan.kingsolomonslots.manager.assets.FontTTFManager
import com.veldan.kingsolomonslots.utils.language.Language

object FontTTFUtil {

    val FONT: FontTTFManager.IFont get() = when(Language.locale.language) {
        "us" -> FontTTFManager.ReggaeOneFont
        else -> FontTTFManager.NotoSansFont
    }

}