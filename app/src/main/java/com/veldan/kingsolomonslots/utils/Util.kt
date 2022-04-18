package com.veldan.kingsolomonslots.utils

import android.util.Log
import com.badlogic.gdx.InputMultiplexer
import com.badlogic.gdx.InputProcessor
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Touchable
import com.badlogic.gdx.scenes.scene2d.ui.Widget
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.viewport.Viewport
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel

val Any.region: TextureRegion get() = if (this is Texture) TextureRegion(this) else throw TypeCastException("Type is not Texture.")

val CharSequence.int: Int get() = toString().toInt()

val Number.length: Int get() = toString().length

val Float.toDelay: Long get() = (this * 1000).toLong()

val Viewport.zeroScreenVector: Vector2 get() = project(Vector2(0f, 0f))



fun log(message: String) {
    Log.i("VLAD", message)
}

fun disposeAll(vararg disposable: Disposable) {
    disposable.forEach { it.dispose() }
}

fun cancelCoroutinesAll(vararg coroutine: CoroutineScope) {
    coroutine.forEach { it.cancel() }
}

fun Batch.beginend(block: Batch.() -> Unit = { }) {
    begin()
    block()
    end()
}

fun Batch.endbegin(block: Batch.() -> Unit = { }) {
    end()
    block()
    begin()
}

fun InputMultiplexer.addProcessors(vararg processor: InputProcessor) {
    processor.onEach { addProcessor(it) }
}

fun Actor.disable() {
    touchable = Touchable.disabled
}

fun Actor.enable() {
    touchable = Touchable.enabled
}

fun Long.transformToBalanceFormat(): String {
    val balance = toString().toMutableList()

    when {
        length == 4  -> balance.add(1, ' ')
        length == 5  -> balance.add(2, ' ')
        length == 6  -> balance.add(3, ' ')
        length == 7  -> {
            balance.add(1, ' ')
            balance.add(5, ' ')
        }
        length == 8  -> {
            balance.add(2, ' ')
            balance.add(6, ' ')
        }
        length == 9  -> {
            balance.add(3, ' ')
            balance.add(7, ' ')
        }
        length == 10 -> {
            balance.add(1, ' ')
            balance.add(5, ' ')
            balance.add(9, ' ')
        }
        length == 11 -> {
            balance.add(2, ' ')
            balance.add(6, ' ')
            balance.add(10, ' ')
        }
        length == 12 -> {
            balance.add(3, ' ')
            balance.add(7, ' ')
            balance.add(11, ' ')
        }
        else         -> toString()
    }

    return balance.joinToString("")
}

fun probability(percent: Int, block: () -> Unit = {}): Boolean {
    val randomNum = (1..100).random()
    return if (randomNum < percent) {
        block()
        true
    } else false
}

fun List<Actor>.setFillParent() {
    onEach { actor ->
        when (actor) {
            is Widget      -> actor.setFillParent(true)
            is WidgetGroup -> actor.setFillParent(true)
        }
    }
}