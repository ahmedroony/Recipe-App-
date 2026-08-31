package com.example.recipeapp.database.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavouriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavorite(recipe: Recipe)

    @Delete
    suspend fun removeFavorite(recipe: Recipe)

    @Query("DELETE FROM favourite_table WHERE userEmail = :userEmail AND idMeal = :idMeal")
    suspend fun removeFavoriteByKeys(userEmail: String, idMeal: String)

    @Query("SELECT * FROM favourite_table WHERE userEmail = :userEmail")
    suspend fun getAllFavorites(userEmail: String): List<Recipe>

    @Query("SELECT EXISTS(SELECT 1 FROM favourite_table WHERE userEmail = :userEmail AND idMeal = :id)")
    suspend fun isFavorite(userEmail: String, id: String): Boolean
}
