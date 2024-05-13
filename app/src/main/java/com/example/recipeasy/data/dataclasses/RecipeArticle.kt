package com.example.recipeasy.data.dataclasses

import androidx.compose.ui.graphics.Color

data class RecipeArticle(
    val id: Int,
    val title: String,
    val image: Int,
    val color: Color = Color(0xFFEECED3),
    val recipe: RecipeDummy = RecipeDummy(
        time = 20,
        recipeTitle = "Chicken with vegetables",
        difficulty = "Easy",
        servings = 4,
        ingredients = listOf(
            IngredientDummy("Chicken", 400, "gr"),
            IngredientDummy("Potatoes", 1, "kg"),
            IngredientDummy("Carrots", 1, "kg"),
            IngredientDummy("Onions", 1, "kg"),
            IngredientDummy("Garlic", 1, "kg"),
            IngredientDummy("Olive oil", 1, "tbps"),
            IngredientDummy("Salt", 1, "kg"),
            IngredientDummy("Pepper", 1, "kg"),
        ),
        preparation =   "Preheat oven to 200°C. " +
                        "Cut the chicken into pieces and season with salt and pepper."+
                        "Cut the potatoes, carrots, onions and garlic into pieces." +
                        "Place the chicken and vegetables in a baking dish and drizzle with olive oil." +
                        "Bake for 30 minutes."

    )
)
