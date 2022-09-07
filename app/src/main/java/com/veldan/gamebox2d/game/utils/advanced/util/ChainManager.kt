package com.veldan.gamebox2d.game.utils.advanced.util

import com.badlogic.gdx.scenes.scene2d.Actor
import com.veldan.gamebox2d.game.utils.advanced.AdvancedGroup

class ChainManager(val group: AdvancedGroup) {
    private val chainList = mutableListOf<Actor>()


    fun addActor(actor: Actor, style: ChainStyle, limit: Int, spaceX: Float, spaceY: Float) {
        if (limit == 0) throw Exception("Limit must be > 0.")

        when (style) {
            ChainStyle.START_TOP_END_BOTTOM -> placeStartTopEndBottom(actor, limit, spaceX, spaceY)
        }
    }



    private fun placeStartTopEndBottom(actor: Actor, limit: Int, spaceX: Float, spaceY: Float) {
        val x: Float
        val y: Float

        if (chainList.isEmpty()) {
            x = 0f
            y = group.height - actor.height
        } else {
            val firstActor = chainList.first()
            val lastActor  = chainList.last()

            if (chainList.size % limit == 0) {
                x = firstActor.x
                y = lastActor.y - spaceY - actor.height
            } else {
                x = lastActor.x + lastActor.width + spaceX
                y = lastActor.y
            }
        }
        group.addActor(actor)
        actor.setPosition(x, y)
        chainList.add(actor)
    }



    enum class ChainStyle {
        START_TOP_END_BOTTOM
    }
}