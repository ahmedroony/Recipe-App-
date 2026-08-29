package com.example.recipeapp.adapter

import android.R
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recipeapp.database.local.Recipe
import com.example.recipeapp.databinding.ItemFavoriteBinding

class FavouriteAdapter(
    private val onItemClick: (Recipe) -> Unit,
    private val onDeleteClick: (Recipe) -> Unit
) : ListAdapter<Recipe, FavouriteAdapter.ViewHolder>(DIFF) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(ItemFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position))

    inner class ViewHolder(private val b: ItemFavoriteBinding) :
        RecyclerView.ViewHolder(b.root) {
        fun bind(recipe: Recipe) {
            b.tvRecipeName.text = recipe.strMeal
            Glide.with(b.root.context)
                .load(recipe.strMealThumb)
                .placeholder(R.color.darker_gray)
                .centerCrop()
                .into(b.ivRecipeThumbnail)
            b.root.setOnClickListener { onItemClick(recipe) }
            b.btnDelete.setOnClickListener { onDeleteClick(recipe) }
        }
    }

    companion object {
        private val DIFF = object : DiffUtil.ItemCallback<Recipe>() {
            override fun areItemsTheSame(a: Recipe, b: Recipe) = a.idMeal == b.idMeal
            override fun areContentsTheSame(a: Recipe, b: Recipe) = a == b
        }
    }
}