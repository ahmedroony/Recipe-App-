package com.example.recipeapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.model.Recipe
import com.example.recipeapp.model.Repository.RecipeRepository
import kotlinx.coroutines.launch

class FavoriteViewModel : ViewModel() {

    private val repository = RecipeRepository.getInstance()

    val favorites = repository.getFavorites()

    fun removeFavorite(recipe: Recipe) {
        viewModelScope.launch { repository.deleteFavorite(recipe) }
    }
}
