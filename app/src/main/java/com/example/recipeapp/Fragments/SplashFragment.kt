package com.example.recipeapp.Fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.airbnb.lottie.LottieAnimationView
import com.example.recipeapp.R
import com.example.recipeapp.util.SessionManager

class SplashFragment : Fragment(R.layout.fragment_splash) {

    private lateinit var sessionManager: SessionManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sessionManager = SessionManager(requireContext())

        val lottieAnimation = view.findViewById<LottieAnimationView>(R.id.lottieAnimationView)
        lottieAnimation?.playAnimation()

        Handler(Looper.getMainLooper()).postDelayed({
            if (isAdded) {
                val navOptions = NavOptions.Builder()
                    .setPopUpTo(R.id.splashFragment, true)
                    .build()

                try {
                    findNavController().navigate(R.id.action_splashFragment_to_loginFragment, null, navOptions)
                } catch (e: Exception) {
                    findNavController().navigate(R.id.loginFragment)
                }
            }
        }, 3000)
    }
}
