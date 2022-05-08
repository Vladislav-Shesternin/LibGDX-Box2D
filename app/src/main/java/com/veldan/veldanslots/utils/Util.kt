package com.veldan.veldanslots.utils

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlin.random.Random

fun log(message: String) {
    Log.i("VLAD", message)
}

val CharSequence.int: Int get() = toString().toInt()

val Number.length: Int get() = toString().length

fun Long.transformToBalanceFormat(): String {
    val balance = toString().toMutableList()

    when {
        length == 4  -> balance.add(1, ' ')
        length == 5  -> balance.add(2, ' ')
        length == 6  -> balance.add(3, ' ')
        length == 7  -> {
            balance.add(1, ' ')
            balance.add(5, ' ')
        }
        length == 8  -> {
            balance.add(2, ' ')
            balance.add(6, ' ')
        }
        length == 9  -> {
            balance.add(3, ' ')
            balance.add(7, ' ')
        }
        length == 10 -> {
            balance.add(1, ' ')
            balance.add(5, ' ')
            balance.add(9, ' ')
        }
        length == 11 -> {
            balance.add(2, ' ')
            balance.add(6, ' ')
            balance.add(10, ' ')
        }
        length == 12 -> {
            balance.add(3, ' ')
            balance.add(7, ' ')
            balance.add(11, ' ')
        }
        else         -> toString()
    }

    return balance.joinToString("")
}

fun probability(percent: Int, block: () -> Unit = {}): Boolean {
    val randomNum = Random.nextInt(1, 100)
    return if (randomNum <= percent) {
        block()
        true
    } else false
}

fun cancelCoroutinesAll(vararg coroutine: CoroutineScope) {
    coroutine.forEach { it.cancel() }
}