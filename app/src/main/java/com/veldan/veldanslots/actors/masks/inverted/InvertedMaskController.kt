package com.veldan.veldanslots.actors.masks.inverted

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.math.Vector2
import com.veldan.veldanslots.utils.controller.GroupController
import com.veldan.veldanslots.utils.zeroScreenVector

class InvertedMaskController(override val group: InvertedMask) : GroupController {

    private lateinit var localToScreenCoordinates: Vector2
    private lateinit var localToScreenSize       : Vector2

    private var mX: Float = 0f
    private var mY: Float = 0f
    private var mW: Float = 0f
    private var mH: Float = 0f



    private fun updateCoordinatesAndSize() {
        with(group) {
            localToScreenCoordinates = localToScreenCoordinates(Vector2())
            localToScreenSize        = stage.viewport.project(Vector2(width, height))

            mX = localToScreenCoordinates.x
            mY = Gdx.graphics.height - localToScreenCoordinates.y
            mW = localToScreenSize.x - stage.viewport.zeroScreenVector.x
            mH = localToScreenSize.y - stage.viewport.zeroScreenVector.y
        }
    }

    private fun drawScissor(batch: Batch?, parentAlpha: Float) {
        Gdx.gl.glEnable(GL20.GL_SCISSOR_TEST)
        Gdx.gl.glScissor(mX.toInt(), mY.toInt(), mW.toInt(), mH.toInt())

        batch?.drawMask(parentAlpha)

        Gdx.gl.glDisable(GL20.GL_SCISSOR_TEST)
    }

    private fun Batch.drawMask(parentAlpha: Float) {
        Gdx.gl.glColorMask(false, false, false, true)

        setBlendFunction(GL20.GL_ZERO, GL20.GL_ONE_MINUS_SRC_ALPHA)

        group.drawSuper(this, parentAlpha)

        drawMasked()
    }

    private fun Batch.drawMasked() {
        setBlendFunction(GL20.GL_ZERO, GL20.GL_SRC_ALPHA)
        with(group) { draw(region, x, y, width, height) }
        flush()

        Gdx.gl.glColorMask(true, true, true, true)
        setBlendFunction(GL20.GL_DST_ALPHA, GL20.GL_ONE_MINUS_DST_ALPHA)
        with(group) { draw(region, x, y, width, height) }
        flush()

        setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA)
    }



    fun draw(batch: Batch?, parentAlpha: Float) {
        if (group.stage != null) {
            batch?.flush()
            updateCoordinatesAndSize()
            drawScissor(batch, parentAlpha)
        }
    }

}