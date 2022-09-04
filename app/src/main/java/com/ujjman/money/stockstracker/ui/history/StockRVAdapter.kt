package com.ujjman.money.stockstracker.ui.history

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ujjman.money.stockstracker.R
import com.ujjman.money.stockstracker.ui.helper.StockDetails
import java.util.*

class StockRVAdapter(private val context: Context, private val listener: ClickListener):RecyclerView.Adapter<StockRVAdapter.StockViewHolder>() {

    private val allStockHistory = ArrayList<StockDetails>()

    inner class StockViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val linearLayout: LinearLayout= itemView.findViewById(R.id.linearlayout_all_stocks)
        val nameTextView: TextView = itemView.findViewById(R.id.stock_name)
        val dateTextView: TextView = itemView.findViewById(R.id.date_textview)
        val openTextView: TextView = itemView.findViewById(R.id.open_textview_item_stocks)
        val closeTextView: TextView = itemView.findViewById(R.id.close_textview_item_stocks)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockViewHolder {
        val viewHolder=StockViewHolder(LayoutInflater.from(context).inflate(R.layout.item_stock,parent, false));
        viewHolder.linearLayout.setOnClickListener {
            listener.onItemClicked(allStockHistory[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: StockViewHolder, position: Int) {
        holder.nameTextView.text= allStockHistory[position].from
        holder.dateTextView.text= allStockHistory[position].code
        holder.openTextView.text= allStockHistory[position].open
        holder.closeTextView.text= allStockHistory[position].close

    }

    override fun getItemCount(): Int {
        return allStockHistory.size
    }

    fun updateList(newList: List<StockDetails>)
    {
        allStockHistory.clear()
        allStockHistory.addAll(newList)
        notifyDataSetChanged()
    }

}
interface ClickListener
{
    fun onItemClicked(stockDetails: StockDetails)
}