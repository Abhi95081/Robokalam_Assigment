package com.example.robokalam.RoomDb

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Portfolio::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun portfolioDao(): PortfolioDao
}
