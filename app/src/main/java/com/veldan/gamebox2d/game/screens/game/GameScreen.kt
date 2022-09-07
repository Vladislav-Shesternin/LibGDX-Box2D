package com.veldan.gamebox2d.game.screens.game

import com.badlogic.gdx.physics.box2d.World
import com.badlogic.gdx.utils.Align
import com.veldan.gamebox2d.game.actors.button.ButtonClickable
import com.veldan.gamebox2d.game.actors.button.ButtonClickableStyle
import com.veldan.gamebox2d.game.box2d.WorldUtil
import com.veldan.gamebox2d.game.box2d.bodies.Borders
import com.veldan.gamebox2d.game.box2d.bodies.Box
import com.veldan.gamebox2d.game.manager.assets.SpriteManager
import com.veldan.gamebox2d.game.utils.LayoutUtil
import com.veldan.gamebox2d.game.utils.advanced.AdvancedScreen
import com.veldan.gamebox2d.game.utils.advanced.AdvancedStage
import com.veldan.gamebox2d.game.utils.disposeAll
import com.veldan.gamebox2d.utils.log
import com.veldan.gamebox2d.game.utils.Layout.Game as LG

class GameScreen: AdvancedScreen() {
    private val mainLayoutUtil = LayoutUtil()

    private val upBtn    = ButtonClickable(ButtonClickableStyle.btn)
    private val downBtn  = ButtonClickable(ButtonClickableStyle.btn)
    private val leftBtn  = ButtonClickable(ButtonClickableStyle.btn)
    private val rightBtn = ButtonClickable(ButtonClickableStyle.btn)

    private val borders by lazy { Borders(LG.borders.w, LG.borders.h, mainLayoutUtil, stageGame) }
    private val boxList by lazy { List<Box>(3) { Box(LG.box.w, LG.box.h, mainLayoutUtil, stageGame) } }
    


    override fun show() {
        super.show()
        setGameBackground(SpriteManager.GameRegion.BACKGROUND.region)
    }

    override fun render(delta: Float) {
        super.render(delta)
        WorldUtil.update(delta)
        WorldUtil.debug(viewportGame.camera.combined)

    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addUpBtn()
        addDownBtn()
        addLeftBtn()
        addRightBtn()
    }

    override fun World.createBodies() {
        createBorders()
        createBoxList()
    }

    override fun dispose() {
        super.dispose()
        disposeAll(WorldUtil)
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addUpBtn() {
        addActor(upBtn)
        upBtn.apply {
            setBounds(LG.up.x, LG.up.y, LG.up.w, LG.up.h)
            setOrigin(Align.center)
            rotation = 0f
            setOnClickListener {}
        }
    }

    private fun AdvancedStage.addDownBtn() {
        addActor(downBtn)
        downBtn.apply {
            setBounds(LG.down.x, LG.down.y, LG.down.w, LG.down.h)
            setOrigin(Align.center)
            rotation = 180f
            setOnClickListener {}
        }
    }

    private fun AdvancedStage.addLeftBtn() {
        addActor(leftBtn)
        leftBtn.apply {
            setBounds(LG.left.x, LG.left.y, LG.left.w, LG.left.h)
            setOrigin(Align.center)
            rotation = 90f
            setOnClickListener {}
        }
    }

    private fun AdvancedStage.addRightBtn() {
        addActor(rightBtn)
        rightBtn.apply {
            setBounds(LG.right.x, LG.right.y, LG.right.w, LG.right.h)
            setOrigin(Align.center)
            rotation = -90f
            setOnClickListener {  }
        }
    }

    // ------------------------------------------------------------------------
    // Create Bodies
    // ------------------------------------------------------------------------
    private fun createBorders() {
        borders.apply {
            setPosition(LG.borders.x, LG.borders.y)
        }
    }

    private fun createBoxList() {
        var newX = LG.box.x
        boxList.onEach { box ->
            box.apply {
                setPosition(newX, LG.box.y)
                newX += LG.box.w + LG.box.hs
            }
        }
    }

}