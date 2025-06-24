package com.nullPointerSociety.elfogon.ui.screens.recipes.saved

import androidx.lifecycle.ViewModel
import com.nullPointerSociety.elfogon.data.repository.AuthRepository
import com.nullPointerSociety.elfogon.data.repository.SpooncularRepository

class SavedRecipesViewModel(
     private val spooncularRepository: SpooncularRepository,
     private val authRepository: AuthRepository
) : ViewModel() {
}