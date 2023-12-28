package com.example.recipeasy.data

import androidx.compose.ui.graphics.Color
import com.example.recipeasy.R
import com.example.recipeasy.data.dataclasses.PantryItem
import com.example.recipeasy.data.dataclasses.RecipeArticle

val recipeArticlesList = listOf(
    RecipeArticle(
        title = "Chicken\n" +
                "with roasted\n" +
                "vegetables",
        image = R.drawable.plate_1,
        color = Color(0xFFEECED3)
    ),
    RecipeArticle(
        title = "Spaghetti\n" +
                " bolognese",
        image = R.drawable.plate_2,
        color = Color(0xFFC9E2FF)
    ),
    RecipeArticle(
        title = "Salmon\n" +
                "with steamed\n" +
                "legumes",
        image = R.drawable.plate_3,
        color = Color(0xFFF7CE94)
    ),
    RecipeArticle(
        title = "Spaghetti\n" +
                " bolognese",
        image = R.drawable.plate_2,
        color = Color(0xFFF7E7A9)
    ),
)


val pantryItems = listOf(
    PantryItem(
        name = "Chicken breast",
        quantity = 2,
        image = R.drawable.chicken_breast,
        color = Color(0xFFEECED3)
    ),
    PantryItem(
        name = "Beans",
        quantity = 3,
        image = R.drawable.beans,
        color = Color(0xFFBEDEDF)
    ),
    PantryItem(
        name = "Eggs",
        quantity = 7,
        image = R.drawable.eggs,
        color = Color(0xFFF7CE94)
    ),
    PantryItem(
        name = "Honey",
        quantity = 1,
        image = R.drawable.honey,
        color = Color(0xFFF7E7A9)
    ),
    PantryItem(
        name = "Onions",
        quantity = 4,
        image = R.drawable.onions,
        color = Color(0xFFBFACF4)
    ),
    PantryItem(
        name = "Tagliatelle",
        quantity = 2,
        image = R.drawable.tagliatelle,
        color = Color(0xFFA5D8D3)
    ),
)

