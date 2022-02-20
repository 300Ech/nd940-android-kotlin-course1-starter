package com.udacity.shoestore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.FragmentInstructionBinding

class InstructionFragment : Fragment() {

    private lateinit var binding: FragmentInstructionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInstructionBinding.bind(
            inflater.inflate(
                R.layout.fragment_instruction,
                container,
                false
            )
        )

        setupListeners()

        return binding.root
    }

    private fun setupListeners() {
        binding.ShowShoes.setOnClickListener {
            findNavController().navigate(R.id.shoeListFragment)
        }
    }
}