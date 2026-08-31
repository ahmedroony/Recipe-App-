package com.example.recipeapp.util

import android.content.Context
import android.content.Intent
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.example.recipeapp.AuthActivity
import com.example.recipeapp.MainActivity
import com.example.recipeapp.R

/**
 * Handles the Options Menu for RecipeActivity (Person 3).
 *
 * PERSON 3 — Add to your Activity:
 *   override fun onCreateOptionsMenu(menu: Menu) = MenuHandler.inflate(this, menu, menuInflater)
 *   override fun onOptionsItemSelected(item: MenuItem) =
 *       MenuHandler.handle(this, item) || super.onOptionsItemSelected(item)
 *
 *   NavHostFragment in your layout must have: android:id="@+id/navHostFragmentRecipe"
 *
 * PERSON 2 — When AuthActivity exists, replace MainActivity in signOut() with AuthActivity.
 */
object MenuHandler {

    // Must match Person 1's SessionManager keys exactly
    private const val prefs = "user_session"
    private const val key = "IS_LOGGED_IN"

    fun inflate(activity: AppCompatActivity, menu: Menu, inflater: MenuInflater): Boolean {
        inflater.inflate(R.menu.menu_options, menu)
        return true
    }

    fun handle(activity: AppCompatActivity, item: MenuItem): Boolean = when (item.itemId) {
        R.id.action_about -> { navigateToAbout(activity); true }
        R.id.action_signout -> { signOut(activity); true }
        else -> false
    }

    private fun navigateToAbout(activity: AppCompatActivity) {
        try {
            activity.findNavController(R.id.navHostFragmentRecipe).navigate(R.id.aboutFragment)
        } catch (e: Exception) {
            // Person 3: ensure android:id="@+id/navHostFragmentRecipe" in your layout
        }
    }

    private fun signOut(activity: AppCompatActivity) {
        activity.getSharedPreferences(prefs, Context.MODE_PRIVATE)
            .edit().putBoolean(key, false).apply()
        // TODO PERSON 2: Replace MainActivity::class.java with AuthActivity::class.java
        val intent = Intent(activity, AuthActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        activity.startActivity(intent)
        activity.finish()
    }
}
