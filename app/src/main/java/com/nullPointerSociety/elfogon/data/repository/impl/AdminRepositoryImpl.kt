package com.nullPointerSociety.elfogon.data.repository.impl

import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import com.nullPointerSociety.elfogon.data.model.user.UserReceptor
import com.nullPointerSociety.elfogon.data.repository.AdminRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.tasks.await
import java.util.Calendar

class AdminRepositoryImpl(
    private val firestoreService: FirebaseFirestore
) : AdminRepository {
    private val _allUsers = MutableStateFlow<List<UserReceptor>>(emptyList())
    override val allUsers: StateFlow<List<UserReceptor>> = _allUsers

    //This functions helps to obtain all the users from the Firestore database collection "users"
    suspend fun fetchAllUsers(){
        // I get all the users from the Firestore collection "users"
        val snapshot = firestoreService.collection("users").get().await()
        // I map the documents to UserReceptor objects, filtering out any that do not exist
        val users = snapshot.documents.mapNotNull { doc ->
            if (doc.exists()) {
                // For each document, I model it to a UserReceptor object
                UserReceptor(
                    id = doc.id,
                    email = doc.getString("email") ?: "",
                    name = doc.getString("name") ?: "",
                    lastName = doc.getString("lastName") ?: "",
                    profilePictureUrl = doc.getString("profilePictureUrl") ?: "",
                    role = doc.getString("role") ?: "user",
                    customSavedRecipes = doc.get("customSavedRecipes") as? List<String> ?: emptyList(),
                    savedRecipes = doc.get("savedRecipes") as? List<String> ?: emptyList(),
                    madeRecipes = doc.get("madeRecipes") as? List<String> ?: emptyList(),
                    registerDate = doc.getTimestamp("registerDate") ?: Timestamp.now()
                )
            } else null
        }
        // I update the _allUsers state flow with the list of users
        _allUsers.value = users
    }

    // Since I already have all the users fetched, I simply can pass and ID and return the user with that ID
    override suspend fun getUserById(uid: String): UserReceptor? {
       return _allUsers.value.find { it.id == uid }
    }

    // This function deletes a user by their ID
    override suspend fun deleteUser(uid: String) {
        //I find the user in the _allUsers state flow
        val user = _allUsers.value.find { it.id == uid }
        if (user != null) {
            //I filter the user with its uid and delete it from the Firestore collection "users"
            firestoreService.collection("users").document(uid).delete().await()
            //I update the _allUsers state flow by removing the user with the given uid
            _allUsers.value = _allUsers.value.filter { it.id != uid }
        } else {
            throw Exception("User not found")
        }
    }

    // This function returns the total number of users in the _allUsers state flow
    override suspend fun getUserCount(): Int {
        //I just return the size of the _allUsers state flow
        return _allUsers.value.size
    }

    // This function returns the number of users who registered today
    override suspend fun getNewUsersToday(): Int {
        // I create a Calendar instance to get the start and end of the current day
        val calendar = Calendar.getInstance()

        //Start of the day (All to 0 hour, minutes, seconds and milliseconds)
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        val startOfDay = Timestamp(calendar.time)

        //End of the day (All to 23 hour, 59 minutes, 59 seconds and 999 milliseconds)
        calendar.set(Calendar.HOUR_OF_DAY, 23)
        calendar.set(Calendar.MINUTE, 59)
        calendar.set(Calendar.SECOND, 59)
        calendar.set(Calendar.MILLISECOND, 999)
        val endOfDay = Timestamp(calendar.time)

        // I query the Firestore collection "users" for documents where the registerDate is between startOfDay and endOfDay
        val snapshot = firestoreService.collection("users")
            .whereGreaterThanOrEqualTo("registerDate", startOfDay)
            .whereLessThanOrEqualTo("registerDate", endOfDay)
            .get()
            .await()

        // I return the size of the snapshot, which represents the number of users who registered today
        return snapshot.size()
    }

    override suspend fun getTopSavedRecipe(): Pair<String, Int>? {
        TODO("Not yet implemented")
    }


}