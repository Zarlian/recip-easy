package com.example.recipeasy.data.retrofit

import com.example.recipeasy.data.dataclasses.Recipe
import com.example.recipeasy.network.APIservice
import retrofit2.Retrofit

class ApiServiceRepo (private val retrofit: Retrofit) : APIservice {
    private val apiService = retrofit.create(APIservice::class.java)

    override suspend fun getRandomRecipe(): Recipe {
        return apiService.getRandomRecipe()
    }

    override suspend fun getRecipeById(recipeId: String): Recipe {
        return apiService.getRecipeById(recipeId)
    }

    override suspend fun getRecipesByIngredient(ingredient: String): Recipe {
        return apiService.getRecipesByIngredient(ingredient)
    }
}