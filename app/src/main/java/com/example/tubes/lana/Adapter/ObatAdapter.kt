package com.example.tubes.lana.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tubes.lana.Model.Obat
import java.text.NumberFormat
import java.util.*



class ObatAdapter (val context: Context, val listobat: List<Obat>, val itemClick: (Obat) -> Unit) : RecyclerView.Adapter<ObatAdapter.Holder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ObatAdapter.Holder {
        val view = LayoutInflater.from(context)
            .inflate(com.example.tubes.lana.R.layout.obat_list_row, parent, false)
        return Holder(view, itemClick)
    }

    override fun getItemCount(): Int {
        return this.listobat.count()
    }

    override fun onBindViewHolder(holder: ObatAdapter.Holder, position: Int) {
        holder?.bindCategory(this.listobat[position], context)
    }

    inner class Holder(itemView: View?, val itemClick: (Obat) -> Unit) : RecyclerView.ViewHolder(itemView!!) {
        val image = itemView?.findViewById<ImageView>(com.example.tubes.lana.R.id.obatimg)
        val nama = itemView?.findViewById<TextView>(com.example.tubes.lana.R.id.obatName)
        val harga = itemView?.findViewById<TextView>(com.example.tubes.lana.R.id.obatharga)

        fun bindCategory(obat: Obat, context: Context) {
            val resourceId = context.resources.getIdentifier(obat.nama,
                "drawable", context.packageName)
            this.image?.setImageResource(resourceId)
            this.nama?.text = obat.nama

            val format = NumberFormat.getCurrencyInstance()
            format.setMaximumFractionDigits(0)
            format.setCurrency(Currency.getInstance("IDR"))
            this.harga?.text = format.format(obat.harga.toInt())
            itemView.setOnClickListener { itemClick(obat) }
        }
    }
}