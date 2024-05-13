package com.example.recipeasy.data.dataclasses

data class Recipe(
    val meals: List<MealDetails>,
)

data class MealDetails(
    val idMeal: String,
    val strMeal: String,
    val strInstructions: String,
    val strMealThumb: String,
    val ingredients: List<Ingredient>,
    val difficulty: String,
    val servings: String,
    val time: String
)

data class Ingredient(
    val name: String,
    val measure: String
)

