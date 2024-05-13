package com.example.recipeasy.ui.pages.recipedetail

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeasy.data.dataclasses.MealDetails
import com.example.recipeasy.data.dataclasses.Recipe
import com.example.recipeasy.network.recipeApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

//sealed interface RecipeDetailUiState {
//    data class Success(val recipe: MealDetails) : RecipeDetailUiState
//    object Error : RecipeDetailUiState
//    object Loading : RecipeDetailUiState
//}
//
//@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
//class RecipeDetailViewModel : ViewModel() {
//    var apiUiState: RecipeDetailUiState by mutableStateOf(RecipeDetailUiState.Loading)
//
//
//    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
//    fun fetchRecipeDetail(recipeId: String) {
//        viewModelScope.launch {
//            apiUiState = RecipeDetailUiState.Loading
//            try {
//                val result = recipeApi.retrofitService.getRecipeById(recipeId)
//                apiUiState = RecipeDetailUiState.Success(result.meals.first())
//            } catch (e: IOException) {
//                Log.e("ERROR", "${e.message}");
//            } catch (e: HttpException) {
//                Log.e("HTTP ERROR", "${e.message}");
//            }
//        }
//    }
//}


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
                // Optionally, handle network errors (e.g., show error state to UI)
            } catch (e: HttpException) {
                Log.e("HTTP ERROR", "HTTP error: ${e.message}")
                // Optionally, handle HTTP errors (e.g., show error state to UI)
            }
        }
    }
}