package com.example.recipeasy.data.repositories

import com.example.recipeasy.data.dataclasses.PantryDao
import com.example.recipeasy.data.dataclasses.PantryItem
import kotlinx.coroutines.flow.Flow

class OfflinePantryRepository (private val pantryDao : PantryDao) : PantryRepository{
    override fun getPantryItemsStream(): Flow<List<PantryItem>> = pantryDao.getAll()

    override fun getItemStream(id: Int): Flow<PantryItem> = pantryDao.getPantryItem(id)

    override suspend fun addItem(item: PantryItem) = pantryDao.addPantryItem(item)

    override suspend fun updateItem(item: PantryItem) = pantryDao.updatePantryItem(item)
    override suspend fun deleteItem(item: PantryItem) = pantryDao.deletePantryItem(item)
}