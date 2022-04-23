package com.veldan.veldanslots.manager.assets.util

import com.veldan.veldanslots.manager.assets.FontTTFManager
import com.veldan.veldanslots.utils.language.Language

object FontTTFUtil {

    val FONT: FontTTFManager.IFont get() = when(Language.locale.language) {
        "us" -> FontTTFManager.ReggaeOneFont
        else -> FontTTFManager.NotoSansFont
    }

}