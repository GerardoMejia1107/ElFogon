package com.nullPointerSociety.elfogon.ui.screens.admin.dashboard

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.nullPointerSociety.elfogon.DelFogonApplication
import com.nullPointerSociety.elfogon.data.repository.AdminRepository
import kotlinx.coroutines.launch

class DashboardViewModel(
    private val adminRepository: AdminRepository
) : ViewModel() {
    val allUsers = adminRepository.allUsers
    val usersToday = adminRepository.usersToday

    init {
        viewModelScope.launch {
            adminRepository.fetchAllUsers()
            adminRepository.fetchNewUsersToday()
        }
    }

    companion object {
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