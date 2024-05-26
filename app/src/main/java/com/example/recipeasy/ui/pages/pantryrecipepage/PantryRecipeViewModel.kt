package com.example.recipeasy.ui.pages.pantryrecipepage

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeasy.data.dataclasses.MealDetails
import com.example.recipeasy.data.repositories.PantryRepository
import com.example.recipeasy.network.recipeApi
import com.example.recipeasy.ui.pages.pantrypage.PantryUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class PantryRecipeViewModel(pantryRepository: PantryRepository) : ViewModel() {

    private val _pantryUiState: MutableStateFlow<PantryUiState> = MutableStateFlow(PantryUiState())

    private val _recipesState = MutableStateFlow<List<MealDetails>>(emptyList())
    val recipesState: StateFlow<List<MealDetails>> = _recipesState

    init {
        viewModelScope.launch {
            pantryRepository.getMainIngredientsStream().collect { pantryItems ->
                _pantryUiState.value = _pantryUiState.value.copy(pantryItems = pantryItems)
            }
        }

        viewModelScope.launch {
            pantryRepository.getMainIngredientsStream().collect { pantryItems ->
                pantryItems.forEach {
                    fetchIngredientRecipes(it.name)
                }
            }
        }
    }

    private fun fetchIngredientRecipes(ingredient: String) {
        viewModelScope.launch {
            try {
                val result = recipeApi.retrofitService.getRecipesByIngredient(ingredient)
                if(result.meals.isNotEmpty()){
                    addRecipesToList(result.meals)
                }
            } catch (e: IOException) {
                Log.e("ERROR", "Network error: ${e.message}")
            } catch (e: HttpException) {
                Log.e("HTTP ERROR", "HTTP error: ${e.message}")
            }
        }
    }

    private fun addRecipesToList(recipes: List<MealDetails>) {
        _recipesState.value += recipes
    }
}