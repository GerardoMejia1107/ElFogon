package com.nullPointerSociety.elfogon.ui.screens.admin.registrations

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.nullPointerSociety.elfogon.DelFogonApplication
import com.nullPointerSociety.elfogon.data.repository.AdminRepository
import com.nullPointerSociety.elfogon.ui.screens.admin.users.UsersViewModel
import kotlinx.coroutines.launch

class RegistrationsViewModel(
    private val adminRepository: AdminRepository
) : ViewModel() {
    val usersToday = adminRepository.usersToday

    init {
        viewModelScope.launch {
            adminRepository.fetchNewUsersToday()
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val app = this[APPLICATION_KEY] as DelFogonApplication
                RegistrationsViewModel(
                    app.appProvider.provideAdminRepository()
                )
            }
        }
    }
}