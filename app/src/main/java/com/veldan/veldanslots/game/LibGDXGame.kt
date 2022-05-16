package com.veldan.veldanslots.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.utils.ScreenUtils
import com.veldan.veldanslots.game.advanced.AdvancedGame
import com.veldan.veldanslots.game.manager.NavigationManager
import com.veldan.veldanslots.game.manager.assets.util.MusicUtil
import com.veldan.veldanslots.game.screens.splash.SplashScreen
import com.veldan.veldanslots.AndroidLauncher
import com.veldan.veldanslots.utils.log

lateinit var game: LibGDXGame private set

class LibGDXGame(val activity: AndroidLauncher) : AdvancedGame() {

    private val color = Color.BLACK

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