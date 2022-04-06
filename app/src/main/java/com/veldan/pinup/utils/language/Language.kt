package com.veldan.pinup.utils.language

import android.content.res.Configuration
import com.veldan.pinup.main.game
import com.veldan.pinup.utils.log
import java.util.*

object Language {

    var locale: Locale = Locale.getDefault()



    fun getLocaleStringResource(
        resourceId: Int,
        requestedLocale: Locale = locale,
    ): String = with(game.activity) { Configuration(resources.configuration).run {
         setLocale(requestedLocale)
         createConfigurationContext(this).getString(resourceId)
    } }

}