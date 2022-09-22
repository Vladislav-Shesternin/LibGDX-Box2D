package com.veldan.gamebox2d.game.screens

import com.badlogic.gdx.physics.box2d.World
import com.badlogic.gdx.utils.Align
import com.veldan.gamebox2d.game.actors.button.ButtonClickable
import com.veldan.gamebox2d.game.actors.button.ButtonClickableStyle
import com.veldan.gamebox2d.game.box2d.WorldUtil
import com.veldan.gamebox2d.game.box2d.bodies.BodyId
import com.veldan.gamebox2d.game.box2d.bodies.Collision
import com.veldan.gamebox2d.game.box2d.bodies.borders.Borders
import com.veldan.gamebox2d.game.box2d.bodies.car.Car
import com.veldan.gamebox2d.game.box2d.bodies.locator.Locator
import com.veldan.gamebox2d.game.manager.assets.SpriteManager
import com.veldan.gamebox2d.game.utils.advanced.AdvancedScreen
import com.veldan.gamebox2d.game.utils.advanced.AdvancedStage
import com.veldan.gamebox2d.game.utils.disposeAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import com.veldan.gamebox2d.game.utils.Layout.Game as LG

class GameScreen: AdvancedScreen() {

    // Actors
    private val upBtn    = ButtonClickable(ButtonClickableStyle.btn)
    private val downBtn  = ButtonClickable(ButtonClickableStyle.btn)
    private val leftBtn  = ButtonClickable(ButtonClickableStyle.btn)
    private val rightBtn = ButtonClickable(ButtonClickableStyle.btn)
    private val nextBtn  = ButtonClickable(ButtonClickableStyle.btn)

    // Body
    private val borders = Borders()
    private val carList = List<Car>(3) { Car() }
    private val locator = Locator()

    // values
    private val currentCarFlow  = MutableStateFlow(carList.first())
    private var currentCarIndex = 0



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
        createCarList()
        createLocator()
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
            setOnClickListener { currentCarFlow.value.stateFlow.tryEmit(Car.State.UP) }
        }
    }

    private fun AdvancedStage.addDownBtn() {
        addActor(downBtn)
        downBtn.apply {
            setBounds(LG.down.x, LG.down.y, LG.down.w, LG.down.h)
            setOrigin(Align.center)
            rotation = 180f
            setOnClickListener { currentCarFlow.value.stateFlow.tryEmit(Car.State.DOWN) }
        }
    }

    private fun AdvancedStage.addLeftBtn() {
        addActor(leftBtn)
        leftBtn.apply {
            setBounds(LG.left.x, LG.left.y, LG.left.w, LG.left.h)
            setOrigin(Align.center)
            rotation = 90f
            setOnClickListener { currentCarFlow.value.stateFlow.tryEmit(Car.State.LEFT) }
        }
    }

    private fun AdvancedStage.addRightBtn() {
        addActor(rightBtn)
        rightBtn.apply {
            setBounds(LG.right.x, LG.right.y, LG.right.w, LG.right.h)
            setOrigin(Align.center)
            rotation = -90f

            setOnClickListener { currentCarFlow.value.stateFlow.tryEmit(Car.State.RIGHT) }
        }
    }

    private fun AdvancedStage.addNextBtn() {
        addActor(nextBtn)
        nextBtn.apply {
            setBounds(LG.next.x, LG.next.y, LG.next.w, LG.next.h)
            setOrigin(Align.center)
            rotation = -90f

            setOnClickListener {
                when (carList.size) {
                    currentCarIndex.inc() -> currentCarIndex = 0
                    else                  -> currentCarIndex++
                }
                currentCarFlow.value = carList[currentCarIndex]
            }
        }

        updateCurrentCar()
    }

    // ------------------------------------------------------------------------
    // Create Bodies
    // ------------------------------------------------------------------------
    private fun createBorders() {
        borders.apply {
            initialize(stageUI, layoutFigmaToGame, LG.borders.x, LG.borders.y, LG.borders.w, LG.borders.h)
        }
    }

    private fun createCarList() {
        var newX = LG.car.x
        carList.onEachIndexed { index, car ->
            if (index == 2) car.id = BodyId.CAR_2

            car.initialize(stageUI, layoutFigmaToGame, newX, LG.car.y, LG.car.w, LG.car.h)
            newX += LG.car.w + LG.car.hs
        }
    }

    private fun createLocator() {
        locator.initialize(stageUI, layoutFigmaToGame, LG.locator.x, LG.locator.y, LG.locator.w, LG.locator.h)
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private fun updateCurrentCar() {
        coroutineMain.launch {
            currentCarFlow.collect { box ->
                box.actor.check()
            }
        }
    }

}