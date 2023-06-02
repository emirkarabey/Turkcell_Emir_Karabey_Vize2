package com.emirk.emirkarabeyvize2.ui.cart

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.emirk.emirkarabeyvize2.adapter.CartAdapter
import com.emirk.emirkarabeyvize2.configs.ApiClient
import com.emirk.emirkarabeyvize2.databinding.FragmentCartBinding

import com.emirk.emirkarabeyvize2.model.Cart
import com.emirk.emirkarabeyvize2.service.DummyJsonService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CartFragment : Fragment() {

    private var _binding: FragmentCartBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dummyService: DummyJsonService =
            ApiClient.getClient().create(DummyJsonService::class.java)

        dummyService.getCart().enqueue(object : Callback<Cart> {
            override fun onResponse(call: Call<Cart>, response: Response<Cart>) {
                if (response.isSuccessful) {
                    val adapter = response.body()?.let {
                        CartAdapter(it)
                    }
                    binding.listView.adapter = adapter
                } else {
                }
            }

            override fun onFailure(call: Call<Cart>, t: Throwable) {
                Log.v("data", "fail")
            }
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}