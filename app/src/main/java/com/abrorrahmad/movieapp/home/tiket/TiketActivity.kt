package com.abrorrahmad.movieapp.home.tiket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.abrorrahmad.movieapp.R
import com.abrorrahmad.movieapp.model.Checkout
import com.abrorrahmad.movieapp.model.Film
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_tiket.*
import java.lang.System.load

class TiketActivity : AppCompatActivity() {

    private var datalist = ArrayList<Checkout>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tiket)

        var data = intent.getParcelableExtra<Film>("data")

        tv_title.text = data?.judul
        tv_genre.text = data?.genre
        tv_rate.text = data?.rating

      Glide.with(this)
          .load(data?.poster)
          .into((iv_poster_image))

        rc_checkout.layoutManager = LinearLayoutManager(this)
        datalist.add(Checkout("C1",""))
        datalist.add(Checkout("C2",""))

        rc_checkout.adapter = TiketAdapter(datalist){

        }
    }
}