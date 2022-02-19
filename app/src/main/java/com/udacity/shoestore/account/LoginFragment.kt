package com.udacity.shoestore.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    private val viewModel = LoginViewModel()
    private lateinit var binding: FragmentLoginBinding
    private var username = ""
    private var password = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            FragmentLoginBinding.bind(inflater.inflate(R.layout.fragment_login, container, false))

        setupListeners()

        viewModel.isLoggedIn.observe(viewLifecycleOwner) { isLoggedIn ->
            if (isLoggedIn) showWelcomeScreen()
        }

        viewModel.isInputValid.observe(viewLifecycleOwner) { isValid ->
            binding.login.isEnabled = isValid
        }

        viewModel.isProcessing.observe(viewLifecycleOwner) { isProcessing ->
            binding.apply {
                loading.visibility = if (isProcessing) View.VISIBLE else View.INVISIBLE
                login.visibility = if (isProcessing) View.GONE else View.VISIBLE
            }
        }

        return binding.root
    }

    private fun setupListeners() {
        binding.apply {
            username.addTextChangedListener { validateInput() }
            password.addTextChangedListener { validateInput() }
            login.setOnClickListener { viewModel.doLoging() }
        }
    }

    private fun validateInput() {
        username = binding.username.text.toString()
        password = binding.password.text.toString()

        viewModel.validateInput(username, password)
    }

    private fun showWelcomeScreen() {
        findNavController().navigate(R.id.welcomeScreenFragment)
    }
}