package com.nullPointerSociety.elfogon.ui.screens.client.recipes.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.nullPointerSociety.elfogon.DelFogonApplication
import com.nullPointerSociety.elfogon.data.model.recipes.Recipe
import com.nullPointerSociety.elfogon.data.repository.SpooncularRepository
import com.nullPointerSociety.elfogon.data.repository.SystemRepository

class RecipeDetailsViewModel(
    private val spooncularRepository: SpooncularRepository,
    private val systemRepository: SystemRepository
) : ViewModel() {
    val recipes = spooncularRepository.recipes

    fun getRecipeById(id: String): Recipe? {
        return spooncularRepository.getRecipeByIdFetched(id)
    }

    fun getRecipeSavedById(id: String): Recipe? {
        return spooncularRepository.getRecipeSavedByIdFetched(id)
    }

    fun getSystemRecipeById(id: String): Recipe? {
        return systemRepository.getRecipeById(id)
    }


    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =
                    (this[APPLICATION_KEY] as DelFogonApplication)

                RecipeDetailsViewModel(
                    application.appProvider.provideSpooncularRepository(),
                    application.appProvider.provideSystemRepository()
                )
            }
        }
    }
}