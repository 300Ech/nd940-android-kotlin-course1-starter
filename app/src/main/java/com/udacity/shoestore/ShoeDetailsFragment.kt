package com.udacity.shoestore

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputLayout
import com.udacity.shoestore.databinding.FragmentShoeDetailsBinding

class ShoeDetailsFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var binding: FragmentShoeDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel.edit(0)

        binding = FragmentShoeDetailsBinding.bind(
            inflater.inflate(
                R.layout.fragment_shoe_details,
                container,
                false
            )
        )
        binding.lifecycleOwner = this

        viewModel.shoeSaved.observe(viewLifecycleOwner) { isSaved ->
            if (isSaved) findNavController().navigate(R.id.action_shoeDetailsFragment_to_shoeListFragment)
        }

        viewModel.errorList.observe(viewLifecycleOwner) { validationErrors ->
            validationErrors.forEach { error ->
                val inputLayout = binding.container.findViewWithTag<TextInputLayout>(error.tag)
                inputLayout?.error = error.errorMessage
            }
        }

        binding.mainViewModel = viewModel

        setupListeners()

        return binding.root
    }

    private fun setupListeners() {
        binding.apply {
            cancelButton.setOnClickListener { returnToShoeList() }

            shoeName.addTextChangedListener {
                shoeNameInputLayout.error = ""
                viewModel.validateInput()
            }

            description.addTextChangedListener {
                descriptionInputLayout.error = ""
                viewModel.validateInput()
            }

            shoeSize.addTextChangedListener {
                shoeSizeInputLayout.error = ""
                viewModel.validateInput()
            }

            company.addTextChangedListener {
                companyInputLayout.error = ""
                viewModel.validateInput()
            }
        }
    }

    private fun returnToShoeList() {
        findNavController().navigate(R.id.action_shoeDetailsFragment_to_shoeListFragment)
    }
}