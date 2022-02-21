package com.udacity.shoestore

import android.os.Bundle
import android.view.View
import android.view.Menu
import android.view.MenuItem
import android.view.MenuInflater
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.squareup.picasso.Picasso
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.viewmodels.MainViewModel

interface OnShoeItemClick {
    fun onShoeItemClickListener(shoeItem: Shoe)
}

class ShoeListFragment : Fragment(), OnShoeItemClick {

    private lateinit var onShoeItemClick: OnShoeItemClick
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

        setupListeners()

        setupObservers()

        onShoeItemClick = this

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
            buildShoeList(it)
        }
    }

    private fun buildShoeList(shoeList: MutableList<Shoe>) {
        binding.container.removeAllViews()

        shoeList.forEach { shoe ->
            val newItemLayout = layoutInflater.inflate(R.layout.shoe_list_item, binding.container, false)
            val textViewCompany = newItemLayout.findViewById<TextView>(R.id.shoeCompany)
            val textViewName = newItemLayout.findViewById<TextView>(R.id.shoeName)
            val textViewDescription = newItemLayout.findViewById<TextView>(R.id.shoeDescription)
            val shoeImage = newItemLayout.findViewById<ImageView>(R.id.shoeImage)

            textViewCompany.text = shoe.company
            textViewName.text = shoe.name
            textViewDescription.text = shoe.description

            if (shoe.images.isNotEmpty()) Picasso.get().load(shoe.images[0]).into(shoeImage)

            newItemLayout.setOnClickListener { onShoeItemClick.onShoeItemClickListener(shoe) }

            binding.container.addView(newItemLayout)
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

    override fun onShoeItemClickListener(shoeItem: Shoe) {
        findNavController().navigate(
            ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailsFragment(shoeItem.id)
        )
    }
}