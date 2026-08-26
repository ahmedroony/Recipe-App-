package com.example.recipeapp.Recipe

data class Recipe(
    val idMeal: String,
    val strMeal: String,
    val strMealThumb: String,
    val strInstruction: String,
    val strYoutube: String,
    var isFavorite: Boolean = false
)
