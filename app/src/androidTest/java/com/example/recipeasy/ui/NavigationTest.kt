package com.example.recipeasy.ui

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NavigationTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testHomeScreenNavigation() {
        composeTestRule.setContent {
            val navController = rememberNavController()
            RecipEasyNavHost(navController = navController)
        }

        composeTestRule.onNodeWithText("Recipeasy", ignoreCase = true).assertExists()

        //Going to shop
        composeTestRule.onNodeWithContentDescription("Shop", ignoreCase = true).performClick()
        composeTestRule.onNodeWithText("Shopping List", ignoreCase = true).assertExists()

        //Going back to home
        composeTestRule.onNodeWithContentDescription("back", ignoreCase = true).performClick()
        composeTestRule.onNodeWithText("Recipeasy", ignoreCase = true).assertExists()

        //Going to filter
        composeTestRule.onNodeWithContentDescription("filter", ignoreCase = true).performClick()
        composeTestRule.onNodeWithText("Result", ignoreCase = true).assertExists()

        //Going back to home
        composeTestRule.onNodeWithContentDescription("back", ignoreCase = true).performClick()
        composeTestRule.onNodeWithText("Recipeasy", ignoreCase = true).assertExists()
    }
}