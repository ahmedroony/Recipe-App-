package com.example.recipeapp.database.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavouriteDao {
    // remember , we did not store the data from API !
    // the entity is empty until we say to create
    // so , when we invoke it , it will store the favourite one
    // the displayed objects are volatile data from the api !!
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavorite(recipe: Recipe)

    @Delete
    suspend fun removeFavorite(recipe: Recipe)

    @Query("SELECT * FROM favourite_table")
    suspend fun getAllFavorites(): List<Recipe>

    @Query("SELECT EXISTS(SELECT 1 FROM favourite_table WHERE idMeal = :id)")
    suspend fun isFavorite(id: String): Boolean
}