package com.example.recipeapp.Model


data class Recipe(
    val idMeal: String,
    val strMeal: String,
    val strMealThumb: String,
    val strInstructions: String,
    val strYoutube: String?,
    var isFavorite: Boolean = false
)

