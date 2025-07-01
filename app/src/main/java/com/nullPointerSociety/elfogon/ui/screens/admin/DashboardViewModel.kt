package com.nullPointerSociety.elfogon.ui.screens.admin

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.nullPointerSociety.elfogon.DelFogonApplication
import com.nullPointerSociety.elfogon.data.repository.AdminRepository
import com.nullPointerSociety.elfogon.ui.screens.home.HomeViewModel
import kotlinx.coroutines.launch

class DashboardViewModel(
    private val adminRepository: AdminRepository
) : ViewModel() {
    val allUsers = adminRepository.allUsers
    init {
        viewModelScope.launch {
            adminRepository.fetchAllUsers()
            adminRepository.allUsers.collect { list ->
                Log.d("Fetched Users", "Users: ${list.size}")
            }
        }

    }

    companion object{
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val app = this[APPLICATION_KEY] as DelFogonApplication
                DashboardViewModel(
                    app.appProvider.provideAdminRepository()
                )
            }
        }
    }


}