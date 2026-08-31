package com.example.recipeapp.Fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.recipeapp.R
import com.example.recipeapp.util.SessionManager

class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var sessionManager: SessionManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sessionManager = SessionManager(requireContext())

        val etEmail = view.findViewById<EditText>(R.id.etEmail)
        val etPassword = view.findViewById<EditText>(R.id.etPassword)
        val btnLogin = view.findViewById<Button>(R.id.btnLogin)
        val tvRegister = view.findViewById<TextView>(R.id.tvRegister)

        btnLogin?.setOnClickListener {
            val email = etEmail?.text?.toString()?.trim() ?: ""
            val password = etPassword?.text?.toString()?.trim() ?: ""

            if (email.isNotEmpty() && password.isNotEmpty()) {
                if (sessionManager.isValidUser(email, password)) {
                    sessionManager.saveSession(email)

                    val navOptions = NavOptions.Builder()
                        .setPopUpTo(R.id.loginFragment, true)
                        .build()

                    try {
                        findNavController().navigate(R.id.action_loginFragment_to_homeFragment, null, navOptions)
                    } catch (e: Exception) {
                        findNavController().navigate(R.id.homeFragment)
                    }
                } else {
                    Toast.makeText(requireContext(), "No account found! Please Register first", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(requireContext(), "Please enter email and password", Toast.LENGTH_SHORT).show()
            }
        }

        tvRegister?.setOnClickListener {
            try {
                findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
            } catch (e: Exception) {
                findNavController().navigate(R.id.registerFragment)
            }
        }
    }
}




