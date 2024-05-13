package com.example.recipeasy.data.dataclasses

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface PantryDao {
    @Query("SELECT * FROM pantry")
    fun getAll(): Flow<List<PantryItem>>

    @Query("SELECT * FROM pantry WHERE id = :id")
    fun getPantryItem(id: Int): Flow<PantryItem>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addPantryItem(item: PantryItem)

    @Update
    suspend fun updatePantryItem(item: PantryItem)

    @Delete
    suspend fun deletePantryItem(item: PantryItem)

}