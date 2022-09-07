package com.veldan.gamebox2d.game.manager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

object GameDataStoreManager {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "GAME_DATA_STORE")

    /*private val BALANCE_KEY  = longPreferencesKey("balance_key")



    object Balance: DataStoreElement<Long> {
        override suspend fun collect(block: suspend (Long) -> Unit) {
            appContext.dataStore.data.collect { block(it[BALANCE_KEY] ?: 1_000L) }
        }

        override suspend fun update(block: suspend (Long) -> Long) {
            appContext.dataStore.edit { it[BALANCE_KEY] = block(it[BALANCE_KEY] ?: 1_000L) }
        }

        override suspend fun get(): Long {
            return appContext.dataStore.data.first()[BALANCE_KEY] ?: 0L
        }
    }*/



    interface DataStoreElement<T>{
        suspend fun collect(block: suspend (T) -> Unit)

        suspend fun update(block: suspend (T) -> T)

        suspend fun get(): T
    }

}

