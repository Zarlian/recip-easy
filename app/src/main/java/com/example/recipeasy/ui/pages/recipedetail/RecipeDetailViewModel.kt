package com.example.recipeasy.ui.pages.recipedetail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeasy.data.dataclasses.MealDetails
import com.example.recipeasy.network.recipeApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class RecipeDetailViewModel() : ViewModel() {
    private val _recipeDetailState = MutableStateFlow<MealDetails?>(null)
    val recipeDetailState: StateFlow<MealDetails?> = _recipeDetailState


    fun fetchRecipeDetail(recipeId: String) {
        viewModelScope.launch {
            try {
                val result = recipeApi.retrofitService.getRecipeById(recipeId)
                _recipeDetailState.value = result.meals.first()
            } catch (e: IOException) {
                Log.e("ERROR", "Network error: ${e.message}")
            } catch (e: HttpException) {
                Log.e("HTTP ERROR", "HTTP error: ${e.message}")
            }
        }
    }
}