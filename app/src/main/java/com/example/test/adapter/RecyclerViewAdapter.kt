package com.example.test.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.test.model.User
import com.example.test.R
import com.squareup.picasso.Picasso

class RecyclerViewAdapter(
  //  private val userList: ArrayList<User>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>(){

    var Users = ArrayList<User>()

    fun setUpdatedData(items : ArrayList<User>) {
        this.Users = items
        notifyDataSetChanged()
    }
    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView),
    View.OnClickListener{
        val avatar = itemView.findViewById<ImageView>(R.id.avatar)
        val firstName = itemView.findViewById<TextView>(R.id.firstName)
        val lastName = itemView.findViewById<TextView>(R.id.lastName)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val position = adapterPosition
            if(position != RecyclerView.NO_POSITION)
            listener.onItemClick(position)
        }

        fun bind(data : User) {
            firstName.text = data.first_name
            lastName.text = data.last_name
            val url  = data.avatar
            Picasso.get()
                .load(url)
                .into(avatar)
        }
    }

    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_list_row, parent, false)

        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return Users.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
      holder.bind(Users[position])

    }



}