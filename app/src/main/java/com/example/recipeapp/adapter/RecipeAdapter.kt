package com.example.recipeapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeapp.R
import com.example.recipeapp.database.local.Recipe

class RecipeAdapter(
    private var recipeList: List<Recipe>
    ,private val onItemClick: (Recipe) -> Unit)
    : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {
    class RecipeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    val tvTitle: TextView = itemView.findViewById(R.id.textView)
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecipeViewHolder {
        // Inflate the item layout for the recipe item
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recipe, parent, false)
        return RecipeViewHolder(view)
    }
//link my data to the view
    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val currentRecipe = recipeList[position]
        holder.tvTitle.text = currentRecipe.strMeal
        holder.itemView.setOnClickListener {
        onItemClick(currentRecipe)
        }
    }

    override fun getItemCount(): Int = recipeList.size
        fun updateData(newList: List<Recipe>) {
            recipeList = newList
            notifyDataSetChanged()
    }
}