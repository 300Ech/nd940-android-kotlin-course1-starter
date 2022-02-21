package com.udacity.shoestore

import android.os.Bundle
import android.view.*
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
        setHasOptionsMenu(true)

        binding.rvShoeList.adapter = adapter
        binding.rvShoeList.layoutManager = LinearLayoutManager(context)

        setupListeners()

        setupObservers()

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.logoutMenuItem -> {
                logout()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
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

    private fun logout() {
        findNavController().navigate(
            ShoeListFragmentDirections.actionShoeListFragmentToLoginFragment()
        )
    }

    override fun onItemClickListener(shoeItem: Shoe) {
        findNavController().navigate(
            ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailsFragment(shoeItem.id)
        )
    }
}