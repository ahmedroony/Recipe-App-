package com.example.recipeapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import com.example.recipeapp.model.Recipe

@Dao
interface FavoriteDao {
    suspend fun insertFavorite(recipe: Recipe)
    suspend fun deleteFavorite(recipe: Recipe)
    fun getAllFavorites(): LiveData<List<Recipe>>
    suspend fun isFavorite(idMeal: String): Boolean
}
