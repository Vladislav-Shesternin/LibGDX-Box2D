package com.veldan.veldanslots.game.screens.shop

import com.veldan.veldanslots.game.actors.button.ButtonClickable
import com.veldan.veldanslots.game.actors.button.ButtonClickableStyle
import com.veldan.veldanslots.game.actors.label.LabelStyle
import com.veldan.veldanslots.game.actors.label.spinning.SpinningLabel
import com.veldan.veldanslots.game.advanced.AdvancedScreen
import com.veldan.veldanslots.game.advanced.AdvancedStage
import com.veldan.veldanslots.game.manager.NavigationManager
import com.veldan.veldanslots.game.manager.assets.SpriteManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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
    val miniLabel  = SpinningLabel("" , LabelStyle.green_64)
    val superLabel = SpinningLabel("", LabelStyle.green_64)
    val megaLabel  = SpinningLabel("" , LabelStyle.green_64)

    // free
    val freeButton = ButtonClickable(ButtonClickableStyle.free)



    override fun show() {
        super.show()
        setBackgrounds(SpriteManager.SplashRegion.BACKGROUND.region)
        stage.addActorsOnStage()

        with(controller) {
            coroutineMain.launch {
                initProductDetails()
                updateProducts()
            }
        }
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
        miniButton.controller.setOnClickListener {
            with(this@ShopScreen.controller) { miniProductDetails?.let { launchBillingFlow(it) } }
        }
    }

    private fun AdvancedStage.addSuperButton() {
        addActor(superButton)
        superButton.setBounds(LS.SUPER_X, LS.SUPER_Y, LS.SUPER_W, LS.SUPER_H)
        superButton.controller.setOnClickListener {
            with(this@ShopScreen.controller) { superProductDetails?.let { launchBillingFlow(it) } }
        }
    }

    private fun AdvancedStage.addMegaButton() {
        addActor(megaButton)
        megaButton.setBounds(LS.MEGA_X, LS.MEGA_Y, LS.MEGA_W, LS.MEGA_H)
        megaButton.controller.setOnClickListener {
            with(this@ShopScreen.controller) { megaProductDetails?.let { launchBillingFlow(it) } }
        }
    }

    private fun AdvancedStage.addMiniLabel() {
        addActor(miniLabel)
        miniLabel.setBounds(LS.MINI_LABEL_X, LS.MINI_LABEL_Y, LS.MINI_LABEL_W, LS.MINI_LABEL_H)
        controller.coroutineMain.launch { controller.miniPriceFlow.collect { miniLabel.controller.setText(it) } }
    }

    private fun AdvancedStage.addSuperLabel() {
        addActor(superLabel)
        superLabel.setBounds(LS.SUPER_LABEL_X, LS.SUPER_LABEL_Y, LS.SUPER_LABEL_W, LS.SUPER_LABEL_H)
        controller.coroutineMain.launch { controller.superPriceFlow.collect { superLabel.controller.setText(it) } }
    }

    private fun AdvancedStage.addMegaLabel() {
        addActor(megaLabel)
        megaLabel.setBounds(LS.MEGA_LABEL_X, LS.MEGA_LABEL_Y, LS.MEGA_LABEL_W, LS.MEGA_LABEL_H)
        controller.coroutineMain.launch { controller.megaPriceFlow.collect { megaLabel.controller.setText(it) } }
    }

    private fun AdvancedStage.addFreeButton() {
        addActor(freeButton)
        freeButton.setBounds(LS.FREE_X, LS.FREE_Y, LS.FREE_W, LS.FREE_H)
        freeButton.controller.setOnClickListener {  }
    }

}