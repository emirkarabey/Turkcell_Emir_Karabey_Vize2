package com.emirk.emirkarabeyvize2.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.emirk.emirkarabeyvize2.adapter.ProductAdapter
import com.emirk.emirkarabeyvize2.configs.ApiClient
import com.emirk.emirkarabeyvize2.databinding.FragmentHomeBinding
import com.emirk.emirkarabeyvize2.model.CartRequest
import com.emirk.emirkarabeyvize2.model.Product
import com.emirk.emirkarabeyvize2.model.ProductRequest
import com.emirk.emirkarabeyvize2.model.Products
import com.emirk.emirkarabeyvize2.service.DummyJsonService
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dummyService: DummyJsonService =
            ApiClient.getClient().create(DummyJsonService::class.java)

        dummyService.getProducts().enqueue(object : Callback<Products> {
            override fun onResponse(call: Call<Products>, response: Response<Products>) {
                if (response.isSuccessful) {
                    val adapter = response.body()?.let {
                        ProductAdapter(it, onItemClick = { product ->
                            findNavController().navigate(
                                HomeFragmentDirections.actionNavigationHomeToDetailFragment(
                                    product.id
                                )
                            )
                        },
                            onButtonClickListener = { product ->
                                val request = CartRequest(
                                    userId = 1,
                                    products = listOf(
                                        ProductRequest(id = 1, quantity = 1),
                                        ProductRequest(id = 50, quantity = 2)
                                    )
                                )

                                try {
                                    lifecycleScope.launch {
                                        val response = dummyService.addToCart(request)
                                        // Yanıtı kullanarak yapmak istediğiniz işlemleri gerçekleştirin
                                        // Örneğin, dönen bir sipariş numarasını alabilir veya bir mesajı görüntüleyebilirsiniz
                                        println("İstek başarıyla tamamlandı")
                                    }
                                } catch (e: Exception) {
                                    println("İstek başarısız oldu: ${e.message}")
                                }
                            })
                    }


                    binding.listView.adapter = adapter
                } else {
                }
            }

            override fun onFailure(call: Call<Products>, t: Throwable) {
                Log.v("data", "fail")
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


