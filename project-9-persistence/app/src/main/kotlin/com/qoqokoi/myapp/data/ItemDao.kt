package com.qoqokoi.myapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: InventoryItem)

    @Update
    suspend fun update(item: InventoryItem)

    @Delete
    suspend fun delete(item: InventoryItem)

    @Query("SELECT * from items WHERE id = :id")
    fun getItem(id: Int): Flow<InventoryItem>

    @Query("SELECT * from items ORDER BY name ASC")
    fun getAllItems(): Flow<List<InventoryItem>>
}
