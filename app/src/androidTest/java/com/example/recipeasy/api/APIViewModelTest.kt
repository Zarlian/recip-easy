package com.example.recipeasy.api

import com.example.recipeasy.data.dataclasses.MealDetails
import com.example.recipeasy.data.dataclasses.Recipe
import com.example.recipeasy.network.APIservice
import com.example.recipeasy.ui.api.APIUiState
import com.example.recipeasy.ui.api.APIViewModel
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

@ExperimentalCoroutinesApi
class APIViewModelTest {

    private val dummyRecipe = Recipe(
        listOf(
            MealDetails(
                "52772",
                "Teriyaki Chicken Casserole",
                "Instructions",
                "Thumbnail URL",
                emptyList(),
                "Difficulty",
                "Servings",
                "Time"
            )
        )
    )

    @Test
    fun testGetRandomRecipesNotEmpty() = runTest {
        val mockApiService = mock(APIservice::class.java)
        `when`(mockApiService.getRandomRecipe()).thenReturn(dummyRecipe)

        val viewModel = APIViewModel(mockApiService)
        viewModel.getRandomRecipes()

        // Wait until apiUiState changes from Loading to Success
        withContext(Dispatchers.Default.limitedParallelism(1)) {
            withTimeout(5000) {
                while (viewModel.apiUiState is APIUiState.Loading) {
                    delay(50)
                }
            }
        }

        val state = viewModel.apiUiState as APIUiState.Success
        assertFalse(state.recipes.isEmpty())
    }

    @Test
    fun testResponseLayout() = runTest {
        val mockApiService = mock(APIservice::class.java)
        `when`(mockApiService.getRandomRecipe()).thenReturn(dummyRecipe)

        val viewModel = APIViewModel(mockApiService)
        viewModel.getRandomRecipes()

        // Wait until apiUiState changes from Loading to Success
        withContext(Dispatchers.Default.limitedParallelism(1)) {
            withTimeout(5000) {
                while (viewModel.apiUiState is APIUiState.Loading) {
                    delay(50)
                }
            }
        }

        val state = viewModel.apiUiState as APIUiState.Success
        val mealDetails = state.recipes[0]

        assertNotNull(mealDetails.idMeal)
        assertTrue(mealDetails.idMeal.isNotEmpty())
        assertNotNull(mealDetails.strMeal)
        assertTrue(mealDetails.strMeal.isNotEmpty())
        assertNotNull(mealDetails.strInstructions)
        assertTrue(mealDetails.strInstructions.isNotEmpty())
        assertNotNull(mealDetails.strMealThumb)
        assertTrue(mealDetails.strMealThumb.isNotEmpty())
        assertNotNull(mealDetails.difficulty)
        assertTrue(mealDetails.difficulty.isNotEmpty())
        assertNotNull(mealDetails.servings)
        assertTrue(mealDetails.servings.isNotEmpty())
        assertNotNull(mealDetails.time)
        assertTrue(mealDetails.time.isNotEmpty())
    }
}