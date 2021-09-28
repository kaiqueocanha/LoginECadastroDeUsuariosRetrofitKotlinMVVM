package com.ocanha.retrofitcomkotlin.views

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ocanha.retrofitcomkotlin.databinding.ActivityRegisterBinding
import com.ocanha.retrofitcomkotlin.model.User
import com.ocanha.retrofitcomkotlin.repositories.UserRepository
import com.ocanha.retrofitcomkotlin.rest.RetrofitService
import com.ocanha.retrofitcomkotlin.utils.Validator.validateEmail
import com.ocanha.retrofitcomkotlin.utils.Validator.validateName
import com.ocanha.retrofitcomkotlin.utils.Validator.validatePassword
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

            if (!validateName(edtName.text.toString())) {

                edtName.error = "Preencha o nome completo"
                edtName.requestFocus()
                return@setOnClickListener

            }

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

            viewModel.register(
                User(
                    edtName.text.toString(),
                    edtEmail.text.toString(),
                    edtPassword.text.toString()
                )
            )

            loadingView.show()

        }

    }

    override fun onStart() {
        super.onStart()

        viewModel.status.observe(this, Observer {
            if (it) {
                Toast.makeText(
                    this,
                    "Usuário registrado com sucesso!",
                    Toast.LENGTH_SHORT
                ).show()
                finish()
            } else {
                Toast.makeText(
                    this,
                    "Erro ao registrar usuário. Tente novamente.",
                    Toast.LENGTH_SHORT
                ).show()
                _binding.loadingView.dismiss()
            }
        })

    }
}