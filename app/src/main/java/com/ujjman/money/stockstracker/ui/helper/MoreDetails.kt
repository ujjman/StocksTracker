package com.ujjman.money.stockstracker.ui.helper

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.ujjman.money.stockstracker.R

class MoreDetails : AppCompatActivity() {
    private lateinit var openTextView: TextView
    private lateinit var closeTextView: TextView
    private lateinit var highTextView: TextView
    private lateinit var lowTextView: TextView
    private lateinit var volumeTextView: TextView
    private lateinit var nameTextView: TextView
    private lateinit var dateTextView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_more_details)
        fullScreencall()
        initialize()
    }
    fun initialize()
    {
        openTextView=findViewById(R.id.open_moredetails_textview)
        closeTextView=findViewById(R.id.close_moredetails_textview)
        highTextView=findViewById(R.id.high_moredetails_textview)
        lowTextView=findViewById(R.id.low_moredetails_textview)
        volumeTextView=findViewById(R.id.volume_moredetails_textview)
        dateTextView=findViewById(R.id.date_moredetails_textview)
        nameTextView=findViewById(R.id.name_textview)
        val i=intent.extras
        openTextView.text= i!!.getString("open")+"   Rs."
        closeTextView.text= i!!.getString("close")+"   Rs."
        lowTextView.text= i!!.getString("low")+"   Rs."
        highTextView.text= i!!.getString("high")+"   Rs."
        volumeTextView.text= i!!.getString("volume")
        dateTextView.text= i!!.getString("date")
        nameTextView.text= i!!.getString("code")
    }
    fun fullScreencall() {
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
            val v = this.window.decorView
            v.systemUiVisibility = View.GONE
        } else if (Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            val decorView = window.decorView
            val uiOptions =
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            decorView.systemUiVisibility = uiOptions
        }
    }
    fun onBackPressed(view: View)
    {
        finish()
    }
}