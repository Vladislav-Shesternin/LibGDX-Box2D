package com.veldan.pinup.screens.options

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.utils.Disposable
import com.veldan.pinup.R
import com.veldan.pinup.actors.checkbox.CheckBox
import com.veldan.pinup.actors.checkbox.CheckBoxGroup
import com.veldan.pinup.main.game
import com.veldan.pinup.utils.cancelCoroutinesAll
import com.veldan.pinup.utils.controller.ScreenController
import com.veldan.pinup.utils.language.Language
import com.veldan.pinup.utils.log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import java.util.*

class OptionsScreenController(
    override val screen: OptionsScreen
): ScreenController {

    val checkBoxGroup = CheckBoxGroup()

    val flagList = listOf<Flag>(
        Flag(screen.usImage, screen.usCheckBox, getFlagBlockByTag(FlagTag.US)),
        Flag(screen.ruImage, screen.ruCheckBox, getFlagBlockByTag(FlagTag.RU)),
        Flag(screen.ukImage, screen.ukCheckBox, getFlagBlockByTag(FlagTag.UK)),
    )



    private fun getFlagBlockByTag(tag: FlagTag): () -> Unit = when (tag) {
        FlagTag.US -> {{
            log("us")
            Language.locale = Locale("us")
        }}
        FlagTag.RU -> {{
            log("ru")
            Language.locale = Locale("ru")
        }}
        FlagTag.UK -> {{
            log("uk")
            Language.locale = Locale("uk")
        }}
    }



    data class Flag(
        val image   : Image,
        val checkBox: CheckBox,
        val block   : () -> Unit,
    )

    enum class FlagTag {
        US, RU, UK
    }

}