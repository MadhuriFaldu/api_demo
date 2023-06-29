package com.practicaltask.utils.local

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object PreferenceKeys {
    val USER_TOKEN = stringPreferencesKey("user_token")
    val TOKEN_ID = stringPreferencesKey("token_id")
    val IS_USER_LOGIN = booleanPreferencesKey("is_user_login")
    val LOGIN_USER = stringPreferencesKey("login_user")

}