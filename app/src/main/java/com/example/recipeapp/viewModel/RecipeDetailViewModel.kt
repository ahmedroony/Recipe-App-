package com.example.recipeapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.database.local.FavouriteDao
import com.example.recipeapp.database.local.Recipe
import com.example.recipeapp.database.remote.ApiService
import com.example.recipeapp.database.remote.RetrofitInstance
import com.example.recipeapp.database.repository.RecipeRepository
import kotlinx.coroutines.launch

class RecipeDetailViewModel(private val repository: RecipeRepository) : ViewModel() {
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
