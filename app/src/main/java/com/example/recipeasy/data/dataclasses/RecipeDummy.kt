package com.example.recipeasy.data.dataclasses

data class RecipeDummy(
    val recipeTitle : String,
    val time: Int,
    val difficulty: String,
    val servings: Int,
    val ingredients: List<IngredientDummy>,
    val preparation: String,

    )
