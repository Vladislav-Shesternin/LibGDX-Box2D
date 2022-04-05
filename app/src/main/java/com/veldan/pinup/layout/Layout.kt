package com.veldan.pinup.layout

object Layout {
    // ------------------------------------------------------------------------
    // Splash
    // ------------------------------------------------------------------------
    object Splash {
        val PROGRESS_X = 227f
        val PROGRESS_Y = 1237f
        val PROGRESS_W = 245f
        val PROGRESS_H = 120f
    }

    // ------------------------------------------------------------------------
    // Menu
    // ------------------------------------------------------------------------
    object Menu {
        val PLAY_Y = 416f
        val OPTIONS_Y = 658f
        val EXIT_Y = 899f

        object Button {
            val X = 120f
            val W = 461f
            val H = 152f

            val TEXT_X = 6f
            val TEXT_Y = 9f
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
        val BALANCE_LABEL_X = 9f
        val BALANCE_LABEL_Y = 67f
        val BALANCE_LABEL_W = 358f
        val BALANCE_LABEL_H = 79f

        val BALANCE_PANEL_X = 9f
        val BALANCE_PANEL_Y = 146f
        val BALANCE_PANEL_W = 358f
        val BALANCE_PANEL_H = 156f

        val BET_LABEL_X = 9f
        val BET_LABEL_Y = 376f
        val BET_LABEL_W = 358f
        val BET_LABEL_H = 79f

        val BET_PANEL_X = 9f
        val BET_PANEL_Y = 455f
        val BET_PANEL_W = 358f
        val BET_PANEL_H = 80f

        val BET_PLUS_X = 236f
        val BET_PLUS_Y = 551f
        val BET_PLUS_W = 83f
        val BET_PLUS_H = 83f

        val BET_MINUS_X = 57f
        val BET_MINUS_Y = 551f
        val BET_MINUS_W = 83f
        val BET_MINUS_H = 83f

        val MENU_X = 1256f
        val MENU_Y = 537f
        val MENU_W = 100f
        val MENU_H = 69f

        val AUTOSPIN_X = 1069f
        val AUTOSPIN_Y = 522f
        val AUTOSPIN_W = 100f
        val AUTOSPIN_H = 100f

        val SPIN_X = 1082f
        val SPIN_Y = 218f
        val SPIN_W = 263f
        val SPIN_H = 263f

        val SLOT_GROUP_X = 376f
        val SLOT_GROUP_Y = 25f

        val ROULETTE_X = 379f
        val ROULETTE_Y = 29f
        val ROULETTE_W = 643f
        val ROULETTE_H = 643f

        val INDICATOR_X = 608.7f
        val INDICATOR_Y = 190.8f
        val INDICATOR_W = 185f
        val INDICATOR_H = 296f

        val EQUALLY_WILD_X = 1195f
        val EQUALLY_WILD_Y = 25f
        val EQUALLY_WILD_W = 167f
        val EQUALLY_WILD_H = 110f

        val SLOT_ITEM_EQUALLY_WILD_X = 1065f
        val SLOT_ITEM_EQUALLY_WILD_Y = 25f
        val SLOT_ITEM_EQUALLY_WILD_W = 110f
        val SLOT_ITEM_EQUALLY_WILD_H = 110f

        val BONUS_SPINS_X = 1041f
        val BONUS_SPINS_Y = 157f
        val BONUS_SPINS_W = 345f
        val BONUS_SPINS_H = 40f

        val DIALOG_PANEL_X = 133f
        val DIALOG_PANEL_Y = 67f
        val DIALOG_PANEL_W = 1133f
        val DIALOG_PANEL_H = 567f

        object BonusSpinsDialogPanel {
            val LABEL_TOP_X = 38f
            val LABEL_TOP_Y = 77f
            val LABEL_TOP_W = 1057f
            val LABEL_TOP_H = 79f

            val LABEL_BOTTOM_X = 38f
            val LABEL_BOTTOM_Y = 156f
            val LABEL_BOTTOM_W = 1057f
            val LABEL_BOTTOM_H = 251f

            val BUTTON_X = 508f
            val BUTTON_Y = 416f
            val BUTTON_W = 118f
            val BUTTON_H = 118f
        }

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
        val W = 110f
        val H = 12287f
        val START_Y = -11676f
        val END_Y = 9f

        val SLOT_ITEM_SPACE_VERTICAL = 13f
        val SLOT_ITEM_W = 110f
        val SLOT_ITEM_H = 110f
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
        val W = 650f
        val H = 650f

        val MASK_GROUP_X = 15f
        val MASK_GROUP_Y = 15f
        val MASK_GROUP_W = 620f
        val MASK_GROUP_H = 620f

        val SLOT_FIRS_X = 4f
        val SLOT_SPACE_HORIZONTAL = 15f

        val GLOW_FIRS_X = 24f
        val GLOW_SPACE_HORIZONTAL = 25f
        val GLOW_Y = 29f

        val SEPARATOR_X = 134.2f
        val SEPARATOR_Y = 50f
        val SEPARATOR_W = 382f
        val SEPARATOR_H = 550f
    }

}