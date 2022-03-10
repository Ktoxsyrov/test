package com.example.test.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.example.test.R
import com.example.test.databinding.FragmentUserDetailBinding
import com.example.test.model.User

class UserDetailFragment(user: User) : Fragment() {

private lateinit var binding: FragmentUserDetailBinding
private val currUser = user
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserDetailBinding.inflate(layoutInflater)
        binding.firstNameLastName.text = "${currUser.first_name} ${currUser.last_name}"
        binding.userEmail.text = currUser.email
        binding.userId.text = "id: " + currUser.id.toString()


        Glide.with(binding.profilePic)
            .load(currUser.avatar)
            .placeholder(R.drawable.ic_launcher_foreground)
            .error(R.drawable.ic_launcher_foreground)
            .fallback(R.drawable.ic_launcher_foreground)
            .into(binding.profilePic)

        binding.userFrame.setOnClickListener{
            binding.allUserFrame.isVisible = false
            //Toast.makeText(context, "click", Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }

}