package com.example.tubes.lana.Adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.tubes.lana.Model.News
import com.example.tubes.lana.R
import com.squareup.picasso.Picasso


class ListNewsAdapter : BaseAdapter {

    var activity: Activity
    lateinit var data: ArrayList<News>

    constructor(activity: Activity, data: ArrayList<News>) : super() {
        this.activity = activity
        this.data = data
    }


    override fun getItem(position: Int): Any {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return data.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        var convertView = convertView
        var holder: ListNewsViewHolder? = null
        if (convertView == null) {
            holder = ListNewsViewHolder()
            convertView = LayoutInflater.from(activity).inflate(
                R.layout.news_list_row, parent, false
            )
            holder.galleryImage = convertView.findViewById(R.id.galleryImage) as ImageView
            holder.author = convertView.findViewById(R.id.author) as TextView
            holder.title = convertView.findViewById<View>(R.id.title) as TextView
            holder.details = convertView.findViewById(R.id.sdetails) as TextView
            holder.time = convertView.findViewById(R.id.time) as TextView
            convertView.tag = holder
        } else {
            holder = convertView.tag as ListNewsViewHolder
        }
//        holder.galleryImage?.id = position
//        holder.author?.id = position
//        holder.title?.id = position
//        holder.details?.id = position
//        holder.time?.id = position

        var song: News = data[position]

        try {
            holder.author?.text = song.author
            holder.title?.text = if (song.title != null) song.title else "-"
            holder.time?.text = song.publish
            holder.details?.text = song.description

            if (song.URLtoimage.length < 5) {
                holder.galleryImage?.visibility = View.GONE
            } else {
                Picasso.get()
                    .load(song.URLtoimage)
                    .resize(300, 200)
                    .centerCrop()
                    .into(holder.galleryImage)
            }
        } catch (e: Exception) {
        }

        return convertView
    }
}

private class ListNewsViewHolder {
    var galleryImage: ImageView? = null
    var author: TextView? = null
    var title: TextView? = null
    var details: TextView? = null
    var time: TextView? = null
}