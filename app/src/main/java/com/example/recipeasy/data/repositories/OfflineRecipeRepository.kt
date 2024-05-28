package com.example.recipeasy.data.repositories

import com.example.recipeasy.data.DataSource
import com.example.recipeasy.data.dataclasses.MealDetails
import kotlinx.coroutines.flow.Flow

class OfflineRecipeRepository() : RecipeRepository {


    override fun getAllRecipes(): Flow<List<MealDetails>> {
        return DataSource.recipeList
    }
}