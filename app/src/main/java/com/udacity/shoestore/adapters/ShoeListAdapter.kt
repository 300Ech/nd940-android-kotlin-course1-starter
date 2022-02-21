package com.udacity.shoestore.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ShoeListItemBinding
import com.udacity.shoestore.generated.callback.OnClickListener
import com.udacity.shoestore.models.Shoe

class ShoeListAdapter(val itemClickListener: OnShoeItemClick) :
    RecyclerView.Adapter<ShoeListAdapter.ShoeItemViewHolder>() {

    interface OnShoeItemClick {
        fun onItemClickListener(shoeItem: Shoe)
    }

    var shoes = mutableListOf<Shoe>()

    @SuppressLint("NotifyDataSetChanged")
    fun setShoeList(shoes: List<Shoe>) {
        this.shoes = shoes.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoeItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.shoe_list_item, parent, false)

        return ShoeItemViewHolder(view, itemClickListener)
    }

    override fun onBindViewHolder(holder: ShoeItemViewHolder, position: Int) {
        holder.bind(shoes[position])
    }

    override fun getItemCount() = shoes.size

    class ShoeItemViewHolder(view: View, private val clickListener: OnShoeItemClick) :
        RecyclerView.ViewHolder(view) {
        fun bind(item: Shoe) {
            val binding = ShoeListItemBinding.bind(itemView)

            binding.shoeName.text = item.name
            binding.shoeCompany.text = item.company
            binding.shoeDescription.text = item.description

            if (item.images.isNotEmpty()) {
                Picasso.get().load(item.images[0]).into(binding.shoeImage)
            }

            binding.root.setOnClickListener {
                clickListener.onItemClickListener(item)
            }
        }
    }
}