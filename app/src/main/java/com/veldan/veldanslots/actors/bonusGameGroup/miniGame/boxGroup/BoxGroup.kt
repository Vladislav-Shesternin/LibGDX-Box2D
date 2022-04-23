package com.veldan.veldanslots.actors.bonusGameGroup.miniGame.boxGroup

import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.veldan.veldanslots.actors.bonusGameGroup.miniGame.boxGroup.util.BoxPrize
import com.veldan.veldanslots.actors.bonusGameGroup.miniGame.boxGroup.util.BoxPrize.FAIL
import com.veldan.veldanslots.actors.bonusGameGroup.miniGame.boxGroup.util.BoxPrize.WIN
import com.veldan.veldanslots.advanced.group.AbstractAdvancedGroup
import com.veldan.veldanslots.advanced.group.AdvancedGroup
import com.veldan.veldanslots.manager.assets.SpriteManager
import com.veldan.veldanslots.manager.assets.util.SoundUtil
import com.veldan.veldanslots.manager.assets.util.playAdvanced
import com.veldan.veldanslots.utils.disable
import com.veldan.veldanslots.utils.listeners.toClickable
import com.veldan.veldanslots.utils.log
import com.veldan.veldanslots.layout.Layout.BoxGroup as LBG

class BoxGroup: AbstractAdvancedGroup() {
    override val controller = BoxGroupController(this)

    val boxList   = List(BoxGroupController.BOX_COUNT) { Image(SpriteManager.GameRegion.BOX.region) }
    val prizeList = List(BoxGroupController.BOX_COUNT) { Image().apply { addAction(Actions.alpha(0f)) } }


    init {
        setSize(LBG.W, LBG.H)
        addActorsOnGroup()
    }



    private fun AdvancedGroup.addActorsOnGroup() {
       addBoxList()
    }

    private fun AdvancedGroup.addBoxList() {
        val boxPrizeList = listOf<BoxPrize>(FAIL, FAIL, WIN).shuffled()

        log("BOX_PRIZE: $boxPrizeList")

        var newBoxX   = LBG.BOX_FIRST_X
        var newPrizeX = LBG.PRIZE_FIRST_X

        var box  : Image
        var prize: Image

        repeat(BoxGroupController.BOX_COUNT) { index ->
            box   = boxList[index]
            prize = prizeList[index]

            addActors(prize, box)

            box.setBounds(newBoxX, LBG.BOX_Y, LBG.BOX_W, LBG.BOX_H)
            newBoxX += LBG.BOX_W + LBG.BOX_SPACE_HORIZONTAL

            prize.drawable = TextureRegionDrawable(boxPrizeList[index].region)
            prize.setBounds(newPrizeX, LBG.PRIZE_Y, LBG.PRIZE_W, LBG.PRIZE_H)
            newPrizeX += LBG.PRIZE_W + LBG.PRIZE_SPACE_HORIZONTAL

            boxList[index].toClickable().setOnClickListener {
                when(boxPrizeList[index]) {
                    WIN  -> SoundUtil.WIN_BOX.playAdvanced()
                    FAIL -> SoundUtil.FAIL_BOX.playAdvanced()
                }

                this@BoxGroup.disable()
                it.addAction(Actions.sequence(
                    Actions.fadeOut(BoxGroupController.TIME_OPEN_BOX),
                    Actions.run { controller.doAfterGetResult(boxPrizeList[index]) }
                ))
                prizeList[index].addAction(Actions.fadeIn(BoxGroupController.TIME_OPEN_BOX))
            }
        }
    }

}