package com.veldan.kingsolomonslots.actors.slot.util.combination

import com.veldan.kingsolomonslots.actors.slot.util.SlotItem
import com.veldan.kingsolomonslots.actors.slot.util.SlotItemContainer

/**
 * Матрица 3 на 5 - содержит 5 слотов (a,b,c,d,e) по 3 элемента (0,1,2).
 * winItemList - содержит список победных Item.
 * WILD не нужно добавлять к winItemList, если он есть в слотах он будет учтен.
 * */
class Matrix3x5(
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

    d0: Item,
    d1: Item,
    d2: Item,

    e0: Item,
    e1: Item,
    e2: Item,
) {

    private val slotA = listOf<Item>(a0, a1, a2)
    private val slotB = listOf<Item>(b0, b1, b2)
    private val slotC = listOf<Item>(c0, c1, c2)
    private val slotD = listOf<Item>(d0, d1, d2)
    private val slotE = listOf<Item>(e0, e1, e2)

    private val slotList = listOf(slotA, slotB, slotC, slotD, slotE)

    private val shuffledSlotItemList = SlotItemContainer.list.shuffled()

    val intersectionList: List<Intersection>? by lazy { generateIntersectionList() }
    val winSlotItemList : List<SlotItem>?     by lazy { generateWinSlotItemList()  }


    /** Генераторовать список пересечений:
     *  Проверяет содержатся ли в слотах матрицы, элементы определенные в winItemList или Item.WILD,
     *  если содержаться -> добавляет в список пересечений, Пересечение с индексом слота и индексом ряда на котором находиться этот победный элемент;
     *  если не содержаться -> ничего не происходит;
     *  return:
     *  если список переечений пуст -> null;
     *  если список пересечений не пуст -> етот список пересечений;
     * */
    private fun generateIntersectionList(): List<Intersection>? {
        val intersectionList = mutableListOf<Intersection>().apply {
            if (winItemList != null) {
                val tmpWinItemList = winItemList + Item.WILD

                slotList.onEachIndexed { slotIndex, slot -> slot.onEachIndexed { itemIndex, item ->
                    if (tmpWinItemList.contains(item)) add(Intersection(
                        slotIndex = slotIndex,
                        rowIndex  = itemIndex
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
     *  1. Получаем список элементов со списка слотов матрицы по идексам со списка Пересечений;
     *  2. Получаем элементы слотов с shuffledSlotItemList по индексам с полученых элементов в первом шаге,
     *     если элемент == WILD получаем WILD.
     * */
    private fun generateWinSlotItemList(): List<SlotItem>? {
        return if (intersectionList == null) null
        else {
            val itemList = mutableListOf<Item>()
            var slotItem: SlotItem

            mutableListOf<SlotItem>().apply {
                intersectionList!!.onEach { itemList.add(slotList[it.slotIndex][it.rowIndex]) }
                itemList.onEach { item ->
                    slotItem = when (item) {
                        Item.WILD -> SlotItemContainer.wild
                        else      -> shuffledSlotItemList[item.index]
                    }
                    add(slotItem)
                }
            }
        }
    }


    /** Генерировать слот:
     *  return: Список элементов слотов;
     *
     *  Список элементов слотов генерируеться с shuffledSlotItemList, элементы с которого получаем благодаря индексам с елементов слотов матрицы.
     *  Если елемент == WILD получаем WILD;
     *  В ином случае получаем элемент слота с shuffledSlotItemList по индексу с элемента слота матрицы.
     * */
    fun generateSlot(slotIndex: Int): List<SlotItem> = mutableListOf<SlotItem>().apply {
        var slotItem: SlotItem
        slotList[slotIndex].onEach { item ->
            slotItem = when (item) {
                Item.WILD -> SlotItemContainer.wild
                else      -> shuffledSlotItemList[item.index]
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
        WILD(SlotItemContainer.SLOT_ITEM_WILD_ID),

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
