package com.example.recipeapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.database.local.Recipe
import com.example.recipeapp.database.remote.RetrofitInstance
import com.example.recipeapp.database.repository.RecipeRepository
import kotlinx.coroutines.launch

//view model preper data for ui
class HomeViewModel(private val repository: RecipeRepository) : ViewModel() {
    private val _recipes = MutableLiveData<List<Recipe>>()
    val recipes: LiveData<List<Recipe>> get() = _recipes
    fun fetchRecipes(query: String = "c") {
        viewModelScope.launch {
            val resultList = repository.getRecipe(query)
            _recipes.value = resultList
        }
    }
}