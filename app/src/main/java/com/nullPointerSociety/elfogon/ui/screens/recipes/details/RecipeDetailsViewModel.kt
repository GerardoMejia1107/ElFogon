package com.nullPointerSociety.elfogon.ui.screens.recipes.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.nullPointerSociety.elfogon.DelFogonApplication
import com.nullPointerSociety.elfogon.data.model.recipes.RecipeApi
import com.nullPointerSociety.elfogon.data.repository.SpooncularRepository

class RecipeDetailsViewModel(
    private val spooncularRepository: SpooncularRepository,
) : ViewModel() {
    val recipes = spooncularRepository.recipes

    fun getRecipeById(id: Int): RecipeApi? {
        return spooncularRepository.getRecipeByIdFetched(id)
    }

    fun getRecipeSavedById(id: Int): RecipeApi? {
        return spooncularRepository.getRecipeSavedByIdFetched(id)
    }


    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =
                    (this[APPLICATION_KEY] as DelFogonApplication)

                RecipeDetailsViewModel(
                    application.appProvider.provideSpooncularRepository(),
                )
            }
        }
    }
}