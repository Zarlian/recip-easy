package com.example.recipeasy.data.repositories

import com.example.recipeasy.data.dataclasses.PantryItem
import kotlinx.coroutines.flow.Flow

interface PantryRepository {
    fun getPantryItemsStream(): Flow<List<PantryItem>>

    fun getItemStream(id: Int): Flow<PantryItem>

    suspend fun addItem(item: PantryItem)

    suspend fun updateItem(item: PantryItem)

    suspend fun deleteItem(item: PantryItem)
}