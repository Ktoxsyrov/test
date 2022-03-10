package com.example.test.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager.VERTICAL
import com.example.test.databinding.ActivityMainBinding
import com.example.test.model.UserList
import com.example.test.viewmodel.MainActivityViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.test.R
import com.example.test.adapter.RecyclerViewAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MainActivityViewModel
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter

    interface OnItemClickListener{
        fun onItemClicked(position: Int, view: View)
    }
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        supportActionBar?.hide()

//        val refresh: FloatingActionButton = findViewById(R.id.refresh_fab)
//        refresh.setOnClickListener {
//            Toast.makeText(this@MainActivity, "Refreshing", Toast.LENGTH_SHORT).show()
//            //setupBinding(viewModel)
//        }

        viewModel = makeApiCall()
        setupBinding(viewModel)

    }

    private fun setupBinding(viewModel: MainActivityViewModel) {

        val activityMainBinding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        activityMainBinding.setVariable(com.example.test.BR.viewModel,  viewModel)
        activityMainBinding.executePendingBindings()

        findViewById<RecyclerView>(R.id.recyclerView).addOnItemCLickListener(object: OnItemClickListener{
            override fun onItemClicked(position: Int, view: View) {



               // Toast.makeText(this@MainActivity, "${recyclerViewAdapter.items.size}", Toast.LENGTH_SHORT).show()
                val clickedItem = recyclerViewAdapter.items[position]
                supportFragmentManager.beginTransaction()
                    .replace(findViewById<FragmentContainerView>(R.id.container).id, UserDetailFragment(clickedItem))
                    .commit()


//                findViewById<FrameLayout>(R.id.recyclerView).isClickable = false
            }

        })

        findViewById<RecyclerView>(R.id.recyclerView).apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            val decoration  = DividerItemDecoration(this@MainActivity, VERTICAL)
            addItemDecoration(decoration)
        }

    }

    private fun makeApiCall(): MainActivityViewModel {
        val viewModel =  ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        recyclerViewAdapter = RecyclerViewAdapter()
        viewModel.getRecyclerListDataObserver().observe(this, Observer<UserList>{
            if(it != null) {

                //update the adapter
                viewModel.setAdapterData(it.data)
                recyclerViewAdapter.setDataList(it.data)
               // recyclerViewAdapter.items = it.data
            } else {
                Toast.makeText(this@MainActivity, "Error in fetching data", Toast.LENGTH_LONG).show()
            }
        })

        viewModel.makeAPICall("2")
        return viewModel
    }



    private fun RecyclerView.addOnItemCLickListener(onClickListener: OnItemClickListener){
        this.addOnChildAttachStateChangeListener(object: RecyclerView.OnChildAttachStateChangeListener {
            override fun onChildViewDetachedFromWindow(view: View) {
                view.setOnClickListener(null)
            }
            override fun onChildViewAttachedToWindow(view: View) {
                view.setOnClickListener {
                    val holder = getChildViewHolder(view)
                    onClickListener.onItemClicked(holder.bindingAdapterPosition, view)
                }
            }
        })
    }
}