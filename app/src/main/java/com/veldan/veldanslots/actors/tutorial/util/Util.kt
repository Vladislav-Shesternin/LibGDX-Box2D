package com.veldan.veldanslots.actors.tutorial.util

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.veldan.veldanslots.layout.Layout

data class TutorialItem(
    val regionFrame : TextureRegion,
    val regionDialog: TextureRegion,
    val layout      : Layout.TutorialGroup.TutorialItemLayout,
    val text        : String,
)