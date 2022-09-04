package com.ujjman.money.stockstracker.ui.history

import android.app.Application
import android.content.Intent
import androidx.lifecycle.*
import com.ujjman.money.stockstracker.ui.helper.StockDetails
import com.ujjman.money.stockstracker.ui.helper.StockDetailsRepository
import com.ujjman.money.stockstracker.ui.helper.StockDetailsRoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HistoryViewModel(application: Application) : AndroidViewModel(application) {

    val allStockDetails: LiveData<List<StockDetails>>
    private val repository: StockDetailsRepository
    init {
        val dao= StockDetailsRoomDatabase.getDatabase(application).stockDetailsDao()
         repository = StockDetailsRepository(dao)
        allStockDetails=repository.allStockDetails
    }

    fun putExtrasInIntent(intent: Intent, stockDetails: StockDetails)
    {
        intent.putExtra("open",stockDetails.open)
        intent.putExtra("close",stockDetails.close)
        intent.putExtra("code",stockDetails.code)
        intent.putExtra("date",stockDetails.from)
        intent.putExtra("high",stockDetails.high)
        intent.putExtra("low",stockDetails.low)
        intent.putExtra("volume",stockDetails.volume)
    }

    fun deleteAll() = viewModelScope.launch {
        repository.delete()
    }


}