package com.example.recipeasy.data.repositories

import com.example.recipeasy.data.dataclasses.RecipeArticle
//import com.example.recipeasy.data.dataclasses.PantryDao
import kotlinx.coroutines.flow.Flow

class OfflineRecipeRepository() : RecipeRepository {


    override fun getAllRecipes(): Flow<List<RecipeArticle>> {
        throw NotImplementedError("Not yet implemented")
    }
}