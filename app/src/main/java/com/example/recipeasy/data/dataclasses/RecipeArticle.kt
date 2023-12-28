package com.example.recipeasy.data.dataclasses

import androidx.compose.ui.graphics.Color

data class RecipeArticle(
    val title: String,
    val image: Int,
    val color: Color = Color(0xFFEECED3),
    val recipe: Recipe = Recipe(
        time = 20,
        recipeTitle = "Chicken with vegetables",
        difficulty = "Easy",
        servings = 4,
        ingredients = listOf(
            Ingredient("Chicken", 400, "gr"),
            Ingredient("Potatoes", 1, "kg"),
            Ingredient("Carrots", 1, "kg"),
            Ingredient("Onions", 1, "kg"),
            Ingredient("Garlic", 1, "kg"),
            Ingredient("Olive oil", 1, "tbps"),
            Ingredient("Salt", 1, "kg"),
            Ingredient("Pepper", 1, "kg"),
        ),
        steps = listOf(
            PrepStep("Preheat oven to 200°C."),
            PrepStep("Cut the chicken into pieces and season with salt and pepper."),
            PrepStep("Cut the potatoes, carrots, onions and garlic into pieces."),
            PrepStep("Place the chicken and vegetables in a baking dish and drizzle with olive oil."),
            PrepStep("Bake for 30 minutes."),
        )
    )
)
