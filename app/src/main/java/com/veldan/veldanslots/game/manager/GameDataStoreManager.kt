package com.veldan.veldanslots.game.manager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.veldan.veldanslots.appContext
import kotlinx.coroutines.flow.first

object GameDataStoreManager {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "GAME_DATA_STORE")

    private val BALANCE_KEY  = longPreferencesKey("balance_key")
    private val TUTORIAL_KEY = booleanPreferencesKey("tutorial_key")



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
    }

    object Tutorial: DataStoreElement<Boolean?> {
       override suspend fun collect(block: suspend (Boolean?) -> Unit) {
            appContext.dataStore.data.collect { block(it[TUTORIAL_KEY]) }
        }

        override suspend fun update(block: suspend (Boolean?) -> Boolean?) {
            appContext.dataStore.edit { it[TUTORIAL_KEY] = block(it[TUTORIAL_KEY]) ?: false }
        }

        override suspend fun get(): Boolean? {
            return appContext.dataStore.data.first()[TUTORIAL_KEY]
        }
    }



    interface DataStoreElement<T>{
        suspend fun collect(block: suspend (T) -> Unit)

        suspend fun update(block: suspend (T) -> T)

        suspend fun get(): T
    }

}

