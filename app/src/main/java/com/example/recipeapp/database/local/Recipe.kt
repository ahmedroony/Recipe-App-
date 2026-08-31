package com.example.recipeapp.database.local

import androidx.room.Entity

@Entity(
    tableName = "favourite_table",
    primaryKeys = ["userEmail", "idMeal"]
)
data class Recipe(
    val idMeal: String,
    val strMeal: String,
    val strMealThumb: String,
    val strInstructions: String,
    val strYoutube: String?,
    var isFavorite: Boolean = false,
    val userEmail: String = ""
)
