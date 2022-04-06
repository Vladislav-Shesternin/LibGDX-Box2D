package com.veldan.pinup.layout

object Layout {
    // ------------------------------------------------------------------------
    // Splash
    // ------------------------------------------------------------------------
    object Splash {
        val PROGRESS_X = 227f
        val PROGRESS_Y = 43f
        val PROGRESS_W = 245f
        val PROGRESS_H = 120f
    }

    // ------------------------------------------------------------------------
    // Menu
    // ------------------------------------------------------------------------
    object Menu {
        val PLAY_Y = 833f
        val OPTIONS_Y = 592f
        val EXIT_Y = 350f

        object Button {
            val X = 120f
            val W = 461f
            val H = 152f

            val TEXT_X = 6f
            val TEXT_Y = 27f
            val TEXT_W = 449f
            val TEXT_H = 115f
        }
    }

    // ------------------------------------------------------------------------
    // Options
    // ------------------------------------------------------------------------
    object Options {

        val PROGRESS_BAR_MUSIC_X = 116f
        val PROGRESS_BAR_MUSIC_Y = 744f

        val PROGRESS_BAR_SOUND_X = 116f
        val PROGRESS_BAR_SOUND_Y = 569f

        val SOUND_X = -1f
        val SOUND_Y = 559f
        val SOUND_W = 124f
        val SOUND_H = 105f

        val MUSIC_X = -1f
        val MUSIC_Y = 735f
        val MUSIC_W = 124f
        val MUSIC_H = 105f

        val BACK_X = 231f
        val BACK_Y = 130f
        val BACK_W = 238f
        val BACK_H = 262f

        object FlagGroup {
            val X = 37f
            val Y = 1107f
            val W = 627f
            val H = 132f

            val FLAG_SPACE_HORIZONTAL = 47f
            val FLAG_FIRST_X = 0f
            val FLAG_Y = 0f
            val FLAG_W = 179f
            val FLAG_H = 132f
        }

        object FlagCheckBoxGroup {
            val X = 85f
            val Y = 1008f
            val W = 533f
            val H = 83f

            val CHECK_BOX_SPACE_HORIZONTAL = 143f
            val CHECK_BOX_FIRST_X = 0f
            val CHECK_BOX_Y = 0f
            val CHECK_BOX_W = 83f
            val CHECK_BOX_H = 83f
        }
    }

    // ------------------------------------------------------------------------
    // Game
    // ------------------------------------------------------------------------
    object Game {
        val BALANCE_PANEL_X = 172f
        val BALANCE_PANEL_Y = 1225f
        val BALANCE_PANEL_W = 356f
        val BALANCE_PANEL_H = 155f

        val BALANCE_TEXT_X = 11f
        val BALANCE_TEXT_Y = 14f
        val BALANCE_TEXT_W = 335f
        val BALANCE_TEXT_H = 128f

        val BET_PANEL_X = 129f
        val BET_PANEL_Y = 430f
        val BET_PANEL_W = 443f
        val BET_PANEL_H = 100f

        val BET_TEXT_X = 0f
        val BET_TEXT_Y = 12f
        val BET_TEXT_W = 443f
        val BET_TEXT_H = 77f

        val BET_LABEL_X = 0f
        val BET_LABEL_Y = 73f
        val BET_LABEL_W = 443f
        val BET_LABEL_H = 66f

        val BET_PLUS_X = 572f
        val BET_PLUS_Y = 430f
        val BET_PLUS_W = 123f
        val BET_PLUS_H = 100f

        val BET_MINUS_X = 5f
        val BET_MINUS_Y = 425f
        val BET_MINUS_W = 124f
        val BET_MINUS_H = 105f

        val MENU_X = 562f
        val MENU_Y = 175f
        val MENU_W = 107f
        val MENU_H = 77f

        val AUTO_SPIN_X = 37f
        val AUTO_SPIN_Y = 161f
        val AUTO_SPIN_W = 106f
        val AUTO_SPIN_H = 108f

        val SPIN_X = 198f
        val SPIN_Y = 62f
        val SPIN_W = 304f
        val SPIN_H = 304f

        val SPIN_TEXT_X = 23f
        val SPIN_TEXT_Y = 89f
        val SPIN_TEXT_W = 258f
        val SPIN_TEXT_H = 125f

        val SLOT_GROUP_X = 0f
        val SLOT_GROUP_Y = 583f
    }

    // ------------------------------------------------------------------------
    // ProgressBar Group
    // ------------------------------------------------------------------------
    object ProgressBar {
        val W = 576f
        val H = 92f

        val BORDERS_X = 40f
        val BORDERS_Y = 42f
        val BORDERS_W = 498f
        val BORDERS_H = 16f

        val CONTROLLER_MIN_X = 9f
        val CONTROLLER_MAX_X = 495f
        val CONTROLLER_Y = 17f
        val CONTROLLER_W = 70f
        val CONTROLLER_H = 70f
    }

    // ------------------------------------------------------------------------
    // Slot Group
    // ------------------------------------------------------------------------
    object Slot {
        val W = 170f
        val H = 18096f

        val START_Y = 7f
        val END_Y   = -17557f

        val SLOT_ITEM_SPACE_VERTICAL = 11f
        val SLOT_ITEM_W = 170f
        val SLOT_ITEM_H = 170f
    }

    // ------------------------------------------------------------------------
    // Glow Group
    // ------------------------------------------------------------------------
    object Glow {
        val W = 100f
        val H = 592f

        val GLOW_ITEM_SPACE_VERTICAL = 23f
        val GLOW_ITEM_W = 100f
        val GLOW_ITEM_H = 100f
    }

    // ------------------------------------------------------------------------
    // SlotGroup Group
    // ------------------------------------------------------------------------
    object SlotGroup {
        val W = 700f
        val H = 624f

        val MASK_X = 74f
        val MASK_Y = 34f
        val MASK_W = 554f
        val MASK_H = 547f

        val SLOT_FIRST_X = 5f
        val SLOT_SPACE_HORIZONTAL = 18f
    }

}