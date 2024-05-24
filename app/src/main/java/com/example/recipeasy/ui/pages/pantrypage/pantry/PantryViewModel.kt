package com.example.recipeasy.ui.pages.pantrypage.pantry

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeasy.data.repositories.PantryRepository
import com.example.recipeasy.ui.pages.pantrypage.PantryUiState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class PantryViewModel(pantryRepository: PantryRepository) : ViewModel() {

    val pantryUiState :StateFlow<PantryUiState> =
        pantryRepository.getPantryItemsStream().map { PantryUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = PantryUiState()
            )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}