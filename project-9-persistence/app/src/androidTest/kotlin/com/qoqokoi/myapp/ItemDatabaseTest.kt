package com.qoqokoi.myapp

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.qoqokoi.myapp.data.InventoryDatabase
import com.qoqokoi.myapp.data.InventoryItem
import com.qoqokoi.myapp.data.ItemDao
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class ItemDatabaseTest {
    private lateinit var itemDao: ItemDao
    private lateinit var db: InventoryDatabase

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        db = Room.inMemoryDatabaseBuilder(context, InventoryDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        itemDao = db.itemDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun daoInsertAndGetItem_ValidatesPersistence() = runBlocking {
        val item = InventoryItem(id = 1, name = "Kombucha Starter Kit", price = 85000.0, quantity = 5)
        itemDao.insert(item)
        val allItems = itemDao.getAllItems().first()
        assertEquals(allItems[0].name, "Kombucha Starter Kit")
    }
}
