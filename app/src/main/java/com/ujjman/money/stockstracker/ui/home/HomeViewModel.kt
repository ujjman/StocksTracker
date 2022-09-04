package com.ujjman.money.stockstracker.ui.home

import android.app.Application
import android.content.Intent
import androidx.lifecycle.*
import com.ujjman.money.stockstracker.ui.helper.StockDetails
import com.ujjman.money.stockstracker.ui.helper.StockDetailsRepository
import com.ujjman.money.stockstracker.ui.helper.StockDetailsRoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    val allStockDetails: LiveData<List<StockDetails>>
    private val repository: StockDetailsRepository
    init {
        val dao= StockDetailsRoomDatabase.getDatabase(application).stockDetailsDao()
        repository = StockDetailsRepository(dao)
        allStockDetails=repository.allStockDetails
    }

    fun insertStockDetails(stockDetails: StockDetails) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(stockDetails)
    }
    fun deleteAll() = viewModelScope.launch {
        repository.delete()
    }
    fun makeUrlForApi(code: String, date: String): String {
        return "https://api.polygon.io/v1/open-close/$code/$date?adjusted=true&apiKey=OtWYF2elcdntPfZd65tVwv3vNqiMMrXm"
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
}