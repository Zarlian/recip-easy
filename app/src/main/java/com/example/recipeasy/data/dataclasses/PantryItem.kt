package com.example.recipeasy.data.dataclasses

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pantry")
data class PantryItem(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val quantity: Int,
    val image: String = "",
    val isMainIngredient: Boolean = false
)
