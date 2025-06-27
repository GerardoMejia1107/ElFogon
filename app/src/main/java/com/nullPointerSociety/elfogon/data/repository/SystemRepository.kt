package com.nullPointerSociety.elfogon.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.nullPointerSociety.elfogon.data.model.Tip
import kotlinx.coroutines.flow.StateFlow

interface SystemRepository {
    val tips: StateFlow<List<Tip>>

    suspend fun fetchTips()
}
