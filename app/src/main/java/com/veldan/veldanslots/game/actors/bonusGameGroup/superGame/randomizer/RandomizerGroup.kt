package com.veldan.veldanslots.game.actors.bonusGameGroup.superGame.randomizer

import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.veldan.veldanslots.game.actors.label.LabelStyle
import com.veldan.veldanslots.game.advanced.group.AbstractAdvancedGroup
import com.veldan.veldanslots.game.advanced.group.AdvancedGroup
import com.veldan.veldanslots.game.manager.assets.SpriteManager
import com.veldan.veldanslots.game.layout.Layout.RandomizerGroup as LRG

class RandomizerGroup: AbstractAdvancedGroup() {
    override val controller = RandomizerGroupController(this)

    val panelImage = Image(SpriteManager.GameRegion.BIG_CIRCLE_PANEL.region)
    val textLabel  = Label("", LabelStyle.reggaeOne_300)



    init {
        setSize(LRG.PANEL_W, LRG.PANEL_H)
        addActorsOnGroup()
    }



    private fun AdvancedGroup.addActorsOnGroup() {
        addPanel()
    }

    private fun AdvancedGroup.addPanel() {
        addAndFillActors(panelImage, textLabel)
        panelImage.setOrigin(Align.center)
        textLabel.apply {
            setAlignment(Align.center)
            addAction(Actions.alpha(0f))
        }
    }

}