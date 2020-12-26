package com.abrorrahmad.movieapp.home.dasboard

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.abrorrahmad.movieapp.R
import com.abrorrahmad.movieapp.model.Film
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.row_item_now_playing.view.*

class CoomingSoonAdapter(private var data: List<Film>,
                         private var listener:(Film) -> Unit)
    : RecyclerView.Adapter<CoomingSoonAdapter.ViewHolder>() {

    lateinit var contextAdapter : Context


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CoomingSoonAdapter.ViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        contextAdapter = parent.context

        val inflatedView = layoutInflater.inflate(R.layout.row_item_cooming_soon, parent, false)
        return ViewHolder(inflatedView)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: CoomingSoonAdapter.ViewHolder, position: Int) {
        holder.bindItem(data[position], listener, contextAdapter)
    }



    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val tvTitle:TextView = view.findViewById(R.id.tv_kursi)
        private val tvGenre:TextView = view.findViewById(R.id.tv_genre)
        private val tvRate:TextView = view.findViewById(R.id.tv_rate)

        private val tvImage:ImageView = view.findViewById(R.id.iv_poster_image)

        fun bindItem(data:Film, listener: (Film) -> Unit, context: Context){
            tvTitle.setText(data.judul)
            tvGenre.setText(data.genre)
            tvRate.setText(data.rating)

            Glide.with(context)
                .load(data.poster)
                .into(tvImage)


            itemView.setOnClickListener {
                listener(data)
            }
        }

    }

}
