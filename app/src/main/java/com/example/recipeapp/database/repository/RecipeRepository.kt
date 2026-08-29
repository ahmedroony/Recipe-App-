package com.example.recipeapp.database.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.recipeapp.database.local.Recipe
import com.example.recipeapp.database.remote.ApiService
import com.example.recipeapp.database.local.FavouriteDao

class RecipeRepository private constructor(
    private val favoriteDao: FavouriteDao,
    private val apiService: ApiService
) {
    // the favourite part
    // preserving the LiveData
    private val _favorites = MutableLiveData<List<Recipe>>()

    // helper function to get data from Room & Update
    // simply A cross between the private & public val
    suspend fun refreshFavoritesFromDb() {
        val favoriteList = favoriteDao.getAllFavorites()
        _favorites.postValue(favoriteList)
    }

    fun getFavorites(): LiveData<List<Recipe>> = _favorites

    suspend fun insertFavorite(recipe: Recipe) {
        // Facade functions for ease of use
        favoriteDao.addFavorite(recipe.copy(isFavorite = true))
        // refresh the dp
        refreshFavoritesFromDb()
    }

    suspend fun deleteFavorite(recipe: Recipe) {
        favoriteDao.removeFavorite(recipe)
        refreshFavoritesFromDb()
    }

    suspend fun isFavorite(idMeal: String): Boolean =
        favoriteDao.isFavorite(idMeal)

    // getting the regular data


    suspend fun getRecipeById(idMeal: String): Recipe? {
        return try {
            val response = apiService.getRecipeByID(idMeal)
            if (response.isSuccessful) {
                // get the first response of the api
                val apiRecipe = response.body()?.meals?.firstOrNull()

                // make sure the response we got is marked to be favourite
                apiRecipe?.apply {
                    isFavorite = this@RecipeRepository.isFavorite(idMeal)
                }
            } else {
                null
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    suspend fun getRecipeByCategory(category: String): List<Recipe> {
        return try {
            val response = apiService.getRecipeByCategory(category)
            if (response.isSuccessful) {
                response.body()?.meals ?: emptyList()
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }


    suspend fun getRecipe(query: String): List<Recipe> {
        return try {
            // get the regular response
            val response = apiService.searchRecipes(query)

            if (response.isSuccessful) {
                // get the list , either full or empty if no response
                response.body()?.meals ?: emptyList()
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList() // if exception , give empty
        }
    }

    // Singleton edit to properly accepts Dao & API
    companion object {
        @Volatile private var INSTANCE: RecipeRepository? = null
        // efficient for dependency injection for different Dao
        fun getInstance(favoriteDao: FavouriteDao, apiService: ApiService): RecipeRepository =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: RecipeRepository(favoriteDao, apiService).also { INSTANCE = it }
            }
    }
}