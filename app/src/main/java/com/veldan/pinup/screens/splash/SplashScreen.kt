package com.veldan.pinup.screens.splash

import android.annotation.SuppressLint
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.veldan.pinup.actors.label.LabelStyleUtil
import com.veldan.pinup.advanced.AdvancedScreen
import com.veldan.pinup.advanced.AdvancedStage
import com.veldan.pinup.main.game
import com.veldan.pinup.layout.setBoundsFigmaY
import com.veldan.pinup.manager.assets.SpriteManager
import com.veldan.pinup.layout.Layout.Splash as LS

@SuppressLint("CustomSplashScreen")
class SplashScreen : AdvancedScreen() {
    override val controller = SplashScreenController(this)

    val progressLabel by lazy { Label("0", LabelStyleUtil.amaranteWhite96) }



    override fun show() {
        super.show()
        game.activity.publicController.showLoader()
        controller.loadSplashAssets()
        setBackgrounds(SpriteManager.SplashSprite.BACKGROUND.data.texture)
        stage.addActorsOnStage()

        controller.loadAssets()
    }

    override fun render(delta: Float) {
        super.render(delta)
        controller.loadingAssets()
    }



    private fun AdvancedStage.addActorsOnStage() {
        addProgress()
    }


    private fun AdvancedStage.addProgress() {
       addActor(progressLabel)
       progressLabel.apply {
           setBoundsFigmaY(LS.PROGRESS_X, LS.PROGRESS_Y, LS.PROGRESS_W, LS.PROGRESS_H)
           setAlignment(Align.center)
       }
    }


}