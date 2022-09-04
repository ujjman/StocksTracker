package com.ujjman.money.stockstracker.ui.helper

import androidx.lifecycle.LiveData
import com.ujjman.money.stockstracker.ui.helper.StockDetails
import com.ujjman.money.stockstracker.ui.helper.StockDetailsDao

class StockDetailsRepository(private val stockDetailsDao: StockDetailsDao) {

    val allStockDetails: LiveData<List<StockDetails>> = stockDetailsDao.getAllDetailsList()

    suspend fun insert(stockDetails: StockDetails)
    {
        stockDetailsDao.insert(stockDetails)
    }
    suspend fun delete()
    {
        stockDetailsDao.delete()
    }

}