package com.example.recipeapp.Model

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("search.php")
    suspend fun searchRecipes(@Query("s") query: String): MealResponse
}