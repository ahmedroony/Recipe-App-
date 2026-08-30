package com.example.recipeapp.Fragments

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.recipeapp.R
import com.example.recipeapp.database.RecipeDatabase
import com.example.recipeapp.database.remote.RetrofitInstance
import com.example.recipeapp.database.repository.RecipeRepository
import com.example.recipeapp.databinding.FragmentRecipeDetailBinding
import com.example.recipeapp.util.RecipeViewModelFactory
import com.example.recipeapp.util.VideoOverlayManager
import com.example.recipeapp.viewModel.RecipeDetailViewModel

class RecipeDetailFragment : Fragment() {

    private var _binding: FragmentRecipeDetailBinding? = null
    private val binding get() = _binding!!

    private val database by lazy { RecipeDatabase.getDatabase(requireContext()) }
    private val repository by lazy {
        RecipeRepository.getInstance(
            favoriteDao = database.favouriteDao(),
            apiService = RetrofitInstance.api
        )
    }
    private val factory by lazy { RecipeViewModelFactory(repository) }

    private val viewModel: RecipeDetailViewModel by viewModels { factory }
    private var isExpanded = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecipeDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val idMeal = arguments?.getString("idMeal") ?: run {
            Toast.makeText(requireContext(), "Recipe not found", Toast.LENGTH_SHORT).show()
            return
        }

        binding.btnBack.setOnClickListener { requireActivity().onBackPressedDispatcher.onBackPressed() }

        viewModel.loadRecipe(idMeal)
        observeViewModel()
        setupClicks()
    }

    private fun observeViewModel() {
        viewModel.recipe.observe(viewLifecycleOwner) { recipe ->
            recipe ?: return@observe
            binding.tvRecipeTitle.text = recipe.strMeal
            Glide.with(this)
                .load(recipe.strMealThumb)
                .placeholder(android.R.color.darker_gray)
                .into(binding.ivRecipeImage)
            val preview = recipe.strInstructions.take(200).trimEnd() +
                    if (recipe.strInstructions.length > 200) "…" else ""
            binding.tvInstructionsPreview.text = preview
            binding.tvInstructionsFull.text = recipe.strInstructions
            binding.btnPlayVideo.visibility =
                if (!recipe.strYoutube.isNullOrBlank()) View.VISIBLE else View.GONE
        }

        viewModel.isFavorite.observe(viewLifecycleOwner) { isFav ->
            binding.fabFavorite.setImageResource(
                if (isFav) R.drawable.ic_favorite_filled else R.drawable.ic_favorite_outline
            )
            binding.fabFavorite.imageTintList = ColorStateList.valueOf(
                ContextCompat.getColor(requireContext(),
                    if (isFav) R.color.favoriteActive else R.color.favoriteInactive)
            )
        }
    }

    private fun setupClicks() {
        binding.btnExpandInstructions.setOnClickListener {
            isExpanded = !isExpanded
            binding.tvInstructionsPreview.visibility = if (isExpanded) View.GONE else View.VISIBLE
            binding.tvInstructionsFull.visibility   = if (isExpanded) View.VISIBLE else View.GONE
            binding.btnExpandInstructions.text = getString(
                if (isExpanded) R.string.collapse_recipe else R.string.show_full_recipe
            )
        }

        binding.fabFavorite.setOnClickListener {
            val wasFav = viewModel.isFavorite.value == true
            viewModel.toggleFavorite()
            Toast.makeText(requireContext(),
                if (wasFav) R.string.removed_from_favorites else R.string.added_to_favorites,
                Toast.LENGTH_SHORT).show()
        }

        binding.btnPlayVideo.setOnClickListener {
            val url = viewModel.recipe.value?.strYoutube
            if (url.isNullOrBlank()) {
                Toast.makeText(requireContext(), R.string.no_video_available, Toast.LENGTH_SHORT).show()
            } else {
                (requireActivity() as? AppCompatActivity)?.let { act ->
                    VideoOverlayManager.show(act, url)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        VideoOverlayManager.dismiss()
        _binding = null
    }
}