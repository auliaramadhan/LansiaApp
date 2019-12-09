package com.example.tubes.lana.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.tubes.lana.Model.Reminder
import com.example.tubes.lana.R
import java.text.NumberFormat
import java.util.*

class ReminderAdapter(val context: Context, val pesanan: List<Reminder>) : BaseAdapter(){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val holderView: View
        val holder : ViewHolder

        if (convertView == null) {
            holderView = LayoutInflater.from(context).inflate(R.layout.reminder_list_view, null)
            holder = ViewHolder()
            holder.nama = holderView.findViewById(R.id.txtreminder)
            holder.waktu = holderView.findViewById(R.id.txtwaktu)
            holder.keterangan = holderView.findViewById(R.id.txtketerangan)
            holderView.tag = holder
        } else {
            holder = convertView.tag as ViewHolder
            holderView = convertView
        }

        val data = pesanan[position]
        holder.nama?.text = "${data.nama}"
        holder.keterangan?.text = data.keterangan
        holder.waktu?.text = data.waktu

        return holderView
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
        var nama: TextView? = null
        var waktu: TextView? = null
        var keterangan : TextView? = null

    }
}