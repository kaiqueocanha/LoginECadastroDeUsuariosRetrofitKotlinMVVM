package com.ocanha.retrofitcomkotlin.model

object UserSession {

    private var _token: String? = null

    fun isUserLogged(): Boolean {
        return _token != null
    }

    fun setToken(token: String) {
        _token = token
    }

    fun cleanSession() {
        _token = null
    }

    fun getToken(): String = if (_token != null)
        _token!!
    else
        ""

}