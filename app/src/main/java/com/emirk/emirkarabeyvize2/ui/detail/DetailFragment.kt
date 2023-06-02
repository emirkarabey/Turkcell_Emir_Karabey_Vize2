package com.emirk.emirkarabeyvize2.ui.detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.emirk.emirkarabeyvize2.R
import com.emirk.emirkarabeyvize2.adapter.ProductAdapter
import com.emirk.emirkarabeyvize2.configs.ApiClient
import com.emirk.emirkarabeyvize2.databinding.FragmentDetailBinding
import com.emirk.emirkarabeyvize2.databinding.FragmentHomeBinding
import com.emirk.emirkarabeyvize2.model.ProductDetail
import com.emirk.emirkarabeyvize2.model.Products
import com.emirk.emirkarabeyvize2.service.DummyJsonService
import com.emirk.emirkarabeyvize2.ui.home.HomeFragmentDirections
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dummyService: DummyJsonService =
            ApiClient.getClient().create(DummyJsonService::class.java)

        dummyService.getProductDetail().enqueue(object : Callback<ProductDetail> {

            override fun onFailure(call: Call<ProductDetail>, t: Throwable) {
                Log.v("data", "fail")
            }

            override fun onResponse(call: Call<ProductDetail>, response: Response<ProductDetail>) {
                if (response.isSuccessful) {
                    Glide.with(binding.imageView)
                        .load(response.body()!!.images[0])
                        .into(binding.imageView)
                    binding.titleTextView.text = response.body()!!.title
                    binding.descriptionTextView.text = response.body()!!.price.toString()
                    binding.categoryTextView.text = response.body()!!.category
                    binding.thumbnailTextView.text = response.body()!!.thumbnail
                } else {
                }
            }
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
