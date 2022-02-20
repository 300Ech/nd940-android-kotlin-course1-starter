package com.udacity.shoestore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.FragmentWelcomeScreenBinding

class WelcomeScreenFragment : Fragment() {

    private lateinit var binding: FragmentWelcomeScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWelcomeScreenBinding.bind(
            inflater.inflate(
                R.layout.fragment_welcome_screen,
                container,
                false
            )
        )

        setupListeners()

        return binding.root
    }

    private fun setupListeners() {
        binding.next.setOnClickListener {
            findNavController().navigate(R.id.action_welcomeScreenFragment_to_instructionFragment)
        }
    }
}