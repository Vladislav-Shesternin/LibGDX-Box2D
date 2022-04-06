package com.veldan.pinup.actors.button

import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.Touchable
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.veldan.pinup.advanced.group.AbstractAdvancedGroup
import com.veldan.pinup.manager.assets.SpriteManager
import com.veldan.pinup.manager.assets.util.playAdvanced

class ButtonClickable(
    style: Style? = null
) : AbstractAdvancedGroup() {
    override val controller = ButtonClickableController(this)

    private var _style = style

    private val image        = Image().apply { isVisible = false }
    private val defaultImage = if (style != null) Image(style.default) else Image()
    private val pressedImage = (if (style != null) Image(style.pressed) else Image()).apply { isVisible = false }

    private var onClickBlock: () -> Unit = { }

    var sound: Sound? = null



    init {
        addListener(getListener())
        addAndFillActors(getActors())
    }



    private fun getActors() = listOf<Actor>(
        image,
        defaultImage,
        pressedImage,
    )



    private fun getListener() = object : InputListener() {
        var isWithin = false

        override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
            sound?.playAdvanced()
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

    fun pressAndDisable(useDisabledStyle: Boolean = true) {
        press()
        disable(useDisabledStyle)
    }

    fun unpressAndEnabled() {
        unpress()
        enable()
    }

    fun enable() {
        touchable = Touchable.enabled
        _style?.disabled?.let { resetImage() }
    }

    fun disable(useDisabledStyle: Boolean = true) {
        touchable = Touchable.disabled
        _style?.disabled?.let { if (useDisabledStyle) setImage(it) }
    }

    fun setStyle(style: Style) {
        _style = style
        defaultImage.drawable = TextureRegionDrawable(style.default)
        pressedImage.drawable = TextureRegionDrawable(style.pressed)
    }

    fun setImage(texture: Texture) {
        image.apply {
            drawable = TextureRegionDrawable(texture)
            isVisible = true
        }
        defaultImage.isVisible = false
        pressedImage.isVisible = false
    }

    fun resetImage() {
        image.isVisible = false
        unpress()
    }

    fun setOnClickListener(sound: Sound? = null/*SoundUtil.CLICK*/, block: () -> Unit) {
        this.sound = sound
        onClickBlock = block
    }



    data class Style(
        val default: Texture,
        val pressed: Texture,
        val disabled: Texture? = null,
    ) {
        companion object {
            val button_1 get() = Style(
                default = SpriteManager.MenuSprite.BUTTON_DEF_1.data.texture,
                pressed = SpriteManager.MenuSprite.BUTTON_PRESS_1.data.texture,
            )
            val back get() = Style(
                default = SpriteManager.OptionsSprite.BACK_DEF.data.texture,
                pressed = SpriteManager.OptionsSprite.BACK_PRESS.data.texture,
            )
            val plus get() = Style(
                default  = SpriteManager.GameSprite.PLUS_DEF.data.texture,
                pressed  = SpriteManager.GameSprite.PLUS_PRESS.data.texture,
                disabled = SpriteManager.GameSprite.PLUS_DIS.data.texture,
            )
            val minus get() = Style(
                default  = SpriteManager.GameSprite.MINUS_DEF.data.texture,
                pressed  = SpriteManager.GameSprite.MINUS_PRESS.data.texture,
                disabled = SpriteManager.GameSprite.MINUS_DIS.data.texture,
            )
            val menu get() = Style(
                default = SpriteManager.GameSprite.MENU_DEF.data.texture,
                pressed = SpriteManager.GameSprite.MENU_PRESS.data.texture,
            )
            val autoSpin get() = Style(
                default  = SpriteManager.GameSprite.AUTO_SPIN_DEF.data.texture,
                pressed  = SpriteManager.GameSprite.AUTO_SPIN_PRESS.data.texture,
                disabled = SpriteManager.GameSprite.AUTO_SPIN_DIS.data.texture,
            )
            val spin get() = Style(
                default  = SpriteManager.GameSprite.SPIN_DEF.data.texture,
                pressed  = SpriteManager.GameSprite.SPIN_PRESS.data.texture,
                disabled = SpriteManager.GameSprite.SPIN_DIS.data.texture,
            )
        }
    }

}