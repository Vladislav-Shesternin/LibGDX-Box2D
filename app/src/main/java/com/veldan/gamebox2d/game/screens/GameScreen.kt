package com.veldan.gamebox2d.game.screens

import com.badlogic.gdx.physics.box2d.World
import com.badlogic.gdx.utils.Align
import com.veldan.gamebox2d.game.actors.button.ButtonClickable
import com.veldan.gamebox2d.game.actors.button.ButtonClickableStyle
import com.veldan.gamebox2d.game.actors.checkbox.CheckBoxGroup
import com.veldan.gamebox2d.game.box2d.WorldUtil
import com.veldan.gamebox2d.game.box2d.bodies.Borders
import com.veldan.gamebox2d.game.box2d.bodies.box.Box
import com.veldan.gamebox2d.game.box2d.bodies.box.State
import com.veldan.gamebox2d.game.manager.assets.SpriteManager
import com.veldan.gamebox2d.game.utils.advanced.AdvancedScreen
import com.veldan.gamebox2d.game.utils.advanced.AdvancedStage
import com.veldan.gamebox2d.game.utils.disposeAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import com.veldan.gamebox2d.game.utils.Layout.Game as LG

class GameScreen: AdvancedScreen() {

    // values
    private val checkBoxGroup = CheckBoxGroup()
    private val textBoxList = listOf<String>("FORCE", "IMPULSE", "TORQUE")

    // Actors
    private val upBtn    = ButtonClickable(ButtonClickableStyle.btn)
    private val downBtn  = ButtonClickable(ButtonClickableStyle.btn)
    private val leftBtn  = ButtonClickable(ButtonClickableStyle.btn)
    private val rightBtn = ButtonClickable(ButtonClickableStyle.btn)
    private val nextBtn  = ButtonClickable(ButtonClickableStyle.btn)

    // Body
    private val borders = Borders()
    private val boxList = List<Box>(3) { Box(textBoxList[it], checkBoxGroup) }

    // values
    private val currentBoxFlow  = MutableStateFlow(boxList.first())
    private var currentBoxIndex = 0



    override fun show() {
        super.show()
        setUIBackground(SpriteManager.GameRegion.BACKGROUND.region)
    }

    override fun render(delta: Float) {
        super.render(delta)
        WorldUtil.update(delta)
        WorldUtil.debug(viewportBox2d.camera.combined)
    }

    override fun World.createBodies() {
        createBorders()
        createBoxList()
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addUpBtn()
        addDownBtn()
        addLeftBtn()
        addRightBtn()
        addNextBtn()
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
            setOnClickListener {

            }
        }
    }

    private fun AdvancedStage.addDownBtn() {
        addActor(downBtn)
        downBtn.apply {
            setBounds(LG.down.x, LG.down.y, LG.down.w, LG.down.h)
            setOrigin(Align.center)
            rotation = 180f
            setOnClickListener { currentBoxFlow.value.state = State.STOP }
        }
    }

    private fun AdvancedStage.addLeftBtn() {
        addActor(leftBtn)
        leftBtn.apply {
            setBounds(LG.left.x, LG.left.y, LG.left.w, LG.left.h)
            setOrigin(Align.center)
            rotation = 90f
            setOnClickListener { currentBoxFlow.value.state = State.LEFT }
        }
    }

    private fun AdvancedStage.addRightBtn() {
        addActor(rightBtn)
        rightBtn.apply {
            setBounds(LG.right.x, LG.right.y, LG.right.w, LG.right.h)
            setOrigin(Align.center)
            rotation = -90f

            setOnClickListener { currentBoxFlow.value.state = State.RIGHT }
        }
    }

    private fun AdvancedStage.addNextBtn() {
        addActor(nextBtn)
        nextBtn.apply {
            setBounds(LG.next.x, LG.next.y, LG.next.w, LG.next.h)
            setOrigin(Align.center)
            rotation = -90f

            setOnClickListener {
                when (boxList.size) {
                    currentBoxIndex.inc() -> currentBoxIndex = 0
                    else                  -> currentBoxIndex++
                }
                currentBoxFlow.value = boxList[currentBoxIndex]
            }
        }

        updateCurrentBox()
    }

    // ------------------------------------------------------------------------
    // Create Bodies
    // ------------------------------------------------------------------------
    private fun createBorders() {
        borders.apply {
            initialize(stageUI, layoutUtil, LG.borders.x, LG.borders.y, LG.borders.w, LG.borders.h)
        }
    }

    private fun createBoxList() {
        var newX = LG.box.x
        boxList.onEach { box ->
            box.apply {
                initialize(stageUI, layoutUtil, newX, LG.box.y, LG.box.w, LG.box.h)
                newX += LG.box.w + LG.box.hs
            }
        }
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private fun updateCurrentBox() {
        coroutineMain.launch {
            currentBoxFlow.collect { box ->
                box.actor.check()
            }
        }
    }

}