package com.udacity.shoestore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputLayout

import com.udacity.shoestore.databinding.FragmentShoeDetailsBinding
import com.udacity.shoestore.viewmodels.MainViewModel

class ShoeDetailsFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var binding: FragmentShoeDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val args = ShoeDetailsFragmentArgs.fromBundle(requireArguments())
        viewModel.edit(args.shoeId)

        binding = FragmentShoeDetailsBinding.bind(
            inflater.inflate(
                R.layout.fragment_shoe_details,
                container,
                false
            )
        )
        binding.lifecycleOwner = this

        setupObservers()

        binding.mainViewModel = viewModel

        setupListeners()

        return binding.root
    }

    private fun setupObservers() {
        viewModel.shoeSaved.observe(viewLifecycleOwner) { isSaved ->
            if (isSaved) findNavController()
                .navigate(ShoeDetailsFragmentDirections.actionShoeDetailsFragmentToShoeListFragment())
        }

        viewModel.errorList.observe(viewLifecycleOwner) { validationErrors ->
            validationErrors.forEach { error ->
                val inputLayout = binding.container.findViewWithTag<TextInputLayout>(error.tag)
                inputLayout?.error = error.errorMessage
            }
        }
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
        findNavController()
            .navigate(ShoeDetailsFragmentDirections.actionShoeDetailsFragmentToShoeListFragment())
    }
}