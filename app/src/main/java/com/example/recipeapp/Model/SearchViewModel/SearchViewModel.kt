package com.example.recipeapp.Model.SearchViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.Model.Repository.RecipeRepository
import com.example.recipeapp.Model.RetrofitInstance
import com.example.recipeapp.Recipe.Recipe
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {

    private val repository = RecipeRepository(RetrofitInstance.api)

    private val _searchResults = MutableLiveData<List<Recipe>>()
    val searchResults: LiveData<List<Recipe>> get() = _searchResults

    fun searchRecipes(query: String) {
        viewModelScope.launch {
            val results = repository.getRecipes(query)
            _searchResults.value = results
        }
    }
}