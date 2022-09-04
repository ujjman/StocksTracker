package com.ujjman.money.stockstracker.ui.home

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ujjman.money.stockstracker.MainActivity
import com.ujjman.money.stockstracker.R
import com.ujjman.money.stockstracker.databinding.FragmentHomeBinding
import com.ujjman.money.stockstracker.ui.helper.MoreDetails
import com.ujjman.money.stockstracker.ui.helper.StockDetails
import com.ujjman.money.stockstracker.ui.helper.StockDetailsFetch
import com.ujjman.money.stockstracker.ui.history.ClickListener

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var searchButton: Button
    private lateinit var moreDetailsTextView: TextView
    private var _binding: FragmentHomeBinding? = null
    private lateinit var fetchStockDetails: StockDetailsFetch

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(activity!!.application)).get(HomeViewModel::class.java)
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        searchButton= binding.searchButton
        moreDetailsTextView=binding.moreDetailsTextview
        binding.moreDetailsTextview.isClickable=false
        moreDetailsTextView.setOnClickListener { view -> openMoreDetails(view) }
        searchButton.setOnClickListener { view -> searchStock(view)  }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun searchStock(view: View)
    {
        binding.openTextview.text="Loading..."
        binding.closeTextview.text="Loading..."
        binding.moreDetailsTextview.isClickable=false
        fetchStockDetails= StockDetailsFetch(binding.root.context, homeViewModel,binding)
        val stockName: TextView= binding.stockNameTextview
        val stockDate: TextView= binding.stockDateTextview
        val url= homeViewModel.makeUrlForApi(stockName.text.toString().uppercase(),stockDate.text.toString())
        fetchStockDetails.getStockData(url)
    }

    private fun openMoreDetails(view: View)
    {
        val intent = Intent(context, MoreDetails::class.java)
        homeViewModel.putExtrasInIntent(intent,fetchStockDetails.returnStockDetailsObject())
        startActivity(intent)
    }

}