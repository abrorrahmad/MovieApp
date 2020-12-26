package com.abrorrahmad.movieapp.checout

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.abrorrahmad.movieapp.R
import com.abrorrahmad.movieapp.model.Checkout
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.row_item_now_playing.view.*
import java.text.NumberFormat
import java.util.*

class CheckoutAdapter(private var data: List<Checkout>,
                      private var listener:(Checkout) -> Unit)
    : RecyclerView.Adapter<CheckoutAdapter.ViewHolder>() {

    lateinit var contextAdapter : Context


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CheckoutAdapter.ViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        contextAdapter = parent.context

        val inflatedView = layoutInflater.inflate(R.layout.row_item_checkout, parent, false)
        return ViewHolder(inflatedView)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: CheckoutAdapter.ViewHolder, position: Int) {
        holder.bindItem(data[position], listener, contextAdapter)
    }



    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val tvTitle:TextView = view.findViewById(R.id.tv_kursi)
        private val tvharga:TextView = view.findViewById(R.id.tv_harga)


        fun bindItem(data:Checkout, listener: (Checkout) -> Unit, context: Context){

            val localID = Locale("id","ID")
            val formatRupiah = NumberFormat.getCurrencyInstance(localID)
            tvharga.setText(formatRupiah.format(data.harga!!.toDouble()))

            if (data.kursi!!.startsWith("Total")){

                tvTitle.setText(data.kursi)
                tvTitle.setCompoundDrawables(null, null,null,null)

            }else{
                tvTitle.setText("Seat NO. "+data.kursi)

            }

            itemView.setOnClickListener {
                listener(data)
            }
        }

    }

}
