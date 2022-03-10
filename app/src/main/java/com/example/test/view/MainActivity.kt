package com.example.test.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager.VERTICAL
import com.example.test.R
import com.example.test.databinding.ActivityMainBinding
import com.example.test.model.UserList
import com.example.test.viewmodel.MainActivityViewModel
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    interface OnItemClickListener{
        fun onItemClicked(position: Int, view: View)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()
        val viewModel = makeApiCall()

        setupBinding(viewModel)



        findViewById<FloatingActionButton>(R.id.rafreshFAB).setOnClickListener {
            setupBinding(viewModel)
        }
    }

    private fun setupBinding(viewModel: MainActivityViewModel) {

        val activityMainBinding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        activityMainBinding.setVariable(com.example.test.BR.viewModel,  viewModel)
        activityMainBinding.executePendingBindings()

        findViewById<RecyclerView>(R.id.recyclerView).addOnItemCLickListener(object: OnItemClickListener{
            override fun onItemClicked(position: Int, view: View) {



                Toast.makeText(this@MainActivity, "$position", Toast.LENGTH_SHORT).show()



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

        viewModel.getRecyclerListDataObserver().observe(this, Observer<UserList>{
            if(it != null) {

                //update the adapter
                viewModel.setAdapterData(it.data)
            } else {
                Toast.makeText(this@MainActivity, "Error in fetching data", Toast.LENGTH_LONG).show()
            }
        })

        viewModel.makeAPICall("2")
        return viewModel
    }



    fun RecyclerView.addOnItemCLickListener(onClickListener: OnItemClickListener){
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