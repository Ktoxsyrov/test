package com.example.test.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test.R
import com.example.test.adapter.RecyclerViewAdapter
import com.example.test.databinding.FragmentRecyclerListBinding
import com.example.test.model.User
import com.example.test.model.UserList
import com.example.test.viewmodel.MainActivityViewModel

class RecyclerListFragment : Fragment(), RecyclerViewAdapter.OnItemClickListener {

    private lateinit var recyclerAdapter : RecyclerViewAdapter
    private lateinit var binding: FragmentRecyclerListBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view =  inflater.inflate(R.layout.fragment_recycler_list, container, false)

        Toast.makeText(context, "sdfsd", Toast.LENGTH_SHORT).show()


        initViewModel(view)
        initViewModel()
        return view

    }

    private fun initViewModel(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        val decoration  = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(decoration)

        recyclerAdapter = RecyclerViewAdapter(this)
        recyclerView.adapter = recyclerAdapter


    }

    private fun initViewModel() {
        val viewModel  = ViewModelProvider(this)[MainActivityViewModel::class.java]
        viewModel.getRecyclerListObserver().observe(viewLifecycleOwner, Observer<UserList> {
            if(it != null) {
                recyclerAdapter.setUpdatedData(it.data)
            } else {
                Toast.makeText(activity, "Произошла ошибка", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.makeApiCall()
    }
    companion object {

        @JvmStatic
        fun newInstance() =
                RecyclerListFragment()
    }


    override fun onItemClick(position: Int) {
        Toast.makeText(context, "$position", Toast.LENGTH_SHORT).show()
        val clickedItem: User = recyclerAdapter.Users[position]

    }


}