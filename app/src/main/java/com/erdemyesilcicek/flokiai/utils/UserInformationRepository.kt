package com.erdemyesilcicek.flokiai.utils

import com.erdemyesilcicek.flokiai.datas.UserInformationModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class UserInformationRepository {
    private val auth = Firebase.auth
    private val db = Firebase.firestore

    companion object {
        private const val COLLECTION_USER_INFOS = "userInfos"
    }

    suspend fun getUserInformation(): Result<UserInformationModel> {
        val currentUser = auth.currentUser ?: return Result.failure(Exception("User not authenticated"))
        
        return try {
            val document = db.collection(COLLECTION_USER_INFOS)
                .document(currentUser.uid)
                .get()
                .await()

            if (document.exists()) {
                document.toObject(UserInformationModel::class.java)
                    ?.let { Result.success(it) }
                    ?: Result.failure(Exception("User data parsing error"))
            } else {
                Result.failure(Exception("No user information found"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    suspend fun saveUserInformation(userInformation: UserInformationModel): Result<Unit> {
        val currentUser = auth.currentUser ?: return Result.failure(Exception("User not authenticated"))
        
        return try {
            db.collection(COLLECTION_USER_INFOS)
                .document(currentUser.uid)
                .set(userInformation)
                .await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun clearUserInformation(): Result<Unit> {
        val currentUser = auth.currentUser ?: return Result.failure(Exception("User not authenticated"))

        return try {
            db.collection(COLLECTION_USER_INFOS)
                .document(currentUser.uid)
                .delete()
                .await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
