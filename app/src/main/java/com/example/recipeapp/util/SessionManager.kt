package com.example.recipeapp.util

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class SessionManager(context: Context) {

    private val prefs: SharedPreferences = context.getSharedPreferences(
        "user_session",
        Context.MODE_PRIVATE
    )

    fun registerUser(email: String, pass: String) {
        prefs.edit {
            putString("USER_PASS_$email", pass)
            putString("REGISTERED_EMAIL", email)
            apply()
        }
    }

    fun isValidUser(email: String, pass: String): Boolean {
        val storedPass = prefs.getString("USER_PASS_$email", null)
        return storedPass != null && storedPass == pass
    }

    fun saveSession(email: String) {
        saveLoginSession(email, true)
    }

    fun saveLoginSession(email: String, isLoggedIn: Boolean) {
        prefs.edit {
            putString("USER_EMAIL", email)
            putBoolean("IS_LOGGED_IN", isLoggedIn)
            apply()
        }
    }

    fun isLoggedIn(): Boolean {
        return prefs.getBoolean("IS_LOGGED_IN", false)
    }

    fun getUserEmail(): String? {
        return prefs.getString("USER_EMAIL", null)
    }

    fun logout() {
        prefs.edit {
            putBoolean("IS_LOGGED_IN", false)
            remove("USER_EMAIL")
            apply()
        }
    }
}
