package com.example.recipeasy.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeasy.data.DataSource
import com.example.recipeasy.data.repositories.RecipeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class HomeViewModel (recipesRepository: RecipeRepository) : ViewModel() {

        private val _uiState = MutableStateFlow(HomeUiState(recipes = DataSource.defaultRecipeList))
        val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()
        val homeUiState: StateFlow<HomeUiState> =
            recipesRepository.getAllRecipes().map { HomeUiState(it) }
                .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                    initialValue = HomeUiState()
                )

        companion object {
            const val TIMEOUT_MILLIS = 5_000L
        }
}