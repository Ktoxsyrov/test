package com.example.test.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.test.model.RecyclerData
import com.example.test.R
import com.squareup.picasso.Picasso

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>(){

    var items = ArrayList<RecyclerData>()

    fun setUpdatedData(items : ArrayList<RecyclerData>) {
        this.items = items
        notifyDataSetChanged()
    }
    class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val avatar = view.findViewById<ImageView>(R.id.avatar)
        val firstName = view.findViewById<TextView>(R.id.firstName)
        val lastName = view.findViewById<TextView>(R.id.lastName)

        fun bind(data : RecyclerData) {
            firstName.text = data.first_name
            lastName.text = data.last_name

            val url  = data.avatar

            Picasso.get()
                .load(url)
                .into(avatar)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_list_row, parent, false)

        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
      holder.bind(items[position])
    }
}