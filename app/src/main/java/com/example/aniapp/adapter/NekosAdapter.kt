package com.example.aniapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.aniapp.R
import com.example.aniapp.model.NekoBestModels.Url

class NekosAdapter(
    private val listOfNekos: MutableList<Url> = mutableListOf()
): RecyclerView.Adapter<NekosViewHolder>(){

    fun setNekos(nekos : List<Url>){
        listOfNekos.clear()
        listOfNekos.addAll(nekos)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NekosViewHolder {
        LayoutInflater.from(parent.context).inflate(R.layout.nekos_items, parent, false).apply {
            return NekosViewHolder(this)
        }
    }

    override fun onBindViewHolder(holder: NekosViewHolder, position: Int) {
        val neko = listOfNekos[position]
        holder.setInfoToVH(neko)
    }

    override fun getItemCount(): Int = listOfNekos.size

}

class NekosViewHolder(
    itemView : View
): RecyclerView.ViewHolder(itemView){
    val artistName : TextView = itemView.findViewById(R.id.artistName)
    val artistHref : TextView = itemView.findViewById(R.id.artistHref)
    val srcURL : TextView = itemView.findViewById(R.id.srcURL)
    val nekoImage : ImageView = itemView.findViewById(R.id.nekoImage)

    fun setInfoToVH(item : Url){
        artistName.text = item.artistName
        artistHref.text = item.artistHref
        srcURL.text = item.sourceUrl

        Glide.with(itemView)
            .load(item.url)
            .placeholder(R.drawable.glide_ph)
            .error(R.drawable.glide_err)
            .fallback(R.drawable.glide_fallback)
            .override(2000, 2150)
            .fitCenter()
            .into(nekoImage)
    }
}