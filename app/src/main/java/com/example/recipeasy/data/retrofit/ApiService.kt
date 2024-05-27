package com.example.recipeasy.data.retrofit

import com.example.recipeasy.data.dataclasses.Recipe
import retrofit2.http.Query

interface ApiService {
    suspend fun getRandomRecipe(): Recipe
    suspend fun getRecipeById(@Query("i") recipeId: String): Recipe
    suspend fun getRecipesByIngredient(@Query("i") ingredient: String): Recipe
}