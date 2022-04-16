package com.veldan.kingsolomonslots.layout

object Layout {
    // ------------------------------------------------------------------------
    // Splash
    // ------------------------------------------------------------------------
    object Splash {

        val PANEL_X = 532f
        val PANEL_Y = 25f
        val PANEL_W = 337f
        val PANEL_H = 102f

        val PROGRESS_X = 532f
        val PROGRESS_Y = 25f
        val PROGRESS_W = 337f
        val PROGRESS_H = 102f
    }

    // ------------------------------------------------------------------------
    // Options
    // ------------------------------------------------------------------------
    object Options {

        val PROGRESS_BAR_MUSIC_X = 126f
        val PROGRESS_BAR_MUSIC_Y = 475f

        val PROGRESS_BAR_SOUND_X = 126f
        val PROGRESS_BAR_SOUND_Y = 141f

        val SOUND_X = 11f
        val SOUND_Y = 130f
        val SOUND_W = 106f
        val SOUND_H = 106f

        val MUSIC_X = 11f
        val MUSIC_Y = 464f
        val MUSIC_W = 106f
        val MUSIC_H = 106f

        val TUTORIAL_IMAGE_X = 46f
        val TUTORIAL_IMAGE_Y = 279f
        val TUTORIAL_IMAGE_W = 143f
        val TUTORIAL_IMAGE_H = 143f

        val TUTORIAL_CHECK_BOX_X = 202f
        val TUTORIAL_CHECK_BOX_Y = 305f
        val TUTORIAL_CHECK_BOX_W = 92f
        val TUTORIAL_CHECK_BOX_H = 92f

        val BACK_X = 1244f
        val BACK_Y = 268f
        val BACK_W = 126f
        val BACK_H = 164f
    }

    // ------------------------------------------------------------------------
    // Game
    // ------------------------------------------------------------------------
    object Game {
        val BALANCE_PANEL_X = 475f
        val BALANCE_PANEL_Y = 18f
        val BALANCE_PANEL_W = 449f
        val BALANCE_PANEL_H = 83f

        val BALANCE_TEXT_X = 11f
        val BALANCE_TEXT_Y = 6f
        val BALANCE_TEXT_W = 428f
        val BALANCE_TEXT_H = 72f

        val BET_PANEL_X = 6f
        val BET_PANEL_Y = 306f
        val BET_PANEL_W = 295f
        val BET_PANEL_H = 90f

        val BET_TEXT_X = 19f
        val BET_TEXT_Y = 22f
        val BET_TEXT_W = 256f
        val BET_TEXT_H = 47f

        val BET_PLUS_X = 169f
        val BET_PLUS_Y = 143f
        val BET_PLUS_W = 113f
        val BET_PLUS_H = 113f

        val BET_MINUS_X = 26f
        val BET_MINUS_Y = 143f
        val BET_MINUS_W = 113f
        val BET_MINUS_H = 113f

        val OPTIONS_X = 1271f
        val OPTIONS_Y = 571f
        val OPTIONS_W = 99f
        val OPTIONS_H = 99f

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
        val W = 1118f
        val H = 84f

        val PROGRESS_X = 31f
        val PROGRESS_Y = 21f
        val PROGRESS_W = 1047f
        val PROGRESS_H = 42f

        val CONTROLLER_MIN_X = 3f
        val CONTROLLER_MAX_X = 1042f
        val CONTROLLER_Y = 6f
        val CONTROLLER_W = 73f
        val CONTROLLER_H = 73f
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
        val W = 340f
        val H = 706f

        val GLOW_ITEM_FIRST_Y = 364f
        val GLOW_ITEM_SPACE_VERTICAL = -160f
        val GLOW_ITEM_W = 340f
        val GLOW_ITEM_H = 342f
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

        val GLOW_FIRST_X = -11f
        val GLOW_Y = -48f
        val GLOW_SPACE_HORIZONTAL = -153f
    }

    // ------------------------------------------------------------------------
    // ClickAnim Group
    // ------------------------------------------------------------------------
    object ClickAnim {
        val W = 234f
        val H = 327f
    }

    // ------------------------------------------------------------------------
    // MiniGame Group
    // ------------------------------------------------------------------------
    object MiniGame {

        object TopPanel {
            val X = 0f
            val Y = 1200f
            val W = 700f
            val H = 200f

            val TIME_X = 0f
            val TIME_Y = 125f
            val TIME_W = 320f
            val TIME_H = 75f

            val TIME_TEXT_X = 0f
            val TIME_TEXT_Y = 0f
            val TIME_TEXT_W = 320f
            val TIME_TEXT_H = 125f

            val BONUS_X = 380f
            val BONUS_Y = 125f
            val BONUS_W = 320f
            val BONUS_H = 75f

            val BONUS_TEXT_X = 380f
            val BONUS_TEXT_Y = 0f
            val BONUS_TEXT_W = 320f
            val BONUS_TEXT_H = 125f
        }

        object GameGroup {
            val COUNT_DOWN_X = 90f
            val COUNT_DOWN_Y = 309f
            val COUNT_DOWN_W = 520f
            val COUNT_DOWN_H = 781f

            val BAG_X = 124f
            val BAG_Y = 333f
            val BAG_W = 453f
            val BAG_H = 733f

            val CLICK_ANIM_X = 233f
            val CLICK_ANIM_Y = 373f
        }

        object ResultGroup{
            val BET_PANEL_X = 130f
            val BET_PANEL_Y = 896f
            val BET_PANEL_W = 443f
            val BET_PANEL_H = 100f

            val BET_LABEL_X = 0f
            val BET_LABEL_Y = 73f
            val BET_LABEL_W = 443f
            val BET_LABEL_H = 66f

            val BET_TEXT_X = 0f
            val BET_TEXT_Y = 12f
            val BET_TEXT_W = 443f
            val BET_TEXT_H = 77f

            val MULTIPLICATION_X = 0f
            val MULTIPLICATION_Y = 752f
            val MULTIPLICATION_W = 700f
            val MULTIPLICATION_H = 144f

            val EQUALS_X = 0f
            val EQUALS_Y = 509f
            val EQUALS_W = 700f
            val EQUALS_H = 144f

            val BONUS_X = 0f
            val BONUS_Y = 627f
            val BONUS_W = 700f
            val BONUS_H = 125f

            val RESULT_X = 0f
            val RESULT_Y = 364f
            val RESULT_W = 700f
            val RESULT_H = 145f
        }
    }

    // ------------------------------------------------------------------------
    // SuperGame Group
    // ------------------------------------------------------------------------
    object SuperGame {

        object BonusGroup{
            val BONUS_LABEL_X = 0f
            val BONUS_LABEL_Y = 1325f
            val BONUS_LABEL_W = 700f
            val BONUS_LABEL_H = 75f

            val BONUS_TEXT_X = 0f
            val BONUS_TEXT_Y = 1200f
            val BONUS_TEXT_W = 700f
            val BONUS_TEXT_H = 125f
        }

        object GameGroup {
            val BOX_GROUP_X = 28f
            val BOX_GROUP_Y = 373f
        }

        object ResultGroup{
            val BET_PANEL_X = 130f
            val BET_PANEL_Y = 896f
            val BET_PANEL_W = 443f
            val BET_PANEL_H = 100f

            val BET_LABEL_X = 0f
            val BET_LABEL_Y = 73f
            val BET_LABEL_W = 443f
            val BET_LABEL_H = 66f

            val BET_TEXT_X = 0f
            val BET_TEXT_Y = 12f
            val BET_TEXT_W = 443f
            val BET_TEXT_H = 77f

            val MULTIPLICATION_X = 0f
            val MULTIPLICATION_Y = 752f
            val MULTIPLICATION_W = 700f
            val MULTIPLICATION_H = 144f

            val EQUALS_X = 0f
            val EQUALS_Y = 509f
            val EQUALS_W = 700f
            val EQUALS_H = 144f

            val BONUS_X = 0f
            val BONUS_Y = 627f
            val BONUS_W = 700f
            val BONUS_H = 125f

            val RESULT_X = 0f
            val RESULT_Y = 364f
            val RESULT_W = 700f
            val RESULT_H = 145f
        }
    }

    // ------------------------------------------------------------------------
    // Box Group
    // ------------------------------------------------------------------------
    object BoxGroup{
        val W = 645f
        val H = 654f

        val BOX_PIZE_GROUP_X = 4f
        val BOX_PIZE_GROUP_Y = 3f
        val BOX_PIZE_GROUP_W = 637f
        val BOX_PIZE_GROUP_H = 648f

        val BOX_SPACE_HORIZONTAL = 10f
        val BOX_SPACE_VERTICAL   = 18f
        val BOX_W = 209f
        val BOX_H = 206f

        val PRIZE_SPACE_HORIZONTAL = 18f
        val PRIZE_SPACE_VERTICAL   = 24f
        val PRIZE_W = 200f
        val PRIZE_H = 200f
    }
}