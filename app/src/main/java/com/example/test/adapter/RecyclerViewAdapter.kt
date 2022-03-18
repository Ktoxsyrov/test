package com.example.test.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.test.R
import com.example.test.databinding.RecyclerListRowBinding
import com.example.test.model.User
import kotlinx.android.synthetic.main.recycler_list_row.view.*

class RecyclerViewAdapter(): RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    private var listData: List<User>? = null
    fun setListData(listData: List<User>?) {
        this.listData = listData
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RecyclerListRowBinding.inflate(layoutInflater)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(listData?.get(position)!!)
    }

    override fun getItemCount(): Int {
        if(listData == null )return 0
        return listData?.size!!
    }

    class MyViewHolder(private val binding: RecyclerListRowBinding): RecyclerView.ViewHolder(binding.root) {


        fun bind(data: User) {
            binding.userData = data

//            Glide.with(binding.avatar)
//                .load(data.avatar)
//                .into(binding.avatar)
        }

    }
    companion object{
        @JvmStatic
        @BindingAdapter("loadImage")
        fun loadImage(avatar: ImageView, url: String) {
            Glide.with(avatar)
                .load(url)
                .circleCrop()
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
                .fallback(R.drawable.ic_launcher_foreground)
                .into(avatar)
        }
    }
}