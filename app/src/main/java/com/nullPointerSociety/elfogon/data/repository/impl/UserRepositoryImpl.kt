package com.nullPointerSociety.elfogon.data.repository.impl

import com.google.firebase.firestore.FirebaseFirestore
import com.nullPointerSociety.elfogon.data.model.UserData
import com.nullPointerSociety.elfogon.data.repository.UserRepository
import kotlinx.coroutines.tasks.await

class UserRepositoryImpl(
    private val firestoreService: FirebaseFirestore
) : UserRepository {
    override suspend fun getUserData(uid: String): UserData? {
        val snapshot = firestoreService.collection("users").document(uid).get().await()
        return if (snapshot.exists()) {
            UserData(
                email = snapshot.getString("email") ?: "",
                name = snapshot.getString("name") ?: "",
                lastName = snapshot.getString("lastName") ?: "",
                profilePictureUrl = snapshot.getString("profilePictureUrl") ?: ""
            )
        } else null
    }

    override suspend fun saveUserData(
        uid: String,
        userData: UserData
    ) {
        val doc = mapOf(
            "email" to userData.email,
            "name" to userData.name,
            "lastName" to userData.lastName,
            "profilePictureUrl" to userData.profilePictureUrl
        )
        firestoreService.collection("users").document(uid).set(doc).await()
    }
}