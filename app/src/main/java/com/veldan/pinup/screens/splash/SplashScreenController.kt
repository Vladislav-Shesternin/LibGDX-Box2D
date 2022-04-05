package com.veldan.pinup.screens.splash

import android.annotation.SuppressLint
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.utils.Disposable
import com.veldan.pinup.main.game
import com.veldan.pinup.manager.NavigationManager
import com.veldan.pinup.manager.assets.FontTTFManager
import com.veldan.pinup.manager.assets.FontTTFManager.AmaranteFont
import com.veldan.pinup.manager.assets.FontTTFManager.NotoSansFont
import com.veldan.pinup.manager.assets.SpriteManager
import com.veldan.pinup.screens.menu.MenuScreen
import com.veldan.pinup.utils.Once
import com.veldan.pinup.utils.cancelCoroutinesAll
import com.veldan.pinup.utils.controller.ScreenController
import com.veldan.pinup.utils.log
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
   private val onceLoadAssets    = Once()
   private var progressValue     = 0
   private val progressMutex     = Mutex()



    override fun dispose() {
        cancelCoroutinesAll(coroutineProgress)
    }



    private fun initAssets() {
        SpriteManager.init(game.assetManager)
        FontTTFManager.init(game.assetManager)
//        MusicManager.init(assetManager)
//        SoundManager.init(assetManager)
    }

    private fun showProgress(progress: Float) {
        coroutineProgress.launch { progressMutex.withLock {
            while ((progressValue / 100f) < progress) {
                progressValue++
                log("$progressValue%")
                Gdx.app.postRunnable { screen.progressLabel.setText("$progressValue%") }
                if (progressValue == 100) cancel()
            }
        } }
    }



    fun loadingAssets() {
        if (game.assetManager.update()) { onceLoadAssets.once {
            showProgress(1f)
            initAssets()

            game.activity.publicController.hideLoader()
            NavigationManager.navigate(MenuScreen())
        } } else showProgress(game.assetManager.progress)
    }

    fun loadSplashAssets() {
        with(SpriteManager) {
            loadListSprite = mutableListOf(*SpriteManager.SplashSprite.values())
            load(game.assetManager)
        }
        with(FontTTFManager) {
            loadListFont = mutableListOf(AmaranteFont.white_96)
            load(game.assetManager)
        }

        game.assetManager.finishLoading()

        SpriteManager.init(game.assetManager)
        FontTTFManager.init(game.assetManager)
    }

    fun loadAssets() {
        with(SpriteManager) {
            loadListSprite = mutableListOf(
                *SpriteManager.MenuSprite.values(),
                *SpriteManager.OptionsSprite.values(),
                // *SpriteManager.GameSprite.values(),
            )
            loadListSpriteList = mutableListOf(
             //   *SpriteManager.SlotItemSpriteList.values(),
            )
            load(game.assetManager)
        }

        with(FontTTFManager) {
            loadListFont = (AmaranteFont.values + NotoSansFont.values).toMutableList()
            load(game.assetManager)
        }
//
//        with(MusicManager) {
//            loadListMusic = mutableListOf(*MusicManager.EnumMusic.values())
//            load(assetManager)
//        }
//
//        with(SoundManager) {
//            loadListSound = mutableListOf(*SoundManager.EnumSound.values())
//            load(assetManager)
//        }
    }

}