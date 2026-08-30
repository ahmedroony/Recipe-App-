package com.example.recipeapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.database.local.Recipe
import com.example.recipeapp.database.repository.RecipeRepository
import kotlinx.coroutines.launch

class SearchViewModel(private val repository: RecipeRepository) : ViewModel() {

    private val _searchResults = MutableLiveData<List<Recipe>>()
    val searchResults: LiveData<List<Recipe>> get() = _searchResults

    fun searchRecipes(query: String) {
        viewModelScope.launch {
            val results = repository.getRecipe(query)
            _searchResults.value = results
        }
    }
}
