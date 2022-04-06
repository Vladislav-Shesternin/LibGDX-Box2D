package com.veldan.pinup.actors.slot.util

/**
 * Доступно 3 победных индекса (101, 102, 103)
 * 100 - WILD
 * созданы для явного определения победных элементов
 * */
private val _winIndexArray = intArrayOf(100, 101, 102, 103)

val winIndexArray = intArrayOf(100, 1, 2, 3)

/**
 * Преобразовывает победные индексы в индексы доступных элементов
 * */
val Int.toSlotItemIndex get() = when (this) {
    _winIndexArray[1] -> winIndexArray[1]
    _winIndexArray[2] -> winIndexArray[2]
    _winIndexArray[3] -> winIndexArray[3]
    else              -> this
}

/**
 * Доступно 5 индексов (так как 3 победных уже заняты)
 * */
enum class CombinationMatrix(
    val matrix: Matrix3x3,
) {
    _1(Matrix3x3(
        a0 = 100, b0 = 1  , c0 = 2  ,
        a1 = 3  , b1 = 101, c1 = 4  ,
        a2 = 5  , b2 = 102  , c2 = 101,
    ))
}

/**
 * Матрица 3 на 3 - предназначена для заполнения индексами доступных элементов слотов
 * Использование:
 * 1. Приготовить перемешаный список элементов;
 * 2. Заполнить слоты элементами с подготовленного списка по индексам полученых в матрице для определённых слотов;
 * 3. Передать jointList - в менеджер определения победных слотов, так как он содержит индексы победных слотов и рядов.
 * */
data class Matrix3x3(
    private val a0: Int,
    private val a1: Int,
    private val a2: Int,

    private val b0: Int,
    private val b1: Int,
    private val b2: Int,

    private val c0: Int,
    private val c1: Int,
    private val c2: Int,
) {
    val slot1 = intArrayOf(a0, a1, a2)
    val slot2 = intArrayOf(b0, b1, b2)
    val slot3 = intArrayOf(c0, c1, c2)

    val slotList = listOf(slot1, slot2, slot3)

    val jointList = mutableListOf<Joint>().apply {
        slotList.onEachIndexed { slotIndex, slot ->
            slot.onEachIndexed{ rowIndex, item -> if (_winIndexArray.contains(item)) add(Joint(
                    slotIndex = slotIndex,
                    rowIndex  = rowIndex
                ))
            }
        }
    }



    data class Joint(
        val slotIndex: Int,
        val rowIndex : Int,
    )
}