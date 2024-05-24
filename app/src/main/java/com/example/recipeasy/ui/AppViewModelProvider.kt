package com.example.recipeasy.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.recipeasy.RecipeApplication
import com.example.recipeasy.ui.home.HomeViewModel
import com.example.recipeasy.ui.pages.pantrypage.entry.PantryEntryViewModel
import com.example.recipeasy.ui.pages.pantrypage.pantry.PantryViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            HomeViewModel(recipeasyApplication().container.recipeRepository)
        }
        initializer {
            PantryEntryViewModel(recipeasyApplication().container.pantryRepository)
        }

        initializer {
            PantryViewModel(recipeasyApplication().container.pantryRepository)
        }
    }
}


fun CreationExtras.recipeasyApplication(): RecipeApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as RecipeApplication)