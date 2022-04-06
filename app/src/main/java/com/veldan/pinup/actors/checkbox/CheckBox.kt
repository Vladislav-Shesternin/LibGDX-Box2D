package com.veldan.pinup.actors.checkbox

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
import com.veldan.pinup.utils.cancelCoroutinesAll
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class CheckBox(
    style: Style? = null,
) : AbstractAdvancedGroup() {
    override val controller = CheckBoxController(this)

    private val defaultImage = if (style != null)  Image(style.default) else Image()
    private val checkImage   = (if (style != null) Image(style.checked) else Image()).apply { isVisible = false }

    private val coroutineCheck = CoroutineScope(Dispatchers.Default)

    private var onCheckBlock: (Boolean) -> Unit = { }

    var sound        : Sound?         = null
    var checkBoxGroup: CheckBoxGroup? = null

    val checkFlow = MutableStateFlow(false)



    init {
        addListener(getListener())
        addAndFillActors(getActors())
        collectCheckFlow()
    }

    override fun dispose() {
        super.dispose()
        cancelCoroutinesAll(coroutineCheck)
    }



    private fun getActors() = listOf<Actor>(
        defaultImage,
        checkImage,
    )



    private fun collectCheckFlow() {
        coroutineCheck.launch { checkFlow.collect { isCheck -> onCheckBlock(isCheck) } }
    }



    private fun getListener() = object : InputListener() {
        var isWithin = false

        override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
            sound?.playAdvanced()
            touchDragged(event, x, y, pointer)
            return true
        }

        override fun touchDragged(event: InputEvent?, x: Float, y: Float, pointer: Int) {
            isWithin = x in 0f..width && y in 0f..height
        }

        override fun touchUp(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int) {
            if (isWithin) {
                if(checkBoxGroup != null) checkAndDisable()
                else { if (checkFlow.value.not()) check() else uncheck() }
            }
        }
    }



    fun check() {
        checkBoxGroup?.run {
            currentCheckedCheckBox?.uncheckAndEnabled()
            currentCheckedCheckBox = this@CheckBox
        }

        defaultImage.isVisible = false
        checkImage.isVisible   = true
        checkFlow.value        = true
    }

    fun uncheck() {
        defaultImage.isVisible = true
        checkImage.isVisible   = false
        checkFlow.value        = false
    }

    fun checkAndDisable() {
        check()
        disable()
    }

    fun uncheckAndEnabled() {
        uncheck()
        enable()
    }

    fun enable() {
        touchable = Touchable.enabled
    }

    fun disable() {
        touchable = Touchable.disabled
    }

    fun setStyle(style: Style) {
        defaultImage.drawable = TextureRegionDrawable(style.default)
        checkImage.drawable   = TextureRegionDrawable(style.checked)
    }

    fun setOnCheckListener(sound: Sound? = null/*SoundUtil.CHECK*/, block: (Boolean) -> Unit) {
        this.sound = sound
        onCheckBlock = block
    }



    data class Style(
        val default: Texture,
        val checked: Texture,
    ) {
        companion object {
            val style_1 get() = Style(
                default = SpriteManager.OptionsSprite.CHECK_BOX_DEF_1.data.texture,
                checked = SpriteManager.OptionsSprite.CHECK_BOX_CHECK_1.data.texture,
            )
        }
    }

}