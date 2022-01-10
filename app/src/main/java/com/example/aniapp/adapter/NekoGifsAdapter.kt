package com.example.aniapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.aniapp.R
import com.example.aniapp.model.NekoBestModels.UrlGif

class NekoGifsAdapter(
    private val listOfNekoGifs: MutableList<UrlGif> = mutableListOf()
): RecyclerView.Adapter<NekoGifsViewHolder>(){

    fun setNekosGif(nekoGifs : List<UrlGif>){
        listOfNekoGifs.clear()
        listOfNekoGifs.addAll(nekoGifs)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NekoGifsViewHolder {
        LayoutInflater.from(parent.context).inflate(R.layout.nekogif_item, parent, false).apply {
            return NekoGifsViewHolder(this)
        }
    }

    override fun onBindViewHolder(holder: NekoGifsViewHolder, position: Int) {
        val nekoGif = listOfNekoGifs[position]
        holder.setInfoToVH(nekoGif)
    }

    override fun getItemCount(): Int = listOfNekoGifs.size

}

class NekoGifsViewHolder(
    itemView : View
): RecyclerView.ViewHolder(itemView){
    val animeName : TextView = itemView.findViewById(R.id.animeName)
    val nekoGif : ImageView = itemView.findViewById(R.id.nekoGif)

    fun setInfoToVH(item : UrlGif){
        animeName.text = item.animeName
        Glide.with(itemView)
            .load(item.url)
            .placeholder(R.drawable.glide_ph)
            .error(R.drawable.glide_err)
            .fallback(R.drawable.glide_fallback)
            .override(2000, 2150)
            .fitCenter()
            .into(nekoGif)
    }
}