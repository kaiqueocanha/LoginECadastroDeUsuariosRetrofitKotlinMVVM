package com.ocanha.retrofitcomkotlin.repositories

import com.ocanha.retrofitcomkotlin.model.User
import com.ocanha.retrofitcomkotlin.rest.RetrofitService

class UserRepository constructor(private val retrofitService: RetrofitService) {

    fun saveUser(user: User) = retrofitService.saveUser(user)

}