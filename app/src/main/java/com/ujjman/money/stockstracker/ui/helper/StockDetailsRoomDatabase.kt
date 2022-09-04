package com.ujjman.money.stockstracker.ui.helper

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(StockDetails::class), version = 1, exportSchema = false)
abstract class StockDetailsRoomDatabase : RoomDatabase() {

    abstract fun stockDetailsDao(): StockDetailsDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: StockDetailsRoomDatabase? = null

        fun getDatabase(context: Context): StockDetailsRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    StockDetailsRoomDatabase::class.java,
                    "stock_details_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}