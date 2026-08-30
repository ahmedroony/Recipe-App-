package com.example.recipeapp.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeapp.Model.HomeViewModel.HomeViewModel
import com.example.recipeapp.R
import com.example.recipeapp.Recipe.RecipeAdapter.RecipeAdapter


class HomeFragment : Fragment(R.layout.fragment_home) {
    private val viewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rvRecipes = view.findViewById<RecyclerView>(R.id.rvRecipes)

        rvRecipes.layoutManager = LinearLayoutManager(requireContext())
       val adapter = RecipeAdapter(emptyList()){ selectedRecipe ->
           val args = Bundle().apply {
               putString("idMeal", selectedRecipe.idMeal)
           }
           findNavController().navigate(R.id.action_home_to_recipeDetail, args)
       }
        rvRecipes.adapter = adapter
        viewModel.recipes.observe(viewLifecycleOwner) { items ->
            adapter.updateData(items)
        }
        viewModel.fetchRecipes("c")
    }
    }