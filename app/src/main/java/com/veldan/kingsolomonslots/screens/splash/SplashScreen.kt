package com.veldan.kingsolomonslots.screens.splash

import android.annotation.SuppressLint
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.veldan.kingsolomonslots.actors.label.LabelStyle
import com.veldan.kingsolomonslots.advanced.AdvancedScreen
import com.veldan.kingsolomonslots.advanced.AdvancedStage
import com.veldan.kingsolomonslots.main.game
import com.veldan.kingsolomonslots.manager.assets.SpriteManager
import com.veldan.kingsolomonslots.layout.Layout.Splash as LS

@SuppressLint("CustomSplashScreen")
class SplashScreen : AdvancedScreen() {
    override val controller = SplashScreenController(this)

    val progressLabel by lazy { Label("0", LabelStyle.reggaeOne_64) }
    val panelImage    by lazy { Image(SpriteManager.SplashRegion.PANEL.region) }



    override fun show() {
        super.show()
        game.activity.controller.showLoader()
        controller.loadSplashAssets()
        setBackgrounds(SpriteManager.SplashRegion.BACKGROUND.region)
        stage.addActorsOnStage()

        controller.loadAssets()
    }

    override fun render(delta: Float) {
        super.render(delta)
        controller.loadingAssets()
    }



    private fun AdvancedStage.addActorsOnStage() {
        addPanel()
        addProgress()
    }


    private fun AdvancedStage.addPanel() {
       addActor(panelImage)
        panelImage.apply {
           setBounds(LS.PROGRESS_X, LS.PROGRESS_Y, LS.PROGRESS_W, LS.PROGRESS_H)
       }
    }

    private fun AdvancedStage.addProgress() {
        addActor(progressLabel)
        progressLabel.apply {
            setBounds(LS.PANEL_X, LS.PANEL_Y, LS.PANEL_W, LS.PANEL_H)
            setAlignment(Align.center)
        }
    }


}