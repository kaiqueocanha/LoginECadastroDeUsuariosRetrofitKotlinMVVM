package com.ocanha.retrofitcomkotlin.viewmodel.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ocanha.retrofitcomkotlin.repositories.UserRepository

class RegisterViewModelFactory constructor(private val repository: UserRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            RegisterViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }

}