package com.veldan.pinup.manager.assets.util

import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader

data class FontTTFData(
    val name: String,
    val parameters: FreetypeFontLoader.FreeTypeFontLoaderParameter,
) {
    lateinit var font: BitmapFont
}

data class FontBPMData(
    val path: String,
) {
    lateinit var font: BitmapFont
}



data class MusicData(
    val path: String,
) {
    lateinit var music: Music
}



data class SoundData(
    val path: String,
) {
    lateinit var sound: Sound
}



data class TextureData(
    val path: String,
) {
    lateinit var texture: Texture
}