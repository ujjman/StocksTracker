package com.ujjman.money.stockstracker.ui.helper

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stock_details_table")
class StockDetails(
    @ColumnInfo(name = "date") var from: String,
    @ColumnInfo(name = "Code") var code: String,
    @ColumnInfo(name = "open") var open: String,
    @ColumnInfo(name = "high") var high: String,
    @ColumnInfo(name = "low") var low: String,
    @ColumnInfo(name = "close") var close: String,
    @ColumnInfo(name = "volume") var volume: String

    )
{
    @PrimaryKey(autoGenerate = true) var id=0
}