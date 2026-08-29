package com.example.recipeapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.database.local.Recipe
import com.example.recipeapp.database.repository.RecipeRepository
import kotlinx.coroutines.launch

class FavoriteViewModel(private val repository: RecipeRepository) : ViewModel() {
    val favorites = repository.getFavorites()

    init {
        viewModelScope.launch { repository.refreshFavoritesFromDb() }
    }

    fun removeFavorite(recipe: Recipe) {
        viewModelScope.launch { repository.deleteFavorite(recipe) }
    }
}