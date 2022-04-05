package com.veldan.pinup.main

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.utils.ScreenUtils
import com.veldan.pinup.advanced.AdvancedGame
import com.veldan.pinup.manager.NavigationManager
import com.veldan.pinup.screens.splash.SplashScreen

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
        assetManager.dispose()
    }

}