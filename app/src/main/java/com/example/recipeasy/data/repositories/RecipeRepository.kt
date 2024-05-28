package com.example.recipeasy.data.repositories

import com.example.recipeasy.data.dataclasses.MealDetails
import com.example.recipeasy.data.dataclasses.RecipeArticle
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {

    fun getAllRecipes(): Flow<List<MealDetails>>
}