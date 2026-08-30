package com.example.recipeapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recipeapp.R
import com.example.recipeapp.database.local.Recipe
import com.example.recipeapp.databinding.ItemRecipeBinding

class RecipeAdapter(
    private var recipeList: List<Recipe>,
    private val onItemClick: (Recipe) -> Unit
) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    inner class RecipeViewHolder(private val b: ItemRecipeBinding) :
        RecyclerView.ViewHolder(b.root) {

        fun bind(recipe: Recipe) {
            b.textView.text = recipe.strMeal
            Glide.with(b.root.context)
                .load(recipe.strMealThumb)
                .placeholder(android.R.color.darker_gray)
                .centerCrop()
                .into(b.imageView)
            b.root.setOnClickListener { onItemClick(recipe) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        RecipeViewHolder(
            ItemRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) =
        holder.bind(recipeList[position])

    override fun getItemCount(): Int = recipeList.size

    fun updateData(newList: List<Recipe>) {
        recipeList = newList
        notifyDataSetChanged()
    }
}
