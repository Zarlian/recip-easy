package com.example.recipeasy.ui.pages.pantrypage.pantry

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeasy.data.repositories.PantryRepository
import com.example.recipeasy.ui.pages.pantrypage.PantryUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class PantryViewModel(private val pantryRepository: PantryRepository) : ViewModel() {

    private val _pantryUiState: MutableStateFlow<PantryUiState> = MutableStateFlow(PantryUiState())
    val pantryUiState: StateFlow<PantryUiState> = _pantryUiState

    init {
        viewModelScope.launch {
            pantryRepository.getPantryItemsStream().collect { pantryItems ->
                _pantryUiState.value = _pantryUiState.value.copy(pantryItems = pantryItems)
            }
        }
    }

    fun increaseQuantity(pantryItemId: Int) {
        viewModelScope.launch {
            _pantryUiState.value = _pantryUiState.value.increaseQuantity(pantryItemId)
            val pantryItem = _pantryUiState.value.pantryItems.find { it.id == pantryItemId }
            pantryItem?.let { pantryRepository.updateItem(it) }
        }
    }

    fun decreaseQuantity(pantryItemId: Int) {
        viewModelScope.launch {
            val updatedUiState = _pantryUiState.value.decreaseQuantity(pantryItemId)
            _pantryUiState.value = updatedUiState
            val pantryItem = updatedUiState.pantryItems.find { it.id == pantryItemId }
            pantryItem?.let {
                if (it.quantity > 0) {
                    pantryRepository.updateItem(it)
                } else {
                    pantryRepository.deleteItem(it)
                }
            }
        }
    }
}