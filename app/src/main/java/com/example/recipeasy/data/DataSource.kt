package com.example.recipeasy.data

import androidx.compose.ui.graphics.Color
import com.example.recipeasy.R
import com.example.recipeasy.data.dataclasses.Ingredient
import com.example.recipeasy.data.dataclasses.MealDetails
import com.example.recipeasy.data.dataclasses.PantryItem
import com.example.recipeasy.data.dataclasses.RecipeArticle
import com.example.recipeasy.data.dataclasses.ShopArticle
import com.example.recipeasy.data.dataclasses.ShopItem
import kotlinx.coroutines.flow.MutableStateFlow


object DataSource {


    val defaultRecipeList = listOf(
        MealDetails(
            idMeal = "0",
            strMeal = "Chicken with roasted vegetables",
            strInstructions = "Heat the olive oil in a large pan over medium heat. Add the onion, carrot, celery and garlic and cook for 5 minutes. Add the ground beef and cook for 5 minutes. Add the tomato paste, tomatoes, oregano, basil, salt and pepper and cook for 10 minutes. Serve with spaghetti.",
            strMealThumb = "R.drawable.plate_1",
            ingredients = listOf(
                Ingredient("Olive oil", "1 tbsp"),
                Ingredient("Onion", ""),
                Ingredient("Carrot", ""),
                Ingredient("Celery", "1 kg"),
                Ingredient("Garlic", "1 clover"),
                Ingredient("Grounded beef", "800 gr"),
                Ingredient("Tomato paste", "1 tbsp"),
                Ingredient("Tomatoes", "500 gr"),
                Ingredient("Oregano", "1 tbsp"),
                Ingredient("Basil", "1 tbsp"),
                Ingredient("Salt", "1 kg"),
                Ingredient("Pepper", "1 tsp")
            ),
            difficulty = "Easy",
            servings = "6",
            time = "20"
        ),
        MealDetails(
            idMeal = "1",
            strMeal = "Spaghetti bolognese",
            strInstructions = "Pat the salmon fillets dry with paper towels. Season them with salt, pepper, and preferred herbs or spices. Heat a tablespoon of olive oil in a large pan over medium heat. Once the oil is hot, carefully place the seasoned salmon fillets in the pan, skin side down if applicable. Cook for about 3-4 minutes on each side or until they're cooked through and flake easily with a fork. Remove the cooked salmon from the pan and set aside. Prepare your choice of vegetables for steaming by washing and chopping them. Bring water to a boil in a pot fitted with a steamer basket. Place the prepared vegetables in the steamer basket, cover the pot, and steam for about 5-7 minutes or until they're tender but still crisp. Transfer the steamed vegetables to a bowl. Season with a pinch of salt, a dash of olive oil, and maybe a squeeze of lemon juice for added flavor. Arrange the cooked salmon fillets on a plate and serve alongside the steamed vegetables.",
            strMealThumb = "R.drawable.plate_2",
            ingredients = listOf(
                Ingredient("Olive oil", "1 tbsp"),
                Ingredient("Onion", ""),
                Ingredient("Carrot", ""),
                Ingredient("Broccoli", ""),
                Ingredient("Zucchini", ""),
                Ingredient("Asparagus", "1 bunch"),
                Ingredient("Green Beans", "200 g"),
                Ingredient("Salmon", "500 gr")
            ),
            difficulty = "Hard",
            servings = "2",
            time = "20"
        ),
        MealDetails(
            idMeal = "3",
            strMeal = "Spaghetti bolognese",
            strInstructions = "",
            strMealThumb = "R.drawable.plate_2",
            ingredients = emptyList(),
            difficulty = "",
            servings = "",
            time = ""
        ),
        MealDetails(
            idMeal = "9",
            strMeal = "Spaghetti bolognese",
            strInstructions = "",
            strMealThumb = "R.drawable.plate_2",
            ingredients = emptyList(),
            difficulty = "",
            servings = "",
            time = ""
        ),
        MealDetails(
            idMeal = "10",
            strMeal = "Spaghetti bolognese",
            strInstructions = "",
            strMealThumb = "R.drawable.plate_2",
            ingredients = emptyList(),
            difficulty = "",
            servings = "",
            time = ""
        )
    )

    val recipeList: MutableStateFlow<List<MealDetails>> = MutableStateFlow(defaultRecipeList)

    val pantryItems = listOf(
        PantryItem(
            id = 0,
            name = "Chicken breast",
            quantity = 2,
            image = "",
            isMainIngredient = true
        ),
        PantryItem(
            id = 1,
            name = "Beans",
            quantity = 3,
            image = "",
            isMainIngredient = false
        ),
        PantryItem(
            id = 2,
            name = "Eggs",
            quantity = 7,
            image = "",
            isMainIngredient = false
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