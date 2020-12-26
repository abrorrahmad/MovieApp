package com.abrorrahmad.movieapp.home.tiket

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.abrorrahmad.movieapp.R
import com.abrorrahmad.movieapp.home.dasboard.CoomingSoonAdapter
import com.abrorrahmad.movieapp.model.Film
import com.abrorrahmad.movieapp.utils.Preferences
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_tiket.*


class TiketFragment : Fragment() {

    private lateinit var preferences: Preferences
    private lateinit var mDatabase : DatabaseReference
    private var datalist = ArrayList<Film>()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tiket, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        preferences = Preferences(context!!)
        mDatabase = FirebaseDatabase.getInstance().getReference("Film")

        rc_tiket.layoutManager = LinearLayoutManager(context)
        getData()
    }

    private fun getData() {
        mDatabase.addValueEventListener(object : ValueEventListener{

            override fun onCancelled(p0: DatabaseError) {
                Toast.makeText(context, ""+p0.message, Toast.LENGTH_LONG).show()
            }

            override fun onDataChange(p0: DataSnapshot) {
                datalist.clear()
                for (getdataSpanshot in p0.children){
                    val film = getdataSpanshot.getValue(Film::class.java)
                    datalist.add(film!!)
                }

                rc_tiket.adapter = CoomingSoonAdapter(datalist){
                    var intent = Intent(context,TiketActivity::class.java).putExtra("data",it)
                    startActivity(intent)

                }

                tv_total.setText("${datalist.size} Movies")
            }


        })
    }


}