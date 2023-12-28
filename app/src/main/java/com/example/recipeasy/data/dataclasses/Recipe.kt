package com.example.recipeasy.data.dataclasses

data class Recipe(
    val recipeTitle : String,
    val time: Int,
    val difficulty: String,
    val servings: Int,
    val ingredients: List<Ingredient>,
    val steps: List<PrepStep>,

)
