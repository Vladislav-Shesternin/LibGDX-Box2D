package com.veldan.veldanslots.screens.splash

import android.annotation.SuppressLint
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.utils.Disposable
import com.veldan.veldanslots.main.game
import com.veldan.veldanslots.manager.NavigationManager
import com.veldan.veldanslots.manager.assets.FontTTFManager
import com.veldan.veldanslots.manager.assets.FontTTFManager.ReggaeOneFont
import com.veldan.veldanslots.manager.assets.MusicManager
import com.veldan.veldanslots.manager.assets.SoundManager
import com.veldan.veldanslots.manager.assets.SpriteManager
import com.veldan.veldanslots.manager.assets.SpriteManager.EnumAtlas
import com.veldan.veldanslots.manager.assets.SpriteManager.EnumTexture
import com.veldan.veldanslots.screens.game.GameScreen
import com.veldan.veldanslots.utils.Once
import com.veldan.veldanslots.utils.cancelCoroutinesAll
import com.veldan.veldanslots.utils.controller.ScreenController
import com.veldan.veldanslots.utils.log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

@SuppressLint("CustomSplashScreen")
class SplashScreenController(
    override val screen: SplashScreen
) : ScreenController, Disposable {

    private val coroutineProgress = CoroutineScope(Dispatchers.Default)
    private val onceLoadAssets = Once()
    private var progressValue = 0
    private val progressMutex = Mutex()



    override fun dispose() {
        cancelCoroutinesAll(coroutineProgress)
    }



    private fun initAssets() {
        with(game) {
            SpriteManager.init(assetManager)
            FontTTFManager.init(assetManager)
            MusicManager.init(assetManager)
            SoundManager.init(assetManager)
        }
    }

    private fun showProgress(progress: Float) {
        coroutineProgress.launch {
            progressMutex.withLock {
                while ((progressValue / 100f) < progress) {
                    progressValue++
                    log("$progressValue%")
                    Gdx.app.postRunnable { screen.progressLabel.setText("$progressValue%") }
                    if (progressValue == 100) cancel()
                }
            }
        }
    }



    fun loadingAssets() {
        if (game.assetManager.update()) {
            onceLoadAssets.once {
                showProgress(1f)
                initAssets()

                game.activity.controller.hideLoader()
                NavigationManager.navigate(GameScreen())
            }
        } else showProgress(game.assetManager.progress)
    }

    fun loadSplashAssets() {
        with(SpriteManager) {
            loadableTextureList = mutableListOf(EnumTexture.BACKGROUND)
            loadableAtlasList   = mutableListOf(EnumAtlas.SPLASH)
            load(game.assetManager)
        }
        with(FontTTFManager) {
            loadListFont = mutableListOf(ReggaeOneFont.font_64)
            load(game.assetManager)
        }

        game.assetManager.finishLoading()

        SpriteManager.init(game.assetManager)
        FontTTFManager.init(game.assetManager)
    }

    fun loadAssets() {
        with(SpriteManager) {
            loadableAtlasList = EnumAtlas.values().toMutableList()
            load(game.assetManager)
        }

        with(FontTTFManager) {
            loadListFont = (ReggaeOneFont.values + FontTTFManager.NotoSansFont.values).toMutableList()
            load(game.assetManager)
        }

        with(MusicManager) {
            loadListMusic = mutableListOf(*MusicManager.EnumMusic.values())
            load(game.assetManager)
        }

        with(SoundManager) {
            loadListSound = mutableListOf(*SoundManager.EnumSound.values())
            load(game.assetManager)
        }
    }

}