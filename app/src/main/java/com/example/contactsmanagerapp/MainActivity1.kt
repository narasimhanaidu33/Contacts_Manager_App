package com.example.contactsmanagerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contactsmanagerapp.viewModel.UserViewModel
import com.example.contactsmanagerapp.viewModel.UserViewModelFactory
import com.example.contactsmanagerapp.databinding.ActivityMain1Binding
import com.example.contactsmanagerapp.room.User
import com.example.contactsmanagerapp.room.UserDatabase
import com.example.contactsmanagerapp.room.UserRepository
import com.example.contactsmanagerapp.viewUI.MyRecyclerViewAdapter

class MainActivity1 : AppCompatActivity() {
    lateinit var binding: ActivityMain1Binding
    lateinit var userViewModel: UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main1)
        //room database
        val dao = UserDatabase.getInstance(application).userDao
        val repository = UserRepository(dao)
        val factory = UserViewModelFactory(repository)

        userViewModel = ViewModelProvider(this,factory).get(UserViewModel::class.java)
        binding.userViewModel = userViewModel
        binding.lifecycleOwner = this
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.recylerView.layoutManager = LinearLayoutManager(this)
        displayUsersList()
    }


private fun displayUsersList() {
    userViewModel.users.observe(this, Observer {
        binding.recylerView.adapter = MyRecyclerViewAdapter(it) { selectedItem: User ->
            listItemClicked(
                selectedItem
            )
        }
    })
}

    private fun listItemClicked(selectedItem: User) {
        Toast.makeText(this, "Selected name is ${selectedItem.name}", Toast.LENGTH_LONG).show()
        userViewModel.initUpdateAndDelete(selectedItem)
    }
}

//    private fun displayUsersList() {
//        userViewModel.users.observe(this, Observer {
//            binding.recylerView.adapter = MyRecyclerViewAdapter(it) { selectedItem: User ->
//                listItemClicked(
//                    selectedItem
//                )
//            }
//
//        })
//    }
//
//    private fun listItemClicked(selectedItem: User) {
//        Toast.makeText(this,"Selected name is ${selectedItem.name}",Toast.LENGTH_LONG).show()
//        userViewModel.initUpdateAndDelete(selectedItem)
//    }