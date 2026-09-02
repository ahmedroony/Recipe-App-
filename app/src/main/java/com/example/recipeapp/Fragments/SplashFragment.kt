package com.example.recipeapp.Fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.recipeapp.R
import com.example.recipeapp.databinding.FragmentSplashBinding
import com.example.recipeapp.util.SessionManager

class SplashFragment : Fragment() {

    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lottieSplash.playAnimation()

        Handler(Looper.getMainLooper()).postDelayed({
            if (isAdded) {
                val navOptions = NavOptions.Builder()
                    .setPopUpTo(R.id.splashFragment, true)
                    .build()

                try {
                    val sessionManager = SessionManager(requireContext())
                    if (sessionManager.isLoggedIn()) {
                        findNavController().navigate(R.id.homeFragment, args = null, navOptions)
                    } else {
                        findNavController().navigate(R.id.loginFragment, args = null, navOptions)
                    }
                }catch (e: Exception){
                    e.printStackTrace()
                }
            }
        }, 3000)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}