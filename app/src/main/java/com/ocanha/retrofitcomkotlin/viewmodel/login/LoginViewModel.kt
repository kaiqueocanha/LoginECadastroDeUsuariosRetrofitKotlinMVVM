package com.ocanha.retrofitcomkotlin.viewmodel.login

import androidx.lifecycle.ViewModel
import com.ocanha.retrofitcomkotlin.repositories.UserRepository

class LoginViewModel constructor(private val repository: UserRepository) : ViewModel()