package com.udacity.shoestore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.udacity.shoestore.adapters.ShoeListAdapter
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.viewmodels.MainViewModel

class ShoeListFragment : Fragment(), ShoeListAdapter.OnShoeItemClick {

    private val adapter = ShoeListAdapter(this)
    private lateinit var binding: FragmentShoeListBinding
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShoeListBinding.bind(
            inflater.inflate(
                R.layout.fragment_shoe_list,
                container,
                false
            )
        )

        binding.rvShoeList.adapter = adapter
        binding.rvShoeList.layoutManager = LinearLayoutManager(context)

        setupListeners()

        setupObservers()

        return binding.root
    }

    private fun setupListeners() {
        binding.apply {
            addButton.setOnClickListener { addNewShoe() }
        }
    }

    private fun setupObservers() {
        viewModel.shoeList.observe(viewLifecycleOwner) {
            adapter.setShoeList(it)
        }
    }

    private fun addNewShoe() {
        findNavController().navigate(
            ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailsFragment(0)
        )
    }

    override fun onItemClickListener(shoeItem: Shoe) {
        findNavController().navigate(
            ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailsFragment(shoeItem.id)
        )
    }
}