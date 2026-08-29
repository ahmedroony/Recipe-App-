package com.example.recipeapp.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.recipeapp.R
import com.example.recipeapp.database.RecipeDatabase
import com.example.recipeapp.database.remote.RetrofitInstance
import com.example.recipeapp.database.repository.RecipeRepository
import com.example.recipeapp.util.RecipeViewModelFactory
import com.example.recipeapp.viewModel.HomeViewModel
import com.example.recipeapp.viewModel.RecipeDetailViewModel


class HomeFragment : Fragment() {
    // get hold of Database , with the safe passage of the context
    // using Lazu for sake of eya catching :)
    private val database by lazy { RecipeDatabase.getDatabase(requireContext()) }

    private val repository by lazy {
        RecipeRepository.getInstance(
            favoriteDao = database.favouriteDao(),
            apiService = RetrofitInstance.api
        )
    }

    private val factory by lazy { RecipeViewModelFactory(repository) }

    // more standard to kotlin , idiot-proof :D
    private val viewModel: HomeViewModel by lazy {
        ViewModelProvider(this, factory)[HomeViewModel::class.java]
    }

    // the actual code


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }
}