package com.veldan.pinup.actors.slot.util.combination

import com.veldan.pinup.actors.slot.util.SlotItem
import com.veldan.pinup.actors.slot.util.SlotItemContainer

/**
 * Матрица 3 на 3 - содержит 3 слота (a,b,c) по 3 элемента (0,1,2).
 * winItemList - содержит список победных Item.
 * WILD не нужно добавлять к winItemList, если он есть в слотах он будет учтен.
 * */
class Matrix3x3(
    private val winItemList: List<Item>? = null,

    a0: Item,
    a1: Item,
    a2: Item,

    b0: Item,
    b1: Item,
    b2: Item,

    c0: Item,
    c1: Item,
    c2: Item,
) {

    private val slotA = intArrayOf(a0.index, a1.index, a2.index)
    private val slotB = intArrayOf(b0.index, b1.index, b2.index)
    private val slotC = intArrayOf(c0.index, c1.index, c2.index)

    private val slotList = listOf(slotA, slotB, slotC)

    private val shuffledSlotItemList = SlotItemContainer.list.shuffled()

    val intersectionList: List<Intersection>? by lazy { generateIntersectionList() }
    val winSlotItemList : List<SlotItem>?      by lazy { generateWinSlotItemSet() }


    /** Генераторовать список пересечений:
     *  Проверяет содержатся ли в слотах матрицы элементы определенные в winItemList и равен ли он Item.WILD.index,
     *  если содержаться -> добавляет в список пересечений Пересечение с указанными индексом слота и индексом ряда;
     *  если не содержаться -> ничего не происходит;
     *  return:
     *  если список переечений пуст -> null;
     *  если список пересечений не пуст -> етот список пересечений;
     * */
    private fun generateIntersectionList(): List<Intersection>? {
        val intersectionList = mutableListOf<Intersection>().apply {
            if (winItemList != null) {
                val winIndexList = winItemList.map { it.index } + Item.WILD.index

                slotList.onEachIndexed { slotIndex, slot -> slot.onEachIndexed { itemIndex, item ->
                    if (winIndexList.contains(item)) add(Intersection(
                        slotIndex = slotIndex,
                        rowIndex = itemIndex
                    ))
                } }
            }
        }
        return if (intersectionList.isEmpty()) null else intersectionList
    }

    /** Генерировать список победных элементов слотов:
     *  return:
     *  если список пересечиний null -> null;
     *  если список пересечений не пуст -> список победных элементов слотов;
     *
     *  1. Полученаем список индексов элементов слотов со списка пересечений;
     *  2. Получаем элементы слотов с shuffledSlotItemList по индексам полученым в первом шаге
     *     если индекс == 100 получаем WILD.
     * */
    private fun generateWinSlotItemSet(): List<SlotItem>? {
        return if (intersectionList == null) null
        else {
            val winSlotItemIndexList = mutableListOf<Int>()
            var slotItem: SlotItem

            mutableListOf<SlotItem>().apply {
                intersectionList!!.onEach { winSlotItemIndexList.add(slotList[it.slotIndex][it.rowIndex]) }
                winSlotItemIndexList.onEach { winSlotItemIndex ->
                    slotItem = when (winSlotItemIndex) {
                        100  -> SlotItemContainer.wild
                        else -> shuffledSlotItemList[winSlotItemIndex]
                    }
                    add(slotItem)
                }
            }
        }
    }


    /** Генерировать слот по индексу:
     *  return: Список элементов слотов;
     *
     *  Список элементов слотов генерируеться с shuffledSlotItemList элементы с которого получаем благодаря индексам со слотов матрицы.
     *  Если индекс == 100 добавляем WILD;
     *  В ином случае добавляем элемент слота с shuffledSlotItemList по индексу со слота матрицы.
     * */
    fun generateSlot(slotIndex: Int): List<SlotItem> = mutableListOf<SlotItem>().apply {
        var slotItem: SlotItem
        slotList[slotIndex].onEach { slotItemIndex ->
            slotItem = when (slotItemIndex) {
                100  -> SlotItemContainer.wild
                else -> shuffledSlotItemList[slotItemIndex]
            }
            add(slotItem)
        }
    }



    data class Intersection(
        val slotIndex: Int,
        val rowIndex : Int,
    )


    /** Элемент - содержит столько элементов сколько есть элементов слотов в SlotItemContainer.list
     *  index - индекс элемента слота который будет получен с shuffledSlotItemList.
     * */
    enum class Item(val index: Int) {
        WILD(100),

        A(0),
        B(1),
        C(2),
        D(3),
        E(4),
        F(5),
        G(6),
        H(7),
    }


}
