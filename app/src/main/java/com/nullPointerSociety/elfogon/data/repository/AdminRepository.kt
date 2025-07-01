package com.nullPointerSociety.elfogon.data.repository

import kotlinx.coroutines.flow.StateFlow

interface AdminRepository {

    suspend fun getAllUsers(): StateFlow<List<String>>

}