package com.emirk.emirkarabeyvize2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.emirk.emirkarabeyvize2.databinding.ItemCartBinding
import com.emirk.emirkarabeyvize2.model.Cart

class CartAdapter(private val cart: Cart) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val binding: ItemCartBinding = convertView?.let {
            ItemCartBinding.bind(it)
        } ?: ItemCartBinding.inflate(LayoutInflater.from(parent?.context), parent, false)

        binding.titleTextView.text = cart.products[position].title
        binding.descriptionTextView.text = cart.products[position].price.toString()

        return binding.root
    }

    override fun getItem(position: Int): Any {
        return cart
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return cart.products.size
    }
}