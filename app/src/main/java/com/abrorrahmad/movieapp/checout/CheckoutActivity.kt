package com.abrorrahmad.movieapp.checout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.abrorrahmad.movieapp.R
import com.abrorrahmad.movieapp.model.Checkout
import com.abrorrahmad.movieapp.model.Film
import com.abrorrahmad.movieapp.utils.Preferences
import kotlinx.android.synthetic.main.activity_checkout.*
import kotlinx.android.synthetic.main.activity_pilih_bangku.*

class CheckoutActivity : AppCompatActivity() {

    private var datalist = ArrayList<Checkout>()
    private var total:Int = 0
    private lateinit var preferences: Preferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        preferences = Preferences(this)
        datalist = intent.getSerializableExtra("data") as ArrayList<Checkout>

        for (a in datalist.indices){
            total += datalist [a].harga!!.toInt()
        }

        datalist.add(Checkout("Total harus dibayar ", total.toString()))

        rc_checkout.layoutManager = LinearLayoutManager (this)
        rc_checkout.adapter = CheckoutAdapter(datalist){

        }

        btn_tiket.setOnClickListener {
            var intent = Intent(this, CheckoutSuccesActivity::class.java)
            startActivity(intent)
        }

    }
}