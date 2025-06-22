package com.nullPointerSociety.elfogon.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.nullPointerSociety.elfogon.DelFogonApplication
import com.nullPointerSociety.elfogon.data.repository.AuthRepository
import com.nullPointerSociety.elfogon.data.repository.SpooncularRepository
import com.nullPointerSociety.elfogon.data.repository.UserRepository
import com.nullPointerSociety.elfogon.data.repository.impl.AuthRepositoryImplementation
import com.nullPointerSociety.elfogon.data.repository.impl.SpooncularRepositoryImpl
import com.nullPointerSociety.elfogon.data.repository.impl.UserRepositoryImpl

class AppProvider(context: DelFogonApplication) {
    private val firebaseAuthInstance: FirebaseAuth = FirebaseAuth.getInstance()
    private val firebaseFirestoreInstance: FirebaseFirestore = Firebase.firestore
    private val firebaseStorageInstance: FirebaseStorage = FirebaseStorage.getInstance()


    private val userRepository: UserRepository = UserRepositoryImpl(firebaseFirestoreInstance)
    private val authRepository: AuthRepository =
        AuthRepositoryImplementation(firebaseAuthInstance, userRepository)


    private val spooncularRepository: SpooncularRepository = SpooncularRepositoryImpl()


    fun provideAuthRepository(): AuthRepository {
        return authRepository
    }

    fun provideUserRepository(): UserRepository {
        return userRepository
    }

    fun provideSpooncularRepository(): SpooncularRepository {
        return spooncularRepository
    }


}