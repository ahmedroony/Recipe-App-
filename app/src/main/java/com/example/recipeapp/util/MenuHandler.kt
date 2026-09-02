package com.example.recipeapp.util

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import com.example.recipeapp.R

/**
 * Handles the Options Menu for RecipeActivity.
 */
object MenuHandler {

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
            // Ensure android:id="@+id/navHostFragmentRecipe" in layout
        }
    }

    private fun signOut(activity: AppCompatActivity) {
        val sessionManager = SessionManager(activity)
        sessionManager.logout()

        try {
            val navController = activity.findNavController(R.id.navHostFragmentRecipe)
            val navOptions = NavOptions.Builder()
                .setPopUpTo(R.id.nav_graph_recipe, true)
                .build()
            navController.navigate(R.id.loginFragment, null, navOptions)
        } catch (e: Exception) {
            activity.recreate()
        }
    }
}
