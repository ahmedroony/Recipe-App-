package com.example.recipeapp.Model.Repository

import com.example.recipeapp.Model.ApiService
import com.example.recipeapp.Recipe.Recipe

class RecipeRepository(private val apiService: ApiService) {
    suspend fun getRecipes(query: String ="a"):List<Recipe>{
        val response = apiService.searchRecipes(query)
        return response.meals ?: emptyList()
    }
}