package com.example.recipeapp.util

import android.content.Context
import android.content.SharedPreferences

class SessionManager(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("user_session", Context.MODE_PRIVATE)

    fun registerUser(email: String, pass: String) {
        val editor = prefs.edit()
        editor.putString("REGISTERED_EMAIL", email)
        editor.putString("REGISTERED_PASS", pass)
        editor.apply()
    }

    fun isValidUser(email: String, pass: String): Boolean {
        val savedEmail = prefs.getString("REGISTERED_EMAIL", "")
        val savedPass = prefs.getString("REGISTERED_PASS", "")
        return email == savedEmail && pass == savedPass && email.isNotEmpty()
    }

    fun saveSession(email: String) {
        val editor = prefs.edit()
        editor.putString("USER_EMAIL", email)
        editor.putBoolean("IS_LOGGED_IN", true)
        editor.apply()
    }

    fun isLoggedIn(): Boolean {
        return prefs.getBoolean("IS_LOGGED_IN", false)
    }

    fun logout() {
        val editor = prefs.edit()
        editor.clear()
        editor.apply()
    }
}