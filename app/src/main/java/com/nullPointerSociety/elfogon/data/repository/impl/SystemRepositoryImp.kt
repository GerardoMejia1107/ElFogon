package com.nullPointerSociety.elfogon.data.repository.impl

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.nullPointerSociety.elfogon.data.model.Tip
import com.nullPointerSociety.elfogon.data.repository.SystemRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.tasks.await

class SystemRepositoryImp(
    private val firestoreService: FirebaseFirestore
) : SystemRepository {
    private val _tips = MutableStateFlow<List<Tip>>(emptyList())
    override val tips: StateFlow<List<Tip>> = _tips

    override suspend fun fetchTips() {
        try {
            val snapshot = firestoreService.collection("tips").get().await()
            val tipList = snapshot.documents.mapNotNull { it.toObject(Tip::class.java) }
            _tips.value = tipList
            Log.d("Show Tips", "${_tips.value}")
        } catch (e: Exception) {
            _tips.value = emptyList()
            Log.d("Show Tips", "${e.message}")
        }
    }
}