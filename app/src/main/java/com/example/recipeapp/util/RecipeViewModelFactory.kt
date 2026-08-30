package com.example.recipeapp.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.recipeapp.database.repository.RecipeRepository
import com.example.recipeapp.viewModel.FavoriteViewModel
import com.example.recipeapp.viewModel.HomeViewModel
import com.example.recipeapp.viewModel.RecipeDetailViewModel
import com.example.recipeapp.viewModel.SearchViewModel

class RecipeViewModelFactory(private val repository: RecipeRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        // a clean helper function helps in creating ViewModels with safe passage
        // for RequireContext to actually pass the context into the repository
        return when {
            modelClass.isAssignableFrom(RecipeDetailViewModel::class.java) -> {
                @Suppress("UNCHECKED_CAST")
                RecipeDetailViewModel(repository) as T
            }
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                @Suppress("UNCHECKED_CAST")
                HomeViewModel(repository) as T
            }
            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
                @Suppress("UNCHECKED_CAST")
                FavoriteViewModel(repository) as T
            }
            modelClass.isAssignableFrom(SearchViewModel::class.java) -> {
                @Suppress("UNCHECKED_CAST")
                SearchViewModel(repository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }
}