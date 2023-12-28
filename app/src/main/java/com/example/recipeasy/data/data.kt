package com.example.recipeasy.data

import androidx.compose.ui.graphics.Color
import com.example.recipeasy.R
import com.example.recipeasy.data.dataclasses.Ingredient
import com.example.recipeasy.data.dataclasses.PantryItem
import com.example.recipeasy.data.dataclasses.PrepStep
import com.example.recipeasy.data.dataclasses.Recipe
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
        color = Color(0xFFC9E2FF),
        recipe = Recipe(
            time = 20,
            recipeTitle = "Spaghetti bolognese",
            difficulty = "Easy",
            servings = 6,
            ingredients = listOf(
                Ingredient("Olive oil", 1, "tbsp"),
                Ingredient("Onion", 1, ""),
                Ingredient("Carrot", 3, ""),
                Ingredient("Celery", 1, "kg"),
                Ingredient("Garlic", 1, "clover"),
                Ingredient("Grounded beef", 800, "gr"),
                Ingredient("Tomato paste", 1, "tbsp"),
                Ingredient("Tomatoes", 500, "gr"),
                Ingredient("Oregano", 1, "tbsp"),
                Ingredient("Basil", 1, "tbsp"),
                Ingredient("Salt", 1, "kg"),
                Ingredient("Pepper", 1, "tsp"),
            ),
            steps = listOf(
                PrepStep("Heat the olive oil in a large pan over medium heat." ),
                PrepStep("Add the onion, carrot, celery and garlic and cook for 5 minutes."),
                PrepStep("Add the ground beef and cook for 5 minutes."),
                PrepStep("Add the tomato paste, tomatoes, oregano, basil, salt and pepper and cook for 10 minutes."),
                PrepStep("Serve with spaghetti."),
            )
        )
    ),
    RecipeArticle(
        title = "Salmon\n" +
                "with steamed\n" +
                "legumes",
        image = R.drawable.plate_3,
        color = Color(0xFFF7CE94),
        recipe = Recipe(
            time = 20,
            recipeTitle = "Salmon",
            difficulty = "Hard",
            servings = 2,
            ingredients = listOf(
                Ingredient("Olive oil", 1, "tbsp"),
                Ingredient("Onion", 1, ""),
                Ingredient("Carrot", 3, ""),
                Ingredient("Broccoli", 1, ""),
                Ingredient("Zucchini", 2, ""),
                Ingredient("Asparagus", 1 , "bunch"),
                Ingredient("Green Beans", 200, "g"),
                Ingredient("Salmon", 500, "gr"),

            ),
            steps = listOf(
                PrepStep("Pat the salmon fillets dry with paper towels. Season them with salt, pepper, and preferred herbs or spices."),
                PrepStep("Heat a tablespoon of olive oil in a large pan over medium heat."),
                PrepStep("Once the oil is hot, carefully place the seasoned salmon fillets in the pan, skin side down if applicable. Cook for about 3-4 minutes on each side or until they're cooked through and flake easily with a fork. Remove the cooked salmon from the pan and set aside."),
                PrepStep("Prepare your choice of vegetables for steaming by washing and chopping them."),
                PrepStep("Bring water to a boil in a pot fitted with a steamer basket."),
                PrepStep("Place the prepared vegetables in the steamer basket, cover the pot, and steam for about 5-7 minutes or until they're tender but still crisp."),
                PrepStep("Transfer the steamed vegetables to a bowl. Season with a pinch of salt, a dash of olive oil, and maybe a squeeze of lemon juice for added flavor."),
                PrepStep("Arrange the cooked salmon fillets on a plate and serve alongside the steamed vegetables.")

            )
        )
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

val filterResultList = listOf(
    RecipeArticle(
        title = "Bread with eggs",
        image = R.drawable.plate_2,
        color = Color(0xFFF7CE94)
    ),
    RecipeArticle(
        title = "Asparagus with eggs",
        image = R.drawable.plate_3,
        color = Color(0xFFBEDEDF)
    ),
    RecipeArticle(
        title = "Chicken pie",
        image = R.drawable.plate_1,
        color = Color(0xFFEECED3)
    ),

)
