package com.example.recipeapp.database

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.recipeapp.database.local.FavoriteDao
import com.example.recipeapp.database.local.FavouriteEntity
import com.example.recipeapp.database.local.UserDao
import com.example.recipeapp.database.local.UserEntity

@Database (entities = [FavouriteEntity ::class], version = 1)
abstract class RecipeDatabase: RoomDatabase() {
    abstract fun favouriteDao(): FavoriteDao

    companion object {
        @Volatile
        private var INSTANCE: RecipeDatabase? = null
        fun getDatabase(context: Context): RecipeDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RecipeDatabase::class.java,
                    "recipe_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}