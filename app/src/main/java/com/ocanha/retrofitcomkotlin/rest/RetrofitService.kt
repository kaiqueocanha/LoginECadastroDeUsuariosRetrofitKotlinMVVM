package com.ocanha.retrofitcomkotlin.rest

import com.ocanha.retrofitcomkotlin.model.LoginRequest
import com.ocanha.retrofitcomkotlin.model.LoginResponse
import com.ocanha.retrofitcomkotlin.model.Recipe
import com.ocanha.retrofitcomkotlin.model.User
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RetrofitService {

    @GET("recipes")
    fun getAllRecipes(): Call<List<Recipe>>

    @POST("recipes")
    fun saveRecipe(@Body recipe: Recipe): Call<ResponseBody>

    @POST("register")
    fun saveUser(@Body user: User): Call<ResponseBody>

    @POST("login")
    fun login(@Body loginRequest: LoginRequest): Call<LoginResponse>

    companion object {

        private val retrofitService: RetrofitService by lazy {

            val retrofit = Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            retrofit.create(RetrofitService::class.java)

        }

        fun getInstance(): RetrofitService {
            return retrofitService
        }
    }

}