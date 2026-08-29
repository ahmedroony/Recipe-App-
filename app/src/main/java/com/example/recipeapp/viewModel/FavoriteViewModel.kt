package com.example.recipeapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.database.model.Recipe
import com.example.recipeapp.database.repository.RecipeRepository
import kotlinx.coroutines.launch

class FavoriteViewModel : ViewModel() {

    private val repository = RecipeRepository.getInstance()

    val favorites = repository.getFavorites()

    fun removeFavorite(recipe: Recipe) {
        viewModelScope.launch { repository.deleteFavorite(recipe) }
    }
}