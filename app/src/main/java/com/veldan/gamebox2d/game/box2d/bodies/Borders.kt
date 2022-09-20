package com.veldan.gamebox2d.game.box2d.bodies

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.veldan.gamebox2d.game.manager.assets.SpriteManager
import com.veldan.gamebox2d.game.utils.LayoutUtil
import com.veldan.gamebox2d.game.utils.advanced.AdvancedGroup
import com.veldan.gamebox2d.game.utils.advanced.AdvancedStage
import kotlin.experimental.and
import kotlin.experimental.or

class Borders: AbstractBody() {

    override val name       = "Borders"
    override val bodyDef    = BodyDef().apply {
        type = BodyDef.BodyType.StaticBody
    }
    override val fixtureDef = FixtureDef().apply {
        filter.apply {
            categoryBits = Collision.Bits.BORDERS.bit
            maskBits = Collision.Bits.BOX_1.bit or Collision.Bits.BOX_2.bit
        }
    }
    override val actor      = AdvancedGroup()



    override fun AdvancedGroup.addActorsOnGroup() {
        addAndFillActor(Image(SpriteManager.GameRegion.BORDERS.region))
    }

}