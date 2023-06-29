package com.practicaltask.utils.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.practicaltask.instanceApp
import com.practicaltask.ui.login.User
import com.practicaltask.utils.extensions.convertStringToObject
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.io.IOException

private const val VAULT_PREF = "stake_holder_prefs"

class LocalStorage(val context: Context) {

    val Context.dataStore by preferencesDataStore(name = VAULT_PREF)

    suspend inline fun <reified T> DataStore<Preferences>.getFromLocalStorage(
        PreferencesKey: Preferences.Key<T>,
        defaultValue: T,
        crossinline func: T.() -> Unit,
    ) {
        data.catch {
            if (it is IOException) {
                emit(emptyPreferences())
            } else {
                throw it
            }
        }.map {
            it[PreferencesKey]
        }.collect {
            if (it == null) {
                func.invoke(defaultValue)
            } else {
                func.invoke(it as T)
            }
        }
    }

    suspend inline fun <reified T> setPref(key: Preferences.Key<T>, value: Any) {
        instanceApp.dataStore.edit {
            it[key] = value as T
        }
    }

    suspend inline fun <reified T> getPref(
        key: Preferences.Key<T>,
        defaultValue: T,
        crossinline responseFunc: T.() -> Unit
    ) {
        instanceApp.dataStore.getFromLocalStorage(key, defaultValue) {
            responseFunc.invoke(this)
        }
    }

    suspend fun getToken() =
        instanceApp.dataStore.data.first()[PreferenceKeys.TOKEN_ID] ?: ""

    suspend fun clearDataStore() {
        instanceApp.dataStore.edit { preferences ->
            preferences.clear()
        }
    }

}