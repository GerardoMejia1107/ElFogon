package com.nullPointerSociety.elfogon.data

import androidx.room.processor.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.nullPointerSociety.elfogon.data.repository.AuthRepository
import com.nullPointerSociety.elfogon.data.repository.impl.AuthRepositoryImplementation
import com.nullPointerSociety.elfogon.data.repository.SpooncularRepository
import com.nullPointerSociety.elfogon.data.repository.impl.SpooncularRepositoryImpl

class AppProvider(context: Context) {
    private val firebaseAuthInstance: FirebaseAuth = FirebaseAuth.getInstance()
    private val firebaseFirestoreInstance: FirebaseFirestore = Firebase.firestore
    private val firebaseStorageInstance: FirebaseStorage = FirebaseStorage.getInstance()


    private val authRepository: AuthRepository = AuthRepositoryImplementation()
    private val spooncularRepository: SpooncularRepository = SpooncularRepositoryImpl()


    fun provideAuthRepository(): AuthRepository {
        return authRepository
    }

    fun provideSpooncularRepository(): SpooncularRepository {
        return spooncularRepository
    }


}