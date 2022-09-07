package com.veldan.gamebox2d.game.utils.advanced

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.InputMultiplexer
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.physics.box2d.World
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.badlogic.gdx.utils.viewport.FillViewport
import com.badlogic.gdx.utils.viewport.FitViewport
import com.veldan.gamebox2d.game.box2d.WorldUtil
import com.veldan.gamebox2d.game.manager.NavigationManager
import com.veldan.gamebox2d.game.utils.*
import com.veldan.gamebox2d.utils.cancelCoroutinesAll
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

abstract class AdvancedScreen : ScreenAdapter(), AdvancedInputProcessor {

    private val viewportBack by lazy { FillViewport(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat()) }
    private val stageBack    by lazy { AdvancedStage(viewportBack) }

    val viewportGame by lazy { FitViewport(GAME_W, GAME_H) }
    val stageGame    by lazy { AdvancedStage(viewportGame) }

    val viewportUI by lazy { FitViewport(FIGMA_W, FIGMA_H) }
    val stageUI    by lazy { AdvancedStage(viewportUI) }

    val inputMultiplexer = InputMultiplexer()

    val backBackgroundImage  = Image()
    val gameBackgroundImage  = Image()

    val coroutineMain = CoroutineScope(Dispatchers.Main)



    override fun show() {
        stageBack.addActor(backBackgroundImage)
        stageGame.apply {
            addActor(gameBackgroundImage)
            addActorsOnStageGame()
        }
        stageUI.addActorsOnStageUI()

        WorldUtil.world.createBodies()

        Gdx.input.inputProcessor = inputMultiplexer.apply { addProcessors(this@AdvancedScreen, stageUI) }
        Gdx.input.setCatchKey(Input.Keys.BACK, true)
    }

    override fun resize(width: Int, height: Int) {
        viewportBack.update(width, height, true)
        viewportGame.update(width, height, true)
        viewportUI.update(width, height, true)
    }

    override fun render(delta: Float) {
        stageBack.render()
        stageGame.render()
        stageUI.render()
    }

    override fun hide() {
        dispose()
    }

    override fun dispose() {
        cancelCoroutinesAll(coroutineMain)
        disposeAll(stageBack, stageGame, stageUI)
        inputMultiplexer.clear()
    }

    override fun keyDown(keycode: Int): Boolean {
        if (keycode == Input.Keys.BACK) NavigationManager.back()
        return super.keyDown(keycode)
    }

    open fun AdvancedStage.addActorsOnStageGame() {}
    open fun AdvancedStage.addActorsOnStageUI() {}
    open fun World.createBodies() {}



    fun setBackBackground(region: TextureRegion) {
        backBackgroundImage.apply {
            drawable = TextureRegionDrawable(region)
            setSize(viewportBack.worldWidth, viewportBack.worldHeight)
        }
    }

    fun setGameBackground(texture: TextureRegion) {
        gameBackgroundImage.apply {
            drawable = TextureRegionDrawable(texture)
            setSize(GAME_W, GAME_H)
        }
    }

    fun setBackgrounds(backRegion: TextureRegion, frontRegion: TextureRegion = backRegion) {
        setBackBackground(backRegion)
        setGameBackground(frontRegion)
    }

}