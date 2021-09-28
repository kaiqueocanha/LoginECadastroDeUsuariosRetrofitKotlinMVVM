package com.ocanha.retrofitcomkotlin.repositories

import com.ocanha.retrofitcomkotlin.model.Recipe
import com.ocanha.retrofitcomkotlin.rest.RetrofitService

class RecipeRepository constructor(private val retrofitService: RetrofitService) {

    fun saveRecipe(token: String, recipe: Recipe) = retrofitService.saveRecipe(token, recipe)

    fun getAllRecipes(token: String) = retrofitService.getAllRecipes(token)

}