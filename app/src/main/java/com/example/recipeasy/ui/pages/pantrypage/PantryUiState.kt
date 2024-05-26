package com.example.recipeasy.ui.pages.pantrypage

import android.net.Uri
import androidx.room.PrimaryKey
import com.example.recipeasy.data.dataclasses.PantryItem

data class PantryUiState(
    val pantryItems : List<PantryItem> = listOf(),
    val pantryItem: PantryItemDetails = PantryItemDetails(),
    val isEntryValid: Boolean = false,
    val isMainIngredientChecked: Boolean = false
){
    fun increaseQuantity(pantryItemId: Int) = copy(
        pantryItems = pantryItems.map {
            if (it.id == pantryItemId) it.copy(quantity = it.quantity + 1)
            else it
        }
    )

    fun decreaseQuantity(pantryItemId: Int) = copy(
        pantryItems = pantryItems.map {
            if (it.id == pantryItemId && it.quantity > 0) it.copy(quantity = it.quantity - 1)
            else it
        }
    )
}

data class PantryItemDetails(
    val id: Int = 0,
    val name: String = "",
    val quantity: String = "",
    val imageUri: Uri? = null,
    val isMainIngredient: Boolean = false
)

fun PantryItemDetails.toItem(): PantryItem = PantryItem(
    id = id,
    name = name,
    image = imageUri.toString(),
    quantity = quantity.toIntOrNull() ?: 0,
    isMainIngredient = isMainIngredient
)


fun PantryItem.toItemUiState(isEntryValid: Boolean = false): PantryUiState = PantryUiState(
    pantryItem = this.toPantryDetails(),
    isEntryValid = isEntryValid
)


fun PantryItem.toPantryDetails(): PantryItemDetails = PantryItemDetails(
    id = id,
    name = name,
    imageUri = Uri.parse(image),
    quantity = quantity.toString(),
    isMainIngredient = isMainIngredient
)