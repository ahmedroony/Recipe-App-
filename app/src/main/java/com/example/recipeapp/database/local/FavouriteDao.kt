package com.example.recipeapp.database.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.recipeapp.database.model.Recipe

import androidx.room.Query

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavorite(recipe: FavouriteEntity)

    @Delete
    suspend fun removeFavorite(recipe: FavouriteEntity)

    // جلب كل المفضلة لعرضها في الـ Favorite Fragment
    @Query("SELECT * FROM fav_table")
    suspend fun getAllFavorites(): List<FavouriteEntity>
}