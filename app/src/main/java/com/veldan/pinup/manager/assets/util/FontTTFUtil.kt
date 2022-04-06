package com.veldan.pinup.manager.assets.util

import com.veldan.pinup.manager.assets.FontTTFManager
import com.veldan.pinup.utils.language.Language
import com.veldan.pinup.utils.log
import java.util.*

object FontTTFUtil {

    val FONT: FontTTFManager.IFont get() = when(Language.locale.language) {
        "us" -> FontTTFManager.AmaranteFont
        else -> FontTTFManager.NotoSansFont
    }

}