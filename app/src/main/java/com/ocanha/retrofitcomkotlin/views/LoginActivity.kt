package com.ocanha.retrofitcomkotlin.views

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ocanha.retrofitcomkotlin.databinding.ActivityLoginBinding
import com.ocanha.retrofitcomkotlin.model.LoginRequest
import com.ocanha.retrofitcomkotlin.model.UserSession
import com.ocanha.retrofitcomkotlin.repositories.UserRepository
import com.ocanha.retrofitcomkotlin.rest.RetrofitService
import com.ocanha.retrofitcomkotlin.utils.Validator.validateEmail
import com.ocanha.retrofitcomkotlin.utils.Validator.validatePassword
import com.ocanha.retrofitcomkotlin.viewmodel.login.LoginViewModel
import com.ocanha.retrofitcomkotlin.viewmodel.login.LoginViewModelFactory

class LoginActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel
    private val retrofitService = RetrofitService.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        viewModel =
            ViewModelProvider(this, LoginViewModelFactory(UserRepository(retrofitService))).get(
                LoginViewModel::class.java
            )

        setupUi()

    }

    private fun setupUi() = _binding.apply {

        btnLogin.setOnClickListener {

            if (!validateEmail(edtEmail.text.toString())) {

                edtEmail.error = "Preencha o email corretamente"
                edtEmail.requestFocus()
                return@setOnClickListener

            }

            if (!validatePassword(edtPassword.text.toString())) {

                edtPassword.error = "Preencha a senha de acesso"
                edtPassword.requestFocus()
                return@setOnClickListener

            }

            viewModel.login(
                LoginRequest(
                    edtEmail.text.toString(),
                    edtPassword.text.toString()
                )
            )
            loadingView.show()

        }

        btnRegister.setOnClickListener {

            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))

        }

    }

    override fun onStart() {
        super.onStart()

        viewModel.success.observe(this, Observer {

            UserSession.setToken(it.token)
            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            finish()

        })

        viewModel.errorMessage.observe(this, Observer {
            _binding.loadingView.dismiss()
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })

    }
}