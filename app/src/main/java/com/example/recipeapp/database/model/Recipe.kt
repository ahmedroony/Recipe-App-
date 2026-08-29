package com.example.recipeapp.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
// this chaange was made to save hpurs of writing new mappers to the old task4 codes!
@Entity(tableName = "favourite_table")
data class Recipe(
    @PrimaryKey val idMeal: String,
    val strMeal: String,
    val strMealThumb: String,
    val strInstructions: String,
    val strYoutube: String?,
    var isFavorite: Boolean = false
)