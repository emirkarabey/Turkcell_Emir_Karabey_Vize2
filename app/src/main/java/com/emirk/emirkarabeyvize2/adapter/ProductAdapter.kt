package com.emirk.emirkarabeyvize2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.bumptech.glide.Glide
import com.emirk.emirkarabeyvize2.databinding.ItemProductBinding
import com.emirk.emirkarabeyvize2.model.Product
import com.emirk.emirkarabeyvize2.model.Products

class ProductAdapter(
    private val products: Products,
    private val onItemClick: (Product) -> Unit,
    private val onButtonClickListener: (Product) -> Unit
) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val binding: ItemProductBinding = convertView?.let {
            ItemProductBinding.bind(it)
        } ?: ItemProductBinding.inflate(LayoutInflater.from(parent?.context), parent, false)

        val product = products.products[position]
        Glide.with(binding.imageView)
            .load(product.images[0])
            .into(binding.imageView)
        binding.titleTextView.text = product.title
        binding.descriptionTextView.text = product.price.toString()

        binding.root.setOnClickListener {
            onItemClick(product)
        }

        binding.addCartButton.setOnClickListener {
            onButtonClickListener(product)
        }

        return binding.root
    }

    override fun getItem(position: Int): Any {
        return products
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return 10
    }
}