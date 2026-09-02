package com.example.recipeapp.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeapp.R
import com.example.recipeapp.adapter.FavouriteAdapter
import com.example.recipeapp.database.RecipeDatabase
import com.example.recipeapp.database.remote.RetrofitInstance
import com.example.recipeapp.database.repository.RecipeRepository
import com.example.recipeapp.databinding.FragmentFavoriteBinding
import com.example.recipeapp.util.RecipeViewModelFactory
import com.example.recipeapp.viewModel.FavoriteViewModel

class FavouriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: FavouriteAdapter

    // get hold of Database , with the safe passage of the context
    // using Lazy for sake of eya catching :)
    private val database by lazy { RecipeDatabase.getDatabase(requireContext()) }

    private val repository by lazy {
        RecipeRepository.getInstance(
            favoriteDao = database.favouriteDao(),
            apiService = RetrofitInstance.api
        )
    }

    private val factory by lazy { RecipeViewModelFactory(repository) }

    // more standard to kotlin , idiot-proof :D
    private val viewModel: FavoriteViewModel by lazy {
        ViewModelProvider(this, factory)[FavoriteViewModel::class.java]
    }

    // the actual code of the fragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        adapter = FavouriteAdapter(
            onItemClick = { recipe ->
                findNavController().navigate(
                    R.id.action_favorite_to_recipeDetail,
                    Bundle().apply { putString("idMeal", recipe.idMeal) }
                )
            },
            onDeleteClick = { recipe -> viewModel.removeFavorite(recipe) }
        )
        binding.rvFavorites.layoutManager = LinearLayoutManager(requireContext())
        binding.rvFavorites.adapter = adapter

        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(rv: RecyclerView, vh: RecyclerView.ViewHolder,
                                t: RecyclerView.ViewHolder) = false
            override fun onSwiped(vh: RecyclerView.ViewHolder, dir: Int) {
                adapter.currentList.getOrNull(vh.bindingAdapterPosition)
                    ?.let { viewModel.removeFavorite(it) }
            }
        }).attachToRecyclerView(binding.rvFavorites)
    }

    private fun observeViewModel() {
        viewModel.favorites.observe(viewLifecycleOwner) { list ->
            adapter.submitList(list)
            val empty = list.isNullOrEmpty()
            binding.tvEmptyState.visibility = if (empty) View.VISIBLE else View.GONE
            binding.rvFavorites.visibility  = if (empty) View.GONE  else View.VISIBLE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
