package com.example.expensetracker

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface TransactionDao {
    @Query("SELECT * from transactions")
    suspend fun getAll(): List<Transaction>

    @Insert
    suspend fun insertAll(vararg transaction: Transaction)

    @Delete
    suspend fun delete(transaction: Transaction)

    @Update
    suspend fun update(vararg transaction: Transaction)
}
