package com.ocanha.retrofitcomkotlin.utils

object Validator {

    //Existem formas melhores de se validar um e-mail. Isso é apenas um exemplo!
    fun validateEmail(email: String): Boolean {

        return !(email.isEmpty() || email.isBlank() || !email.contains("@"))

    }

    fun validatePassword(password: String): Boolean {

        return !(password.isEmpty() || password.isBlank())

    }

    fun validateName(name: String): Boolean {

        return !(name.isEmpty() || name.isBlank())

    }

}