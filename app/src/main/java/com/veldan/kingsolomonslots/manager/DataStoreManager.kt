package com.veldan.kingsolomonslots.manager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.veldan.kingsolomonslots.main.game
import kotlinx.coroutines.flow.first

object DataStoreManager {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "DATA_STORE")

    private val BALANCE_KEY  = longPreferencesKey("balance_key")
    private val TUTORIAL_KEY = booleanPreferencesKey("tutorial_key")



    object Balance: DataStoreElement<Long> {
        override suspend fun collect(block: suspend (Long) -> Unit) {
            game.activity.dataStore.data.collect { block(it[BALANCE_KEY] ?: 10_000L) }
        }

        override suspend fun update(block: suspend (Long) -> Long) {
            game.activity.dataStore.edit { it[BALANCE_KEY] = block(it[BALANCE_KEY] ?: 10_000L) }
        }

        override suspend fun get(): Long {
            return game.activity.dataStore.data.first()[BALANCE_KEY] ?: 0L
        }
    }

    object Tutorial: DataStoreElement<Boolean?> {
       override suspend fun collect(block: suspend (Boolean?) -> Unit) {
            game.activity.dataStore.data.collect { block(it[TUTORIAL_KEY]) }
        }

        override suspend fun update(block: suspend (Boolean?) -> Boolean?) {
            game.activity.dataStore.edit { it[TUTORIAL_KEY] = block(it[TUTORIAL_KEY]) ?: false }
        }

        override suspend fun get(): Boolean? {
            return game.activity.dataStore.data.first()[TUTORIAL_KEY]
        }
    }



    interface DataStoreElement<T>{
        suspend fun collect(block: suspend (T) -> Unit)

        suspend fun update(block: suspend (T) -> T)

        suspend fun get(): T
    }

}

