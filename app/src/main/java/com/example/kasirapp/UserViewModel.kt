package com.example.kasirapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application):AndroidViewModel(application) {
    val getUser: LiveData<List<User>>
    val repository : KasirRepo

    init {
        val dao = KasirDb.getDatabase(application).getDaoKsr()
        repository = KasirRepo(dao)
        getUser = repository.user
    }

    fun insertUser(user: User) = viewModelScope.launch(Dispatchers.IO){
        repository.insertUser(user)
    }

    fun deleteUser(user: User) = viewModelScope.launch(Dispatchers.IO){
        repository.deleteUser(user)
    }

    fun updateUser(user: User) = viewModelScope.launch(Dispatchers.IO){
        repository.updateUser(user)
    }

}