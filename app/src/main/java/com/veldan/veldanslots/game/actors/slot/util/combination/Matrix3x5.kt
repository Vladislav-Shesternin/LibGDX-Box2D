package com.veldan.veldanslots.game.actors.slot.util.combination

import com.veldan.veldanslots.game.actors.slot.util.SlotItem
import com.veldan.veldanslots.game.actors.slot.util.SlotItemContainer

/**
 * Матрица 3 на 5 - содержит 5 слотов (a,b,c,d,e) по 3 элемента (0,1,2).
 * winItemList - содержит список победных Item.
 * WILD не нужно добавлять к winItemList, если он есть в слотах он будет учтен.
 * */
class Matrix3x5(
    private val winItemList: List<Item>? = null,
    val scheme: String = "",

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

    private var shuffledSlotItemList = SlotItemContainer.list.shuffled()

    var intersectionList: List<Intersection>? = null
        private set
    var winSlotItemList: List<SlotItem>?      = null
        private set



    /**
     * Заполняет: [intersectionList, winSlotItemList] если [winItemList != null].
     * */
    private fun generateAndSetData() {
        val tmpIntersectionList = mutableListOf<Intersection>()
        val tmpWinSlotItemList  = mutableListOf<SlotItem>()

        winItemList?.let { winList ->

            val tmpWinItemList = winList + Item.WILD

            slotList.onEachIndexed { slotIndex, slot ->
                slot.onEachIndexed { itemIndex, item ->

                    if (tmpWinItemList.contains(item)) {
                        tmpIntersectionList.add(Intersection(
                            slotIndex = slotIndex,
                            rowIndex  = itemIndex,
                        ))
                    }

                    when {
                        winList.contains(item) -> tmpWinSlotItemList.add(shuffledSlotItemList[item.index])
                        item == Item.WILD      -> tmpWinSlotItemList.add(SlotItemContainer.wild)
                    }
                }
            }
        }

        intersectionList = if (tmpIntersectionList.isNotEmpty()) tmpIntersectionList else null
        winSlotItemList  = if (tmpWinSlotItemList.isNotEmpty()) tmpWinSlotItemList else null

    }



    fun init(): Matrix3x5 {
        shuffledSlotItemList = SlotItemContainer.list.shuffled()
        generateAndSetData()
        return this
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
