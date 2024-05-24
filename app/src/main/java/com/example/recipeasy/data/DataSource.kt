package com.example.recipeasy.data

import androidx.compose.ui.graphics.Color
import com.example.recipeasy.R
import com.example.recipeasy.data.dataclasses.IngredientDummy
import com.example.recipeasy.data.dataclasses.PantryItem
//import com.example.recipeasy.data.dataclasses.PantryItem
import com.example.recipeasy.data.dataclasses.RecipeDummy
import com.example.recipeasy.data.dataclasses.RecipeArticle
import com.example.recipeasy.data.dataclasses.ShopArticle
import com.example.recipeasy.data.dataclasses.ShopItem
import kotlinx.coroutines.flow.MutableStateFlow


object DataSource {


    val defaultRecipeList = listOf(
        RecipeArticle(
            id = 0,
            title = "Chicken\n" +
                    "with roasted\n" +
                    "vegetables",
            image = R.drawable.plate_1,
            color = Color(0xFFEECED3)
        ),
        RecipeArticle(
            id = 1,
            title = "Spaghetti\n" +
                    " bolognese",
            image = R.drawable.plate_2,
            color = Color(0xFFC9E2FF),
            recipe = RecipeDummy(
                time = 20,
                recipeTitle = "Spaghetti bolognese",
                difficulty = "Easy",
                servings = 6,
                ingredients = listOf(
                    IngredientDummy("Olive oil", 1, "tbsp"),
                    IngredientDummy("Onion", 1, ""),
                    IngredientDummy("Carrot", 3, ""),
                    IngredientDummy("Celery", 1, "kg"),
                    IngredientDummy("Garlic", 1, "clover"),
                    IngredientDummy("Grounded beef", 800, "gr"),
                    IngredientDummy("Tomato paste", 1, "tbsp"),
                    IngredientDummy("Tomatoes", 500, "gr"),
                    IngredientDummy("Oregano", 1, "tbsp"),
                    IngredientDummy("Basil", 1, "tbsp"),
                    IngredientDummy("Salt", 1, "kg"),
                    IngredientDummy("Pepper", 1, "tsp"),
                ),
                preparation =
                "Heat the olive oil in a large pan over medium heat." +
                        "Add the onion, carrot, celery and garlic and cook for 5 minutes." +
                        "Add the ground beef and cook for 5 minutes." +
                        "Add the tomato paste, tomatoes, oregano, basil, salt and pepper and cook for 10 minutes." +
                        "Serve with spaghetti."

            )
        ),
        RecipeArticle(
            id = 2,
            title = "Salmon\n" +
                    "with steamed\n" +
                    "legumes",
            image = R.drawable.plate_3,
            color = Color(0xFFF7CE94),
            recipe = RecipeDummy(
                time = 20,
                recipeTitle = "Salmon",
                difficulty = "Hard",
                servings = 2,
                ingredients = listOf(
                    IngredientDummy("Olive oil", 1, "tbsp"),
                    IngredientDummy("Onion", 1, ""),
                    IngredientDummy("Carrot", 3, ""),
                    IngredientDummy("Broccoli", 1, ""),
                    IngredientDummy("Zucchini", 2, ""),
                    IngredientDummy("Asparagus", 1, "bunch"),
                    IngredientDummy("Green Beans", 200, "g"),
                    IngredientDummy("Salmon", 500, "gr"),

                    ),
                preparation =
                "Pat the salmon fillets dry with paper towels. Season them with salt, pepper, and preferred herbs or spices." +
                        "Heat a tablespoon of olive oil in a large pan over medium heat." +
                        "Once the oil is hot, carefully place the seasoned salmon fillets in the pan, skin side down if applicable. Cook for about 3-4 minutes on each side or until they're cooked through and flake easily with a fork. Remove the cooked salmon from the pan and set aside." +
                        "Prepare your choice of vegetables for steaming by washing and chopping them." +
                        "Bring water to a boil in a pot fitted with a steamer basket." +
                        "Place the prepared vegetables in the steamer basket, cover the pot, and steam for about 5-7 minutes or until they're tender but still crisp." +
                        "Transfer the steamed vegetables to a bowl. Season with a pinch of salt, a dash of olive oil, and maybe a squeeze of lemon juice for added flavor." +
                        "Arrange the cooked salmon fillets on a plate and serve alongside the steamed vegetables."


            )
        ),
        RecipeArticle(
            id = 3,
            title = "Spaghetti\n" +
                    " bolognese",
            image = R.drawable.plate_2,
            color = Color(0xFFF7E7A9)
        ),
        RecipeArticle(
            id = 9,
            title = "Spaghetti\n" +
                    " bolognese",
            image = R.drawable.plate_2,
            color = Color(0xFFF7E7A9)
        ),
        RecipeArticle(
            id = 10,
            title = "Spaghetti\n" +
                    " bolognese",
            image = R.drawable.plate_2,
            color = Color(0xFFF7E7A9)
        ),

        )

    val recipeList: MutableStateFlow<List<RecipeArticle>> = MutableStateFlow(defaultRecipeList)

    val pantryRecipeList = listOf(
        RecipeArticle(
            id = 0,
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
            //image = R.drawable.chicken_breast
        ),
        PantryItem(
            name = "Beans",
            quantity = 3,
            //image = R.drawable.beans
        ),
        PantryItem(
            name = "Eggs",
            quantity = 7,
            //image = R.drawable.eggs
        ),
        PantryItem(
            name = "Honey",
            quantity = 1,
            //image = R.drawable.honey
        ),
        PantryItem(
            name = "Onions",
            quantity = 4,
            //image = R.drawable.onions
        ),
        PantryItem(
            name = "Tagliatelle",
            quantity = 2,
            //image = R.drawable.tagliatelle
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
            id = 4,
            title = "Bread with eggs",
            image = R.drawable.plate_2,
            color = Color(0xFFF7CE94)
        ),
        RecipeArticle(
            id = 5,
            title = "Asparagus with eggs",
            image = R.drawable.plate_3,
            color = Color(0xFFBEDEDF)
        ),
        RecipeArticle(
            id = 6,
            title = "Chicken pie",
            image = R.drawable.plate_1,
            color = Color(0xFFEECED3)
        )
    )
}