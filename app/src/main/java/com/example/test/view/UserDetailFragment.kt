package com.example.test.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.test.R
import com.example.test.databinding.FragmentUserDetailBinding
import com.example.test.model.User
import com.example.test.viewmodel.MainActivityViewModel

class UserDetailFragment(user: User) : Fragment() {

private lateinit var binding: FragmentUserDetailBinding
private lateinit var viewModel: MainActivityViewModel
private val currUser = user
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(requireActivity()).get(MainActivityViewModel::class.java)
        var edited: Boolean = false
        binding = FragmentUserDetailBinding.inflate(layoutInflater)
        binding.firstNameFr.setText(currUser.first_name)
        binding.firstNameFr.doAfterTextChanged {
            edited = true
        }
        binding.lastNameFr.setText(currUser.last_name)
        binding.lastNameFr.doAfterTextChanged {
            edited = true
        }
        binding.userEmail.setText(currUser.email)
        binding.userEmail.doAfterTextChanged {
            edited = true
        }

        binding.deleteUserButton.setOnClickListener {
            viewModel.deleteUser(currUser)
           // view?.let { activity?.dismissKeyboardShortcutsHelper() }
            binding.allUserFrame.isVisible = false
        }


        Glide.with(binding.profilePic)
            .load(currUser.avatar)
            .placeholder(R.drawable.ic_launcher_foreground)
            .error(R.drawable.ic_launcher_foreground)
            .fallback(R.drawable.ic_launcher_foreground)
            .into(binding.profilePic)


        binding.background.setOnClickListener{
            if(edited) {
                val updatedUser = User(
                    currUser.id,
                    binding.userEmail.text.toString(),
                    binding.firstNameFr.text.toString(),
                    binding.lastNameFr.text.toString(),
                    currUser.avatar
                    )
                viewModel.updateUser(updatedUser)
            }
            binding.allUserFrame.isVisible = false
        }
        binding.body.setOnClickListener{

        }

        return binding.root
    }

}