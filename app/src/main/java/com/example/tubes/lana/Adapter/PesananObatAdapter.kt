package com.example.tubes.lana.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.tubes.lana.Model.PesananObat
import com.example.tubes.lana.R
import java.text.NumberFormat
import java.util.*

class PesananObatAdapter(val context: Context, val pesanan: List<PesananObat>) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val categoryView: View
        val holder: ViewHolder

        if (convertView == null) {
            categoryView = LayoutInflater.from(context).inflate(R.layout.pesanan_obat_list, null)
            holder = ViewHolder()
            holder.namaObat = categoryView.findViewById(R.id.txtnamaobat)
            holder.totalHarga = categoryView.findViewById(R.id.txthargatotal)
            categoryView.tag = holder
        } else {
            holder = convertView.tag as ViewHolder
            categoryView = convertView
        }

        val data = pesanan[position]
        if (data.jumlah != null) holder.namaObat?.text = "${data.nama} X ${data.jumlah}"
        else holder.namaObat?.text = data.nama

        val format = NumberFormat.getCurrencyInstance()
        format.setMaximumFractionDigits(0)
        format.setCurrency(Currency.getInstance("IDR"))
        holder.totalHarga?.text = format.format(data.totalharga)
        return categoryView
    }

    override fun getItem(position: Int): Any {
        return pesanan[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return pesanan.count()
    }

    private class ViewHolder {
        var namaObat: TextView? = null
        var totalHarga: TextView? = null
    }
}