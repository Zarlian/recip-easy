package com.example.recipeasy.data.repositories

import com.example.recipeasy.data.DataSource
import com.example.recipeasy.data.dataclasses.RecipeArticle
import kotlinx.coroutines.flow.Flow

class DummyDataRecipeRepo : RecipeRepository {
    override fun getAllRecipes(): Flow<List<RecipeArticle>> {
        return DataSource.recipeList
    }
}