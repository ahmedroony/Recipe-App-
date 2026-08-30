package com.example.recipeapp.Fragments
import android.text.TextWatcher
import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeapp.R
import com.example.recipeapp.Recipe.RecipeAdapter.RecipeAdapter
import com.example.recipeapp.Model.SearchViewModel.SearchViewModel

class SearchFragment : Fragment(R.layout.fragment_search) {
private val viewModel: SearchViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val etSearch = view.findViewById<EditText>(R.id.etSearch)
        val rvSearchResults = view.findViewById<RecyclerView>(R.id.rvSearchResults)
        val adapter = RecipeAdapter(emptyList())
        {
            selectRecipe -> Toast.makeText(context, "Clicked: ${selectRecipe.strMeal}", Toast.LENGTH_SHORT).show()
        }

        rvSearchResults.layoutManager = LinearLayoutManager(requireContext())
        rvSearchResults.adapter = adapter
        viewModel.searchResults.observe(viewLifecycleOwner) { updatalist ->
            adapter.updateData(updatalist)
        }

        etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val query = s.toString().trim()
                if (query.isNotEmpty()) {
                    viewModel.searchRecipes(query)
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }
}