package com.example.recipeasy.ui

import HomeScreen
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.recipeasy.data.dataclasses.Ingredient
import com.example.recipeasy.data.dataclasses.MealDetails
import com.example.recipeasy.ui.api.APIUiState
import com.example.recipeasy.ui.api.APIViewModel
import org.junit.Rule
import org.junit.Test

class HomeScreenUiTest {

    @get:Rule
    val composeTestRule = createComposeRule()


    @Test
    fun homeScreenDisplaysLoading() {
        val fakeAPIViewModel = FakeAPIViewModel(APIUiState.Loading)

        composeTestRule.setContent {
            val navController = rememberNavController()
            HomeScreen(
                navigateToShop = {},
                navigateToFilter = {},
                selectedPage = Page.HOME,
                onItemSelected = {},
                navigateToRecipeDetail = {},
                navController = navController,
                apiViewModel = fakeAPIViewModel
            )
        }

        composeTestRule.onNodeWithText("Loading recipes...").assertExists()
    }


    @Test
    fun homeScreenDisplaysError() {
        val fakeAPIViewModel = FakeAPIViewModel(APIUiState.Error)

        composeTestRule.setContent {
            val navController = rememberNavController()
            HomeScreen(
                navigateToShop = {},
                navigateToFilter = {},
                selectedPage = Page.HOME,
                onItemSelected = {},
                navigateToRecipeDetail = {},
                navController = navController,
                apiViewModel = fakeAPIViewModel
            )
        }

        composeTestRule.onNodeWithText("Error!").assertExists()
    }

    @Test
    fun homeScreenDisplaysRecipes() {
        val mealDetails = MealDetails(
            "Meal1",
            "Category1",
            "Area1",
            "Instructions1",
            listOf(Ingredient("Ingredient1", "Measure1"), Ingredient("Ingredient2", "Measure2")),
            "Easy",
            "4",
            "30min"
        )
        val fakeAPIViewModel = FakeAPIViewModel(APIUiState.Success(listOf(mealDetails)))

        composeTestRule.setContent {
            val navController = rememberNavController()
            HomeScreen(
                navigateToShop = {},
                navigateToFilter = {},
                selectedPage = Page.HOME,
                onItemSelected = {},
                navigateToRecipeDetail = {},
                navController = navController,
                apiViewModel = fakeAPIViewModel
            )
        }

        composeTestRule.onNodeWithText("Category1").assertExists()
    }
}

class FakeAPIViewModel(private val uiState: APIUiState) : APIViewModel() {
    override var apiUiState: APIUiState = uiState
        get() = field
        set(value) {
            field = value
            notifyChange()
        }

    private fun notifyChange() {
        // Notify observers about the change in state (if any)
    }

    override fun getRandomRecipes() {
        // Do nothing
    }
}