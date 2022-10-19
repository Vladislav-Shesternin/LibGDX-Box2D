package com.veldan.gamebox2d.game.screens

import android.annotation.SuppressLint
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.veldan.gamebox2d.AndroidLauncher
import com.veldan.gamebox2d.game.actors.label.LabelStyle
import com.veldan.gamebox2d.game.utils.advanced.AdvancedScreen
import com.veldan.gamebox2d.game.utils.advanced.AdvancedStage
import com.veldan.gamebox2d.game.game
import com.veldan.gamebox2d.game.manager.NavigationManager
import com.veldan.gamebox2d.game.manager.assets.FontTTFManager
import com.veldan.gamebox2d.game.manager.assets.SpriteManager
import com.veldan.gamebox2d.game.utils.Once
import com.veldan.gamebox2d.utils.log
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import com.veldan.gamebox2d.game.utils.Layout.Splash as LS

@SuppressLint("CustomSplashScreen")
class SplashScreen : AdvancedScreen() {

    private val progressLabel by lazy { Label("0", LabelStyle.notoSans_Green_40) }

    private val onceFinishLoadingAssets = Once()
    private val progressMutex           = Mutex()
    private var progressValue           = 0


    override fun show() {
        loadSplashAssets()
        super.show()

        loadAssets()
    }

    override fun render(delta: Float) {
        super.render(delta)
        loadingAssets()
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addProgress()
    }



    private fun loadSplashAssets() {
        with(FontTTFManager) {
            loadListFont = mutableListOf(FontTTFManager.NotoSansFont.font_40)
            load(game.assetManager)
        }

        game.assetManager.finishLoading()

        FontTTFManager.init(game.assetManager)
    }

    private fun loadAssets() {
        with(SpriteManager) {
            loadableAtlasList   = SpriteManager.EnumAtlas.values().toMutableList()
            load(game.assetManager)
        }
        with(FontTTFManager) {
            loadListFont = (FontTTFManager.NotoSansFont.values + FontTTFManager.InterFont.values).toMutableList()
            load(game.assetManager)
        }
    }

    private fun loadingAssets() {
        game.assetManager.update()
        showProgress(game.assetManager.progress)
    }

    private fun showProgress(progress: Float) {
        coroutineMain.launch {
            progressMutex.withLock {
                while ((progressValue / 100f) < progress) {
                    progressValue++
                    log("$progressValue%")
                    Gdx.app.postRunnable { progressLabel.setText("$progressValue%") }
                    if (progressValue == 100) {
                        onceFinishLoadingAssets.once {
                            cancel()
                            initAssets()
                            AndroidLauncher.hideLoader()

                            NavigationManager.navigate(GameScreen())
                        }
                    }
                    delay(10)
                }
            }
        }
    }

    private fun initAssets() {
        with(game) {
            SpriteManager.init(assetManager)
            FontTTFManager.init(assetManager)
        }
    }
    

    private fun AdvancedStage.addProgress() {
        addActor(progressLabel)

        progressLabel.apply {
            debug()
            setBounds(LS.progress.x, LS.progress.y, LS.progress.w, LS.progress.h)
            setAlignment(Align.center)
        }
    }


}