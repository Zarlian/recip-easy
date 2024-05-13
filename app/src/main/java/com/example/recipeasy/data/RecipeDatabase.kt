package com.example.recipeasy.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.recipeasy.data.dataclasses.PantryItem
import com.example.recipeasy.data.dataclasses.PantryDao

@Database(entities = [PantryItem::class], version = 1)
abstract class RecipeDatabase : RoomDatabase()  {

    abstract fun pantryDao(): PantryDao

    companion object {
        @Volatile
        private var INSTANCE: RecipeDatabase? = null

        fun getDatabase(context: Context): RecipeDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(context, RecipeDatabase::class.java, "recipe_database")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}