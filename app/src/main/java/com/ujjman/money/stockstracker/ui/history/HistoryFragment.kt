package com.ujjman.money.stockstracker.ui.history

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ujjman.money.stockstracker.R
import com.ujjman.money.stockstracker.databinding.FragmentHistoryBinding
import com.ujjman.money.stockstracker.ui.helper.MoreDetails
import com.ujjman.money.stockstracker.ui.helper.StockDetails


class HistoryFragment : Fragment(), ClickListener {

    private lateinit var historyViewModel: HistoryViewModel
    private var _binding: FragmentHistoryBinding? = null
    private lateinit var deleteAll: Button

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        historyViewModel =
            ViewModelProvider(this).get(HistoryViewModel::class.java)
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        val recyclerView= binding.recyclerView
        recyclerView?.layoutManager =LinearLayoutManager(context)
        val adapter = context?.let { StockRVAdapter(it,this) }
        recyclerView?.adapter =adapter
        val root: View = binding.root
        deleteAll=binding.deleteAllHistory
        deleteAll.setOnClickListener {
            deleteHistory(it)
        }
        historyViewModel.allStockDetails.observe(this, Observer {
            adapter?.updateList(it)
        })

        return root
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun deleteHistory(view: View)
    {
        historyViewModel.deleteAll()
    }

    override fun onItemClicked(stockDetails: StockDetails) {
        val intent = Intent(context, MoreDetails::class.java)
        historyViewModel.putExtrasInIntent(intent,stockDetails)
        startActivity(intent)
    }
}