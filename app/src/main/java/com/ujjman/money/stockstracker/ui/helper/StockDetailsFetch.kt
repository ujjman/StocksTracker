package com.ujjman.money.stockstracker.ui.helper


import android.content.Context
import android.graphics.Typeface
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.ujjman.money.stockstracker.databinding.FragmentHomeBinding
import com.ujjman.money.stockstracker.ui.home.HomeViewModel
import org.json.JSONObject

class StockDetailsFetch(private val application: Context, private val homeViewModel: HomeViewModel, private val binding: FragmentHomeBinding) {

    private lateinit var stockDetails:StockDetails
    fun getStockData(url:String)
    {
        val queue = Volley.newRequestQueue(application)
        stockDetails= StockDetails("","","","","","","")
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                updateData(response)
            },
            { error ->
                if(error.networkResponse.statusCode == 429)
                {
                    Toast.makeText(application,"Please try again after 1 min as API supports 5 responses per min",Toast.LENGTH_LONG).show()
                    updateOpenAndCloseTextview()
                }
                else
                {
                    Toast.makeText(application,"Error occured : "+error.cause,Toast.LENGTH_LONG).show()
                }

            }
        )
        queue.add(jsonObjectRequest)
    }
    private fun updateData(response: JSONObject)
    {
        stockDetails.open=response.getString("open")
        stockDetails.close=response.getString("close")
        stockDetails.from=response.getString("from")
        stockDetails.code=response.getString("symbol")
        stockDetails.high=response.getString("high")
        stockDetails.low=response.getString("low")
        stockDetails.volume=response.getString("volume")
        homeViewModel.insertStockDetails(stockDetails)
        val openTextview= binding.openTextview
        val closeTextview=binding.closeTextview
        val moreDetailsTextview=binding.moreDetailsTextview
        val stockNameTopTextview=binding.stockNameTopTextview
        binding.moreDetailsTextview.isClickable=true
        openTextview.text = "Open : "+response.getString("open")+"   Rs."
        closeTextview.text="Close : "+response.getString("close")+"   Rs."
        stockNameTopTextview.text=response.getString("symbol")
        moreDetailsTextview.text="More Details"
        moreDetailsTextview.setTypeface(null,Typeface.BOLD_ITALIC)
    }

    private fun updateOpenAndCloseTextview()
    {
        binding.openTextview.text="Please wait"
        binding.closeTextview.text="Please wait"
        binding.moreDetailsTextview.text=""
        binding.moreDetailsTextview.isClickable=false
    }

     fun returnStockDetailsObject(): StockDetails
    {
        return this.stockDetails
    }
}