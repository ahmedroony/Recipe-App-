package com.example.recipeapp.Fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.recipeapp.R
import com.example.recipeapp.util.SessionManager

class RegisterFragment : Fragment(R.layout.fragment_register) {

    private lateinit var sessionManager: SessionManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sessionManager = SessionManager(requireContext())

        val etEmail = view.findViewById<EditText>(R.id.etEmail)
        val etPassword = view.findViewById<EditText>(R.id.etPassword)
        val btnRegister = view.findViewById<Button>(R.id.btnRegister)
        val tvLogin = view.findViewById<TextView>(R.id.tvLogin)

        btnRegister?.setOnClickListener {
            val email = etEmail?.text?.toString()?.trim() ?: ""
            val password = etPassword?.text?.toString()?.trim() ?: ""

            if (email.isNotEmpty() && password.isNotEmpty()) {
                sessionManager.registerUser(email, password)
                Toast.makeText(requireContext(), "Account Created! Please Login", Toast.LENGTH_SHORT).show()

                findNavController().navigateUp()
            } else {
                Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }

        tvLogin?.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}
