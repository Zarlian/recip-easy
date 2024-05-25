package com.example.recipeasy.ui.pages.pantrypage.entry

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.recipeasy.data.repositories.PantryRepository
import com.example.recipeasy.ui.pages.pantrypage.PantryItemDetails
import com.example.recipeasy.ui.pages.pantrypage.PantryUiState
import com.example.recipeasy.ui.pages.pantrypage.toItem

class PantryEntryViewModel(private val pantryRepository: PantryRepository) : ViewModel() {

    var pantryUiState by mutableStateOf(PantryUiState())
        private set

    fun updateUiState(pantryDetails: PantryItemDetails) {
        pantryUiState =
            PantryUiState(pantryItem = pantryDetails, isEntryValid = validateInput(pantryDetails))
    }

    suspend fun saveItem() {
        if (validateInput()) {
            pantryRepository.addItem(pantryUiState.pantryItem.toItem())
        }
    }

    private fun validateInput(uiState: PantryItemDetails = pantryUiState.pantryItem): Boolean {
        return with(uiState) {
            name.isNotBlank()  && imageUri != null && quantity.isNotBlank()
        }
    }
}
