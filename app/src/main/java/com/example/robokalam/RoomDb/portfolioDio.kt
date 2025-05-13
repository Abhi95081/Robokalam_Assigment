package com.example.robokalam.RoomDb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PortfolioDao {
    @Insert
    suspend fun insertPortfolio(portfolio: Portfolio)

    @Query("SELECT * FROM Portfolio")
    fun getAll(): Flow<List<Portfolio>>
}