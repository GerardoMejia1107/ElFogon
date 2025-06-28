package com.nullPointerSociety.elfogon.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
object HomeScreenNav

@Serializable
object SavedRecipesScreenNav

@Serializable
object ProfileScreenNav

@Serializable
object MadeRecipesScreenNav

@Serializable
data class RecipeDetailsScreenNav(val id: String, val requestOrigin: String)

@Serializable
object SearchByFilterScreenNav

@Serializable
object LogInScreenNav

@Serializable
object SignUpScreenNav


