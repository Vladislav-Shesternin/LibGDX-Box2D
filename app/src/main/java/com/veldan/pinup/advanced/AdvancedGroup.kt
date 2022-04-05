package com.veldan.pinup.advanced

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Widget
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup
import com.badlogic.gdx.utils.Disposable
import com.veldan.pinup.utils.setFillParent

open class AdvancedGroup: WidgetGroup(), Disposable {

    private val chainList = mutableListOf<Actor>()



    override fun dispose() {
        children.onEach { if (it is Disposable) it.dispose() }
    }



    fun addAlignActor(
        actor: Actor,
        alignmentHorizontal: AlignmentHorizontal = AlignmentHorizontal.START,
        alignmentVertical: AlignmentVertical = AlignmentVertical.BOTTOM,
    ) {
        addActor(actor)

        // START to BOTTOM (DEFAULT)
        var newX = 0f
        var newY = 0f

        when (alignmentHorizontal to alignmentVertical) {
            AlignmentHorizontal.START to AlignmentVertical.CENTER -> {
                newY = (height / 2) - (actor.height / 2)
            }
            AlignmentHorizontal.START to AlignmentVertical.TOP     -> {
                newY = height - actor.height
            }
            AlignmentHorizontal.CENTER to AlignmentVertical.BOTTOM   -> {
                newX = (width / 2) - (actor.width / 2)
            }
            AlignmentHorizontal.CENTER to AlignmentVertical.CENTER -> {
                newX = (width / 2) - (actor.width / 2)
                newY = (height / 2) - (actor.height / 2)
            }
            AlignmentHorizontal.CENTER to AlignmentVertical.TOP -> {
                newX = (width / 2) - (actor.width / 2)
                newY = height - actor.height
            }
            AlignmentHorizontal.END to AlignmentVertical.BOTTOM -> {
                newX = width - actor.width
            }
            AlignmentHorizontal.END to AlignmentVertical.CENTER -> {
                newX = width - actor.width
                newY = (height / 2) - (actor.height / 2)
            }
            AlignmentHorizontal.END to AlignmentVertical.TOP    -> {
                newX = width - actor.width
                newY = height - actor.height
            }
        }
        actor.setPosition(newX, newY)
    }

    fun addAndFillActor(actor: Actor) {
        addActor(actor)
        when (actor) {
            is Widget      -> actor.setFillParent(true)
            is WidgetGroup -> actor.setFillParent(true)
        }
    }

    fun addAndFillActors(actors: List<Actor>) {
        actors.forEach { addActor(it) }
        actors.setFillParent()
    }

    fun addAndFillActors(vararg actors: Actor) {
        actors.forEach { addActor(it) }
        actors.toList().setFillParent()
    }

    fun addActors(vararg actors: Actor) {
        actors.forEach { addActor(it) }
    }

    fun addActors(actors: List<Actor>) {
        actors.forEach { addActor(it) }
    }

    fun addActorChain(actor: Actor, style: ChainStyle, limit: Int, spaceX: Float, spaceY: Float) {
        if (limit == 0) throw Exception("Limit must be > 0.")

        when (style) {
            ChainStyle.START_TOP_END_BOTTOM -> styleStartTopEndBottom(actor, limit, spaceX, spaceY)
        }
    }



    private fun styleStartTopEndBottom(actor: Actor, limit: Int, spaceX: Float, spaceY: Float) {
        val x: Float
        val y: Float

        if (chainList.isEmpty()) {
            x = 0f
            y = this.height - actor.height
        } else {
            val firstActor = chainList.first()
            val lastActor = chainList.last()

            if (chainList.size % limit == 0) {
                x = firstActor.x
                y = lastActor.y - spaceY - actor.height
            } else {
                x = lastActor.x + lastActor.width + spaceX
                y = lastActor.y
            }
        }
        actor.setPosition(x, y)
        chainList.add(actor)
        addActor(actor)
    }



    enum class ChainStyle {
        START_TOP_END_BOTTOM
    }

    enum class AlignmentHorizontal { START, CENTER, END, }
    enum class AlignmentVertical { BOTTOM, CENTER, TOP, }

}