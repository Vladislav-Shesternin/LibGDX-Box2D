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

abstract class AdvancedScreen(
    val gameW : Float = 100f,
    val gameH : Float = 50f,
    val figmaW: Float = 1400f,
    val figmaH: Float = 700f,
) : ScreenAdapter(), AdvancedInputProcessor {

    private val viewportBack by lazy { FillViewport(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat()) }
    private val stageBack    by lazy { AdvancedStage(viewportBack) }

    val viewportUI by lazy { FitViewport(figmaW, figmaH) }
    val stageUI    by lazy { AdvancedStage(viewportUI) }

    val viewportBox2d by lazy { FitViewport(gameW, gameH) }

    val inputMultiplexer    = InputMultiplexer()
    val backBackgroundImage = Image()
    val uiBackgroundImage   = Image()

    val coroutineMain = CoroutineScope(Dispatchers.Main)
    val layoutUtil    = LayoutUtil(gameW, gameH, figmaW, figmaH)



    override fun show() {
        stageBack.addActor(backBackgroundImage)
        stageUI.apply {
            addActor(uiBackgroundImage)
            WorldUtil.world.createBodies()
            addActorsOnStageUI()
        }

        Gdx.input.inputProcessor = inputMultiplexer.apply { addProcessors(this@AdvancedScreen, stageUI) }
        Gdx.input.setCatchKey(Input.Keys.BACK, true)
    }

    override fun resize(width: Int, height: Int) {
        viewportBack.update(width, height, true)
        viewportUI.update(width, height, true)
        viewportBox2d.update(width, height, true)
    }

    override fun render(delta: Float) {
        stageBack.render()
        stageUI.render()
    }

    override fun hide() {
        dispose()
    }

    override fun dispose() {
        cancelCoroutinesAll(coroutineMain)
        disposeAll(stageBack, stageUI)
        inputMultiplexer.clear()
    }

    override fun keyDown(keycode: Int): Boolean {
        if (keycode == Input.Keys.BACK) NavigationManager.back()
        return super.keyDown(keycode)
    }

    open fun World.createBodies() {}
    open fun AdvancedStage.addActorsOnStageUI() {}



    fun setBackBackground(region: TextureRegion) {
        backBackgroundImage.apply {
            drawable = TextureRegionDrawable(region)
            setSize(viewportBack.worldWidth, viewportBack.worldHeight)
        }
    }

    fun setUIBackground(texture: TextureRegion) {
        uiBackgroundImage.apply {
            drawable = TextureRegionDrawable(texture)
            setSize(figmaW, figmaH)
        }
    }

    fun setBackgrounds(backRegion: TextureRegion, uiRegion: TextureRegion = backRegion) {
        setBackBackground(backRegion)
        setUIBackground(uiRegion)
    }

}