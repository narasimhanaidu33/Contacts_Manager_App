package com.example.contactsmanagerapp.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.contactsmanagerapp.room.UserRepository

//class UserViewModelFactory(private val repository: UserRepository): ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        return super.create(modelClass)
//        if (modelClass.isAssignableFrom(UserViewModel::class.java)){
//            return UserViewModel(repository) as T
//        }
//        throw illegalDecoyCallException("Unknown View Model Class")
//    }
//}
//class
//
//UserViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
//
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        val extras = null as CreationExtras
//        return modelClass.getConstructor(Application::class.java, extras::class.java).newInstance(application, extras)
//    }
//}
class UserViewModelFactory(private val repository: UserRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel(repository) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}