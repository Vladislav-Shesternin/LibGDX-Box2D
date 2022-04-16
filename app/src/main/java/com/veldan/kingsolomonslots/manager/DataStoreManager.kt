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



    // ------------------------------------------------------------------------
    // Balance
    // ------------------------------------------------------------------------
    suspend fun collectBalance(block: suspend (Long) -> Unit) {
        game.activity.dataStore.data.collect { block(it[BALANCE_KEY] ?: 10_000L) }
    }

    suspend fun updateBalance(block: suspend (Long) -> Long) {
        game.activity.dataStore.edit { it[BALANCE_KEY] = block(it[BALANCE_KEY] ?: 10_000L) }
    }

    suspend fun getBalance(): Long {
        return game.activity.dataStore.data.first()[BALANCE_KEY] ?: 0L
    }

    // ------------------------------------------------------------------------
    // Tutorial
    // ------------------------------------------------------------------------
    suspend fun collectTutorial(block: suspend (Boolean?) -> Unit) {
        game.activity.dataStore.data.collect { block(it[TUTORIAL_KEY]) }
    }

    suspend fun updateTutorial(block: suspend (Boolean?) -> Boolean) {
        game.activity.dataStore.edit { it[TUTORIAL_KEY] = block(it[TUTORIAL_KEY]) }
    }

    suspend fun getTutorial(): Boolean? {
        return game.activity.dataStore.data.first()[TUTORIAL_KEY]
    }

}

