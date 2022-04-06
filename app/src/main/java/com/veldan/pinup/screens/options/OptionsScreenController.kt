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

    companion object {
        var progressSoundVolume = -1
        var progressMusicVolume = -1
        var checkedFlag: FlagTag? = null
    }

    val checkBoxGroup = CheckBoxGroup()

    val flagList = listOf<Flag>(
        Flag(FlagTag.US, screen.usImage, screen.usCheckBox, getFlagBlockByTag(FlagTag.US)),
        Flag(FlagTag.RU, screen.ruImage, screen.ruCheckBox, getFlagBlockByTag(FlagTag.RU)),
        Flag(FlagTag.UK, screen.ukImage, screen.ukCheckBox, getFlagBlockByTag(FlagTag.UK)),
    )



    private fun getFlagBlockByTag(tag: FlagTag): () -> Unit {
        return when (tag) {
            FlagTag.US -> { {
                log("us")
                Language.locale = Locale("us")
                checkedFlag = tag
            } }
            FlagTag.RU -> { {
                log("ru")
                Language.locale = Locale("ru")
                checkedFlag = tag
            } }
            FlagTag.UK -> { {
                log("uk")
                Language.locale = Locale("uk")
                checkedFlag = tag
            } }
        }
    }



    data class Flag(
        val tag     : FlagTag,
        val image   : Image,
        val checkBox: CheckBox,
        val block   : () -> Unit,
    )

    enum class FlagTag {
        US, RU, UK
    }

}