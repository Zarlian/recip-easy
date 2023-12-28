package com.example.recipeasy.data

import androidx.compose.ui.graphics.Color
import com.example.recipeasy.R
import com.example.recipeasy.data.dataclasses.PantryItem
import com.example.recipeasy.data.dataclasses.RecipeArticle
import com.example.recipeasy.data.dataclasses.ShopArticle
import com.example.recipeasy.data.dataclasses.ShopItem

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

val pantryRecipeList = listOf(
    RecipeArticle(
        title = "Chicken\n" +
                "with roasted\n" +
                "vegetables",
        image = R.drawable.plate_1,
        color = Color(0xFFEECED3)
    )
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

val shop = listOf(
    ShopArticle(
        title = "Meat",
        shopItems = listOf(
            ShopItem(
                name = "Spare ribs",
                quantity = 4,
                recipeTitle = "Spare ribs with fried potatoes"
            ),
            ShopItem(
                name = "Chicken breast",
                quantity = 4,
                recipeTitle = "Chicken pie & french toast"
            ),
            ShopItem(
                name = "gr Grounded beef",
                quantity = 500,
                recipeTitle = "Lasagne"
            ),

        ),
        color = Color(0xFFEECED3)
    ),
    ShopArticle(
        title = "Dairy",
        shopItems = listOf(
            ShopItem(
                name = "Free Range Eggs",
                quantity = 6,
                recipeTitle = "French toast with scrambled eggs"
            ),
            ShopItem(
                name = "l Reduced fat Milk",
                quantity = 2,
                recipeTitle = "French toast & lasagna"
            ),
            ShopItem(
                name = "gr Cheddar",
                quantity = 500,
                recipeTitle = "Cottage Pie"
            ),

            ),
        color = Color(0xFFF7CE94)
    ),
    ShopArticle(
        title = "Vegetables",
        shopItems = listOf(
            ShopItem(
                name = "Leeks",
                quantity = 2,
                recipeTitle = "Chicken Pie"
            ),
            ShopItem(
                name = "Tomatoes",
                quantity = 5,
                recipeTitle = "Lasagna"
            ),
            ShopItem(
                name = "Bell Peppers",
                quantity = 2,
                recipeTitle = "Lasagna"
            ),

            ),
        color = Color(0xFFBEDEDF)
    )
)

