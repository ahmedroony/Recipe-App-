package com.example.recipeapp.database.remote

import com.example.recipeapp.database.model.MealResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("search.php")
    suspend fun searchRecipes(@Query("s") searchQuery: String): Response<MealResponse>

    @GET("lookup.php")
    suspend fun getRecipeByID(@Query("i") mealId: String): Response<MealResponse>

    @GET("filter.php")
    suspend fun getRecipeByCategory(@Query("c") category: String): Response<MealResponse>
}