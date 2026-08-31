package com.example.recipeapp.model.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.recipeapp.model.Recipe

/**
 * Singleton repository — currently in-memory/mock.
 * PERSON 1 integration: replace mock internals with your Room DAO.
 * ViewModels need zero changes when you do.
 */
class RecipeRepository private constructor() {

    private val _favorites = MutableLiveData<List<Recipe>>(emptyList())
    private val inMemoryFavorites = mutableListOf<Recipe>()

    companion object {
        val MOCK_RECIPES = listOf(
            Recipe("52772", "Teriyaki Chicken Casserole",
                "https://www.themealdb.com/images/media/meals/wvpsxx1468256321.jpg",
                "Preheat oven to 350°F. Spray a 9x13 pan with non-stick spray. " +
                "Place chicken thighs in a single layer. Mix soy sauce, honey, garlic, " +
                "and ginger. Pour over chicken. Bake uncovered 35-40 minutes until " +
                "cooked through and sauce is caramelized. Serve over rice.",
                "https://www.youtube.com/watch?v=ZJy1ajvMU1k"),
            Recipe("52795", "Chicken Handi",
                "https://www.themealdb.com/images/media/meals/wyxwsp1486979827.jpg",
                "Blend onion, ginger, and garlic into a fine paste. Heat oil. " +
                "Add paste and cook 8-10 minutes. Add chicken, stir to coat. " +
                "Add tomatoes, yogurt, and spices. Cover, cook on low 25 minutes.",
                "https://www.youtube.com/watch?v=RpB28wXyO-U"),
            Recipe("52882", "Three Fish Pie",
                "https://www.themealdb.com/images/media/meals/spswqs1511558697.jpg",
                "Preheat oven to 200°C. Boil potatoes until tender, mash with butter. " +
                "Poach fish in milk 5 minutes. Make white sauce. Layer fish, sauce, " +
                "and peas in dish. Top with mash. Bake 30 minutes until golden.",
                "https://www.youtube.com/watch?v=1IszT_guI08"),
            Recipe("52959", "Baingan Bharta",
                "https://www.themealdb.com/images/media/meals/urtpqw1487341253.jpg",
                "Roast eggplant over open flame until charred. Cool and peel. Mash. " +
                "Heat oil, fry onions golden. Add tomatoes, ginger, garlic, spices. " +
                "Cook 5 minutes. Add mashed eggplant. Cook 10 more minutes.",
                null)
        )

        @Volatile private var INSTANCE: RecipeRepository? = null
        fun getInstance(): RecipeRepository =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: RecipeRepository().also { INSTANCE = it }
            }
    }

    fun getFavorites(): LiveData<List<Recipe>> = _favorites

    suspend fun insertFavorite(recipe: Recipe) {
        if (inMemoryFavorites.none { it.idMeal == recipe.idMeal }) {
            inMemoryFavorites.add(recipe.copy(isFavorite = true))
            _favorites.postValue(inMemoryFavorites.toList())
        }
    }

    suspend fun deleteFavorite(recipe: Recipe) {
        inMemoryFavorites.removeAll { it.idMeal == recipe.idMeal }
        _favorites.postValue(inMemoryFavorites.toList())
    }

    suspend fun isFavorite(idMeal: String): Boolean =
        inMemoryFavorites.any { it.idMeal == idMeal }

    /** PERSON 1: Replace with Retrofit call */
    suspend fun getRecipeById(idMeal: String): Recipe? =
        MOCK_RECIPES.find { it.idMeal == idMeal }
}
