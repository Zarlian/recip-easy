package com.example.recipeasy.ui.api

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeasy.data.dataclasses.MealDetails
import com.example.recipeasy.network.recipeApi
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface APIUiState {
    data class Success(val recipes: List<MealDetails>) : APIUiState
    object Error : APIUiState
    object Loading : APIUiState
}

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
open class APIViewModel : ViewModel() {
    open var apiUiState: APIUiState by mutableStateOf(APIUiState.Loading)

    init {
        getRandomRecipes()
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    open fun getRandomRecipes() {
        val recipes = mutableListOf<MealDetails>()
        viewModelScope.launch {
            apiUiState = APIUiState.Loading
            repeat(10) {
                try {
                    val result = recipeApi.retrofitService.getRandomRecipe()
                    recipes.add(result.meals[0])
                } catch (e: IOException) {
                    Log.e("ERROR", "${e.message}");
                } catch (e: HttpException) {
                    Log.e("HTTP ERROR", "${e.message}");
                }
            }
            apiUiState = APIUiState.Success(recipes.toList())
        }
    }
}
