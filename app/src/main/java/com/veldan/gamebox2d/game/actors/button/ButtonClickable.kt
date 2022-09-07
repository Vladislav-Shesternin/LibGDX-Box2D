package com.veldan.gamebox2d.game.actors.button

import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.Touchable
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.veldan.gamebox2d.game.utils.advanced.AdvancedGroup
import com.veldan.gamebox2d.utils.log

class ButtonClickable(
    style: ButtonClickableStyle? = null
) : AdvancedGroup() {

    private val defaultImage  =  if (style != null) Image(style.default)  else Image()
    private val pressedImage  = (if (style != null) Image(style.pressed)  else Image()).apply { isVisible = false }
    private val disabledImage = (if (style != null) Image(style.disabled) else Image()).apply { isVisible = false }

    private var onClickBlock: () -> Unit = { }

    private var sound: Sound? = null



    init {
        addAndFillActors(getActors())
        addListener(getListener())
    }



    private fun getActors() = listOf<Actor>(
        defaultImage,
        pressedImage,
        disabledImage,
    )



    private fun getListener() = object : InputListener() {
        var isWithin = false

        override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
            touchDragged(event, x, y, pointer)
            return true
        }

        override fun touchDragged(event: InputEvent?, x: Float, y: Float, pointer: Int) {
            isWithin = x in 0f..width && y in 0f..height
            if (isWithin) press() else unpress()
        }

        override fun touchUp(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int) {
            if (isWithin) {
                unpress()
                onClickBlock()
            }
        }
    }

    fun press() {
        defaultImage.isVisible = false
        pressedImage.isVisible = true
    }

    fun unpress() {
        defaultImage.isVisible = true
        pressedImage.isVisible = false
    }

    fun disable(useDisabledStyle: Boolean = true) {
        touchable = Touchable.disabled

        if (useDisabledStyle) {
            defaultImage.isVisible  = false
            pressedImage.isVisible  = false
            disabledImage.isVisible = true
        }

    }

    fun enable() {
        touchable = Touchable.enabled

        defaultImage.isVisible  = true
        pressedImage.isVisible  = false
        disabledImage.isVisible = false

    }

    fun pressAndDisable(useDisabledStyle: Boolean = true) {
        press()
        disable(useDisabledStyle)
    }

    fun unpressAndEnable() {
        unpress()
        enable()
    }

    fun setStyle(style: ButtonClickableStyle) {
        defaultImage.drawable  = TextureRegionDrawable(style.default)
        pressedImage.drawable  = TextureRegionDrawable(style.pressed)
        disabledImage.drawable = TextureRegionDrawable(style.disabled)
    }

    fun setOnClickListener(sound: Sound? = null/*= SoundUtil.CLICK*/, block: () -> Unit) {
        this.sound = sound
        onClickBlock = block
    }

}