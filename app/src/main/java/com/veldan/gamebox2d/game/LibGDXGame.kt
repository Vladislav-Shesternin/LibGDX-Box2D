package com.veldan.gamebox2d.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.utils.ScreenUtils
import com.veldan.gamebox2d.game.utils.advanced.AdvancedGame
import com.veldan.gamebox2d.game.manager.NavigationManager
import com.veldan.gamebox2d.game.manager.assets.util.MusicUtil
import com.veldan.gamebox2d.game.screens.splash.SplashScreen
import com.veldan.gamebox2d.AndroidLauncher

lateinit var game: LibGDXGame private set

class LibGDXGame(val activity: AndroidLauncher) : AdvancedGame() {

    private val color = Color.PURPLE

    lateinit var assetManager: AssetManager private set



    override fun create() {
        game         = this
        assetManager = AssetManager()

        NavigationManager.navigate(SplashScreen())
    }

    override fun render() {
        ScreenUtils.clear(color)
        super.render()
    }

    override fun dispose() {
        super.dispose()
        MusicUtil.dispose()
        assetManager.dispose()
    }

}