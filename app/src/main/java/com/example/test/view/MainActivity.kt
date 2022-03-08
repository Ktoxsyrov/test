package com.example.test.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.room.Room
import com.example.test.R
import com.example.test.databinding.ActivityMainBinding
//import com.example.test.model.UserDatabase

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rafreshFAB.setOnClickListener{
            setupFragment()
            Toast.makeText(this, "Refreshing", Toast.LENGTH_SHORT).show()
        }


//        val db = Room.databaseBuilder(
//            applicationContext,
//            UserDatabase::class.java,
//            "users_database"
//        ).build()

    }

    private fun setupFragment() {

        val fragment  = RecyclerListFragment.newInstance()
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction : FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(android.R.id.content, fragment)
        fragmentTransaction.commit()
    }
}

















