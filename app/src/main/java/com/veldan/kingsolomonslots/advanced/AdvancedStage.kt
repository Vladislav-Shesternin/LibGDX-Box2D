package com.veldan.kingsolomonslots.advanced

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Widget
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.viewport.Viewport

open class AdvancedStage(viewport: Viewport) : Stage(viewport) {

    fun addActors(vararg actors: Actor) {
        actors.forEach { addActor(it) }
    }

    fun addActors(actors: List<Actor>) {
        actors.forEach { addActor(it) }
    }

    fun addAndFillActor(actor: Actor) {
        addActor(actor)
        when (actor) {
            is Widget      -> actor.setFillParent(true)
            is WidgetGroup -> actor.setFillParent(true)
        }
    }

    fun render() {
        viewport.apply(true)
        act()
        draw()
    }

    override fun dispose() {
        actors.onEach { actor -> if (actor is Disposable) actor.dispose() }
        super.dispose()

    }

}