package com.example.recipeapp.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.recipeapp.database.repository.RecipeRepository
import com.example.recipeapp.database.repository.UserRepository
import com.example.recipeapp.viewModel.FavoriteViewModel
import com.example.recipeapp.viewModel.HomeViewModel
import com.example.recipeapp.viewModel.RecipeDetailViewModel

class UserViewModelFactory(private val repository: UserRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        // a clean helper function helps in creating ViewModels with safe passage
        // for RequireContext to actually pass the context into the repository
        // whenever you need a ViewModel that use UserRepo , apply it here first
        // after applying the class of ViewModel then include it in the fragment
        return when {
            /*
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
            */

            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }
}