package com.example.recipeasy.data.dataclasses

import androidx.compose.ui.graphics.Color

data class ShopArticle(
    val shopItems : List<ShopItem>,
    val title: String,
    val color: Color
)
