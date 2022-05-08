package com.veldan.veldanslots.game.screens.shop

import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.veldan.veldanslots.game.actors.button.ButtonClickable
import com.veldan.veldanslots.game.actors.button.ButtonClickableStyle
import com.veldan.veldanslots.game.actors.label.LabelStyle
import com.veldan.veldanslots.game.advanced.AdvancedScreen
import com.veldan.veldanslots.game.advanced.AdvancedStage
import com.veldan.veldanslots.game.manager.NavigationManager
import com.veldan.veldanslots.game.manager.assets.SpriteManager
import com.veldan.veldanslots.game.layout.Layout.Shop as LS

class ShopScreen : AdvancedScreen() {
    override val controller = ShopScreenController(this)

    // back
    val backButton = ButtonClickable(ButtonClickableStyle.back)

    // shop items
    val miniButton  = ButtonClickable(ButtonClickableStyle.shop_mini)
    val superButton = ButtonClickable(ButtonClickableStyle.shop_super)
    val megaButton  = ButtonClickable(ButtonClickableStyle.shop_mega)

    // shop labels
    val miniLabel  = Label("${ShopScreenController.MINI_PRICE}$" , LabelStyle.reggaeOne_white_64)
    val superLabel = Label("${ShopScreenController.SUPER_PRICE}$", LabelStyle.reggaeOne_white_64)
    val megaLabel  = Label("${ShopScreenController.MEGA_PRICE}$" , LabelStyle.reggaeOne_white_64)

    // free
    val freeButton = ButtonClickable(ButtonClickableStyle.free)



    override fun show() {
        super.show()
        setBackgrounds(SpriteManager.SplashRegion.BACKGROUND.region)
        stage.addActorsOnStage()
    }



    private fun AdvancedStage.addActorsOnStage() {
        addBackButton()
        addMiniButton()
        addSuperButton()
        addMegaButton()
        addMiniLabel()
        addSuperLabel()
        addMegaLabel()
        addFreeButton()
    }


    private fun AdvancedStage.addBackButton() {
        addActor(backButton)
        backButton.setBounds(LS.BACK_X, LS.BACK_Y, LS.BACK_W, LS.BACK_H)
        backButton.controller.setOnClickListener { NavigationManager.back() }
    }

    private fun AdvancedStage.addMiniButton() {
        addActor(miniButton)
        miniButton.setBounds(LS.MINI_X, LS.MINI_Y, LS.MINI_W, LS.MINI_H)
        miniButton.controller.setOnClickListener {  }
    }

    private fun AdvancedStage.addSuperButton() {
        addActor(superButton)
        superButton.setBounds(LS.SUPER_X, LS.SUPER_Y, LS.SUPER_W, LS.SUPER_H)
        superButton.controller.setOnClickListener {  }
    }

    private fun AdvancedStage.addMegaButton() {
        addActor(megaButton)
        megaButton.setBounds(LS.MEGA_X, LS.MEGA_Y, LS.MEGA_W, LS.MEGA_H)
        megaButton.controller.setOnClickListener {  }
    }

    private fun AdvancedStage.addMiniLabel() {
        addActor(miniLabel)
        miniLabel.setAlignment(Align.center)
        miniLabel.setBounds(LS.MINI_LABEL_X, LS.MINI_LABEL_Y, LS.MINI_LABEL_W, LS.MINI_LABEL_H)
    }

    private fun AdvancedStage.addSuperLabel() {
        addActor(superLabel)
        superLabel.setAlignment(Align.center)
        superLabel.setBounds(LS.SUPER_LABEL_X, LS.SUPER_LABEL_Y, LS.SUPER_LABEL_W, LS.SUPER_LABEL_H)
    }

    private fun AdvancedStage.addMegaLabel() {
        addActor(megaLabel)
        megaLabel.setAlignment(Align.center)
        megaLabel.setBounds(LS.MEGA_LABEL_X, LS.MEGA_LABEL_Y, LS.MEGA_LABEL_W, LS.MEGA_LABEL_H)
    }

    private fun AdvancedStage.addFreeButton() {
        addActor(freeButton)
        freeButton.setBounds(LS.FREE_X, LS.FREE_Y, LS.FREE_W, LS.FREE_H)
        freeButton.controller.setOnClickListener {  }
    }

}