package com.nullPointerSociety.elfogon.data

import androidx.room.processor.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.nullPointerSociety.elfogon.data.repository.firebase.auth.AuthRepository
import com.nullPointerSociety.elfogon.data.repository.firebase.auth.AuthRepositoryImplementation
import com.nullPointerSociety.elfogon.data.repository.spooncular.SpooncularRepository
import com.nullPointerSociety.elfogon.data.repository.spooncular.SpooncularRepositoryImplementation

class AppProvider(context: Context) {
    private val firebaseAuthInstance: FirebaseAuth = FirebaseAuth.getInstance()
    private val firebaseFirestoreInstance: FirebaseFirestore = Firebase.firestore
    private val firebaseStorageInstance: FirebaseStorage = FirebaseStorage.getInstance()


    private val authRepository: AuthRepository = AuthRepositoryImplementation()
    private val spooncularRepository: SpooncularRepository = SpooncularRepositoryImplementation()


    fun provideAuthRepository(): AuthRepository {
        return authRepository
    }

    fun provideSpooncularRepository(): SpooncularRepository {
        return spooncularRepository
    }


}