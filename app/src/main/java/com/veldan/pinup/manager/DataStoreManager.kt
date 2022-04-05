package com.veldan.pinup.manager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.veldan.pinup.main.game
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.flow.take

object DataStoreManager {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "DATA_STORE")

    private val BALANCE_KEY = longPreferencesKey("balance_key")



    suspend fun collectBalance(block: suspend (Long) -> Unit) {
        game.activity.dataStore.data.collect { block(it[BALANCE_KEY] ?: 10_000L) }
    }

    suspend fun updateBalance(block: suspend (Long) -> Long) {
        game.activity.dataStore.edit { it[BALANCE_KEY] = block(it[BALANCE_KEY] ?: 10_000L) }
    }

    suspend fun getBalance() = CompletableDeferred<Long>().also { continuation ->
        game.activity.dataStore.data.take(1).collect { continuation.complete(it[BALANCE_KEY] ?: 0L) }
    }.await()

}

