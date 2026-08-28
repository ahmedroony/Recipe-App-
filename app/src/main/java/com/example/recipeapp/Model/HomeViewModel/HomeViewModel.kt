package com.example.recipeapp.Model.HomeViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.Model.ApiService
import com.example.recipeapp.Model.Repository.RecipeRepository
import com.example.recipeapp.Model.RetrofitInstance
import com.example.recipeapp.Recipe.Recipe
import kotlinx.coroutines.launch

//view model preper data for ui
class HomeViewModel() : ViewModel() {
    private val repository = RecipeRepository(RetrofitInstance.api)
    private val _recipes = MutableLiveData<List<Recipe>>()
    val recipes: LiveData<List<Recipe>> get() = _recipes
    fun fetchRecipes(query: String = "c") {
        viewModelScope.launch {
            val resultList = repository.getRecipes(query)
            _recipes.value = resultList
        }
    }
}