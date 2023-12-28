package com.example.recipeasy.data.dataclasses

import androidx.compose.ui.graphics.Color

data class RecipeArticle(
    val title: String,
    val image: Int,
    val color: Color = Color(0xFFEECED3)
)
