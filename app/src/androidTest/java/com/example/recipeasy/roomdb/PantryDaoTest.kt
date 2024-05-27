package com.example.recipeasy.roomdb

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.recipeasy.data.DataSource.pantryItems
import com.example.recipeasy.data.RecipeDatabase
import com.example.recipeasy.data.dataclasses.PantryDao
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.IOException

class PantryDaoTest {

    private lateinit var pantryDao: PantryDao
    private lateinit var db: RecipeDatabase


    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        db = Room.inMemoryDatabaseBuilder(context, RecipeDatabase::class.java)
            // Allowing main thread queries, just for testing.
            .allowMainThreadQueries()
            .build()
        pantryDao = db.pantryDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    val firstPantryItem = pantryItems[0]
    val secondPantryItem = pantryItems[1]
    val thirdPantryItem = pantryItems[2]

    private suspend fun addOnePantryItemToDB() {
        pantryDao.addPantryItem(firstPantryItem)
    }

    private suspend fun addTwoPantryItemsToDB() {
        pantryDao.addPantryItem(secondPantryItem)
        pantryDao.addPantryItem(thirdPantryItem)
    }

    private suspend fun addAllItemsToDB() {
        pantryDao.addPantryItem(firstPantryItem)
        pantryDao.addPantryItem(secondPantryItem)
        pantryDao.addPantryItem(thirdPantryItem)
    }

    @Test
    @Throws(Exception::class)
    fun daoInsert_addItemIntoDB() = runBlocking {
        addOnePantryItemToDB()
        val allOrders = pantryDao.getAll().first()
        assertEquals(allOrders[0].name, firstPantryItem.name)
    }

    @Test
    @Throws(Exception::class)
    fun daoGetAllItems_returnsAllItemsFromDB() = runBlocking {
        addTwoPantryItemsToDB()
        val allOrders = pantryDao.getAll().first()
        assertEquals(allOrders[0].name, secondPantryItem.name)
        assertEquals(allOrders[1].name, thirdPantryItem.name)
    }

    @Test
    @Throws(Exception::class)
    fun daoGetMainIngredient_returnsItemsWithMainIngredientsFromDB() = runBlocking {
        addAllItemsToDB()
        val allOrders = pantryDao.getMainIngredients().first()
        assertEquals(allOrders[0].name, firstPantryItem.name)
    }

}