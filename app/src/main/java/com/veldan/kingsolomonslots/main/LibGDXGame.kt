package com.veldan.kingsolomonslots.main

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.utils.ScreenUtils
import com.veldan.kingsolomonslots.advanced.AdvancedGame
import com.veldan.kingsolomonslots.manager.NavigationManager
import com.veldan.kingsolomonslots.manager.assets.util.MusicUtil
import com.veldan.kingsolomonslots.screens.splash.SplashScreen

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