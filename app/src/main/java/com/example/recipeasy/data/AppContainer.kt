package com.example.recipeasy.data

import android.content.Context
import com.example.recipeasy.data.repositories.DummyDataRecipeRepo
import com.example.recipeasy.data.repositories.OfflinePantryRepository
import com.example.recipeasy.data.repositories.OfflineRecipeRepository
import com.example.recipeasy.data.repositories.PantryRepository
import com.example.recipeasy.data.repositories.RecipeRepository

interface AppContainer {
    val pantryRepository: PantryRepository

    val recipeRepository: RecipeRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val pantryRepository: PantryRepository by lazy {
        OfflinePantryRepository(RecipeDatabase.getDatabase(context).pantryDao())
    }
    override val recipeRepository: RecipeRepository by lazy {
        DummyDataRecipeRepo()
    }
}