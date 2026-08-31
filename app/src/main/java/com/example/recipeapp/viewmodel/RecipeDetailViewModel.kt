package com.example.recipeapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.model.Recipe
import com.example.recipeapp.model.Repository.RecipeRepository
import kotlinx.coroutines.launch

class RecipeDetailViewModel : ViewModel() {

    private val repository = RecipeRepository.getInstance()

    private val _recipe = MutableLiveData<Recipe?>()
    val recipe: LiveData<Recipe?> = _recipe

    private val _isFavorite = MutableLiveData(false)
    val isFavorite: LiveData<Boolean> = _isFavorite

    fun loadRecipe(idMeal: String) {
        viewModelScope.launch {
            _recipe.postValue(repository.getRecipeById(idMeal))
            _isFavorite.postValue(repository.isFavorite(idMeal))
        }
    }

    fun toggleFavorite() {
        val recipe = _recipe.value ?: return
        viewModelScope.launch {
            val isFav = _isFavorite.value == true
            if (isFav) { repository.deleteFavorite(recipe); _isFavorite.postValue(false) }
            else        { repository.insertFavorite(recipe); _isFavorite.postValue(true)  }
        }
    }
}
