package com.veldan.kingsolomonslots.actors.checkbox

import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.Touchable
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.badlogic.gdx.utils.Disposable
import com.veldan.kingsolomonslots.manager.assets.util.playAdvanced
import com.veldan.kingsolomonslots.utils.cancelCoroutinesAll
import com.veldan.kingsolomonslots.utils.controller.GroupController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class CheckBoxController(override val group: CheckBox) : GroupController, Disposable {

    private val coroutineCheck = CoroutineScope(Dispatchers.Default)

    private val checkFlow = MutableStateFlow(false)

    private var onCheckBlock: (Boolean) -> Unit = { }

    private var sound: Sound? = null

    var checkBoxGroup: CheckBoxGroup? = null



    init {
        group.addListener(getListener())
        collectCheckFlow()
    }



    override fun dispose() {
        cancelCoroutinesAll(coroutineCheck)
    }



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
            isWithin = x in 0f..group.width && y in 0f..group.height
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
            currentCheckedCheckBox?.controller?.uncheckAndEnabled()
            currentCheckedCheckBox = group
        }

        with(group) {
            defaultImage.isVisible = false
            checkImage.isVisible   = true
        }

        checkFlow.value = true
    }

    fun uncheck() {
        with(group) {
            defaultImage.isVisible = true
            checkImage.isVisible   = false
        }
        checkFlow.value = false
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
        group.touchable = Touchable.enabled
    }

    fun disable() {
        group.touchable = Touchable.disabled
    }

    fun setStyle(style: CheckBoxStyle) {
        with(group) {
            defaultImage.drawable = TextureRegionDrawable(style.default)
            checkImage.drawable   = TextureRegionDrawable(style.checked)
        }
    }

    fun setOnCheckListener(sound: Sound? = null/*SoundUtil.CHECK*/, block: (Boolean) -> Unit) {
        this.sound = sound
        onCheckBlock = block
    }

}