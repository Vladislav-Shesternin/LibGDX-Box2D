package com.veldan.kingsolomonslots.actors.button

import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.Touchable
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.veldan.kingsolomonslots.manager.assets.util.SoundUtil
import com.veldan.kingsolomonslots.manager.assets.util.playAdvanced
import com.veldan.kingsolomonslots.utils.controller.GroupController

class ButtonClickableController(override val group: ButtonClickable) : GroupController {

    private var _style = group.style

    private var onClickBlock: () -> Unit = { }

    var sound: Sound? = null

    
    
    init {
        group.addListener(getListener())
    }
    


    private fun getListener() = object : InputListener() {
        var isWithin = false

        override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
            sound?.playAdvanced()
            touchDragged(event, x, y, pointer)
            return true
        }

        override fun touchDragged(event: InputEvent?, x: Float, y: Float, pointer: Int) {
            isWithin = x in 0f..group.width && y in 0f..group.height
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
        with(group) {
            defaultImage.isVisible = false
            pressedImage.isVisible = true
        }
    }

    fun unpress() {
        with(group) {
            defaultImage.isVisible = true
            pressedImage.isVisible = false
        }
    }

    fun pressAndDisable(useDisabledStyle: Boolean = true) {
        press()
        disable(useDisabledStyle)
    }

    fun unpressAndEnabled() {
        unpress()
        enable()
    }

    fun enable() {
        group.touchable = Touchable.enabled
        _style?.disabled?.let { resetImage() }
    }

    fun disable(useDisabledStyle: Boolean = true) {
        group.touchable = Touchable.disabled
        _style?.disabled?.let { if (useDisabledStyle) setImage(it) }
    }

    fun setStyle(style: ButtonClickableStyle) {
        _style = style
        with(group) {
            defaultImage.drawable = TextureRegionDrawable(style.default)
            pressedImage.drawable = TextureRegionDrawable(style.pressed)
        }
    }

    fun setImage(region: TextureRegion) {
        with(group) {
            image.apply {
                drawable = TextureRegionDrawable(region)
                isVisible = true
            }
            defaultImage.isVisible = false
            pressedImage.isVisible = false
        }
    }

    fun resetImage() {
        group.image.isVisible = false
        unpress()
    }

    fun setOnClickListener(sound: Sound? = SoundUtil.CLICK, block: () -> Unit) {
        this.sound = sound
        onClickBlock = block
    }

}