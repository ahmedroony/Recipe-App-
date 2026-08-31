package com.example.recipeapp.database.local

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class SessionManager(context: Context) {

    // the Shared preference that will be used to determine the login across activities
    private val prefs: SharedPreferences = context.getSharedPreferences(
        "user_session"
        , Context.MODE_PRIVATE)

    // the Auth Person will use this to save the logic session
    fun saveLoginSession(email: String, isLoggedIn: Boolean) {
        prefs.edit().apply {
            putString("USER_EMAIL", email)
            putBoolean("IS_LOGGED_IN", isLoggedIn)
            apply() // works at background
        }
    }


    fun isLoggedIn(): Boolean {
        return prefs.getBoolean("IS_LOGGED_IN", false)
    }


    fun getUserEmail(): String? {
        return prefs.getString("USER_EMAIL", null)
    }

    // one for logout
    fun logout() {
        prefs.edit {
            clear()
            apply()
        }
    }
}