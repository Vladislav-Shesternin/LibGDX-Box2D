package com.veldan.veldanslots.game.actors.bonusGameGroup.superGame

import com.veldan.veldanslots.R
import com.veldan.veldanslots.game.actors.bonusGameGroup.superGame.randomizer.RandomizerGroup
import com.veldan.veldanslots.game.actors.label.LabelStyle
import com.veldan.veldanslots.game.actors.label.spinning.SpinningLabel
import com.veldan.veldanslots.game.advanced.group.AbstractAdvancedGroup
import com.veldan.veldanslots.game.advanced.group.AdvancedGroup
import com.veldan.veldanslots.game.utils.disable
import com.veldan.veldanslots.game.utils.language.Language
import com.veldan.veldanslots.game.layout.Layout.SuperGameGroup as LSG

class SuperGameGroup: AbstractAdvancedGroup() {
    override val controller = SuperGameGroupController(this)

    val titleLabel      = SpinningLabel("", LabelStyle.white_70)
    val randomizerGroup = RandomizerGroup()



    init {
        disable()
        addActorsOnGroup()
    }



    private fun AdvancedGroup.addActorsOnGroup() {
        addTitleLabel()
        addRandomizerGroup()
    }

    private fun AdvancedGroup.addTitleLabel() {
        addActor(titleLabel)
        with(titleLabel) {
            controller.setText(Language.getStringResource(R.string.super_game_title_1))
            setBounds(LSG.TITLE_X, LSG.TITLE_Y, LSG.TITLE_W, LSG.TITLE_H)
        }
    }

    private fun AdvancedGroup.addRandomizerGroup() {
        addActor(randomizerGroup)
        randomizerGroup.setBounds(LSG.RANDOMIZER_X, LSG.RANDOMIZER_Y, LSG.RANDOMIZER_W, LSG.RANDOMIZER_H)
    }

}