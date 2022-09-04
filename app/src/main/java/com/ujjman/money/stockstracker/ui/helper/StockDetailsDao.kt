package com.ujjman.money.stockstracker.ui.helper

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ujjman.money.stockstracker.ui.helper.StockDetails

@Dao
interface StockDetailsDao {

    @Query("SELECT * FROM stock_details_table ORDER BY id ASC")
    fun getAllDetailsList(): LiveData<List<StockDetails>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(stockDetails: StockDetails)

    @Query("DELETE FROM stock_details_table ")
    suspend fun delete()
}