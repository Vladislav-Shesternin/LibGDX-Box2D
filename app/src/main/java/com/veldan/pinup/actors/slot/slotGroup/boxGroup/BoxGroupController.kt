package com.veldan.pinup.actors.slot.slotGroup.boxGroup

import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.veldan.pinup.actors.label.LabelStyleUtil
import com.veldan.pinup.advanced.group.util.ChainManager
import com.veldan.pinup.layout.Layout
import com.veldan.pinup.manager.assets.SpriteManager
import com.veldan.pinup.manager.assets.util.SoundUtil
import com.veldan.pinup.utils.controller.GroupController
import com.veldan.pinup.utils.listeners.toClickable
import com.veldan.pinup.utils.log

class BoxGroupController(override val group: BoxGroup) : GroupController {

    private val boxTexture = SpriteManager.GameSprite.BOX.data.texture
    private val xTexture   = SpriteManager.GameSprite.X.data.texture

    private val boxPrizeList = listOf<BoxPrize>(
        BoxPrize.WIN(5) , BoxPrize.WIN(7) , BoxPrize.WIN(10),
        BoxPrize.WIN(15), BoxPrize.WIN(20), BoxPrize.WIN(25) ,
        BoxPrize.Fail         , BoxPrize.Fail         , BoxPrize.Fail         ,
    ).shuffled()

    var boxClickBlock: (boxPrize: BoxPrize) -> Unit = {}



    fun layoutBoxGroup() {
        log(boxPrizeList.toString())

        boxPrizeList.onEach { boxPrize ->
            val boxPrizeActor = when (boxPrize) {
                is BoxPrize.WIN  -> Label(boxPrize.factor.toString(), LabelStyleUtil.amaranteWhite140).apply { setAlignment(Align.center) }
                is BoxPrize.Fail -> Image(xTexture)
            }

            boxPrizeActor.apply {
                addAction(Actions.alpha(0f))
                setSize(Layout.BoxGroup.PRIZE_W, Layout.BoxGroup.PRIZE_H)
                group.boxPrizeGroup.addActorChain(this, ChainManager.ChainStyle.START_TOP_END_BOTTOM, 3, Layout.BoxGroup.PRIZE_SPACE_HORIZONTAL, Layout.BoxGroup.PRIZE_SPACE_VERTICAL)
            }

            Image(boxTexture).apply {
                setSize(Layout.BoxGroup.BOX_W, Layout.BoxGroup.BOX_H)
                group.addActorChain(this, ChainManager.ChainStyle.START_TOP_END_BOTTOM, 3, Layout.BoxGroup.BOX_SPACE_HORIZONTAL, Layout.BoxGroup.BOX_SPACE_VERTICAL)
                toClickable().setOnClickListener(SoundUtil.WIN) {
                    addAction(Actions.fadeOut(1f))
                    boxPrizeActor.addAction(Actions.fadeIn(1f))
                    boxClickBlock(boxPrize)
                }
            }
        }
    }



    sealed class BoxPrize{
        data class WIN(val factor: Int): BoxPrize()
        object Fail: BoxPrize()
    }
    
}