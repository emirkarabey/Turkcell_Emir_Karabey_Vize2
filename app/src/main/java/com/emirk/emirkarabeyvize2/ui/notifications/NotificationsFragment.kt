package com.emirk.emirkarabeyvize2.ui.notifications

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.emirk.emirkarabeyvize2.configs.ApiClient
import com.emirk.emirkarabeyvize2.databinding.FragmentNotificationsBinding
import com.emirk.emirkarabeyvize2.model.JWTUser
import com.emirk.emirkarabeyvize2.service.DummyJsonService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dummyService: DummyJsonService =
            ApiClient.getClient().create(DummyJsonService::class.java)

        binding.loginButton.setOnClickListener {
            val email = binding.editTextTextEmailAddress.text.toString()
            val pass = binding.editTextTextPassword.text.toString()
            val jwtUser = JWTUser(email, pass)
            dummyService.login(jwtUser).enqueue(object : Callback<JWTUser> {
                override fun onResponse(call: Call<JWTUser>, response: Response<JWTUser>) {
                    val jwtUser = response.body()
                    Log.d("status", response.code().toString())
                    if (jwtUser != null) {
                        findNavController()
                            .navigate(NotificationsFragmentDirections.actionNavigationNotificationsToNavigationHome())
                    }
                }

                override fun onFailure(call: Call<JWTUser>, t: Throwable) {
                    Log.e("login", t.toString())
                    Toast.makeText(requireContext(), "Internet or Server Fail", Toast.LENGTH_LONG)
                        .show()
                }
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}