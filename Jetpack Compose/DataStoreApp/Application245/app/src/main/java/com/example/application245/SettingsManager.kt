package com.example.application245

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore(name = "settings")

class SettingsManager(context: Context) {
    private val dataScope = context.dataStore

    private val USER_NAME_KEY = stringPreferencesKey("user_name")

    val userNameFlow: Flow<String> = dataScope.data.map { preferences ->
        preferences[USER_NAME_KEY] ?: "Guest"
    }

    suspend fun saveName(name: String) {
        dataScope.edit { preferences ->
            preferences[USER_NAME_KEY] = name
        }
    }
}