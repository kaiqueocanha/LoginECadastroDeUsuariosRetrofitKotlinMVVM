package com.ocanha.retrofitcomkotlin.views

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.ocanha.retrofitcomkotlin.databinding.ActivityRegisterBinding
import com.ocanha.retrofitcomkotlin.repositories.UserRepository
import com.ocanha.retrofitcomkotlin.rest.RetrofitService
import com.ocanha.retrofitcomkotlin.viewmodel.register.RegisterViewModel
import com.ocanha.retrofitcomkotlin.viewmodel.register.RegisterViewModelFactory

class RegisterActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityRegisterBinding
    private lateinit var viewModel: RegisterViewModel
    private val retrofitService = RetrofitService.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        viewModel =
            ViewModelProvider(this, RegisterViewModelFactory(UserRepository(retrofitService))).get(
                RegisterViewModel::class.java
            )

        setupUi()

    }

    private fun setupUi() = _binding.apply {

        btnRegister.setOnClickListener {



        }

    }

}