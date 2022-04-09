package com.veldan.pinup.actors.animation.click

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.veldan.pinup.manager.assets.SpriteManager
import com.veldan.pinup.utils.controller.GroupController

class ClickAnimController(override val group: ClickAnim) : GroupController {

    private val keyFrameList = SpriteManager.AnimationList.CLICK.dataList.map { it.texture }

    private val animation = Animation<Texture>(0.066f, *keyFrameList.toTypedArray())

    private var time = 0f

    private var isPlay = false



    fun drawAnimation(deltaTime: Float) {
        if (isPlay) {
            group.image.drawable = TextureRegionDrawable(animation.getKeyFrame(time, true))
            time += deltaTime
        } else time = 0f
    }

    fun play() {
        isPlay = true
    }

    fun stop() {
        isPlay = false
    }

}