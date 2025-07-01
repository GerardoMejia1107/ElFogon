package com.nullPointerSociety.elfogon.data.model.user

import com.google.firebase.Timestamp

data class UserReceptor(
    val name: String = "",
    val lastName: String = "",
    val email: String = "",
    val profilePictureUrl: String = "",
    val role: String = "",
    val customSavedRecipes: List<String> = emptyList(),
    val savedRecipes: List<String> = emptyList(),
    val madeRecipes: List<String> = emptyList(),
    val registerDate: Timestamp = Timestamp.now(),
)
