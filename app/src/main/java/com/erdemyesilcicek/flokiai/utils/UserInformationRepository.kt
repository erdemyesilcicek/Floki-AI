package com.erdemyesilcicek.flokiai.utils

import com.erdemyesilcicek.flokiai.datas.UserInformationModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class UserInformationRepository(
    //private val db: FirebaseFirestore,
    //private val auth: FirebaseAuth
) {

    private val auth = Firebase.auth
    private val db = Firebase.firestore

    companion object {
        private const val COLLECTION_USER_INFOS = "userInfos"
    }

    suspend fun getUserInformation(): Result<UserInformationModel> {
        val currentUser = auth.currentUser
        return if (currentUser != null) {
            try {
                val document = db.collection(COLLECTION_USER_INFOS)
                    .document(currentUser.uid)
                    .get()
                    .await()

                if (document.exists()) {
                    val userInfo = document.toObject(UserInformationModel::class.java)
                    if (userInfo != null) {
                        Result.success(userInfo)
                    } else {
                        Result.failure(Exception("User data parsing error"))
                    }
                } else {
                    Result.failure(Exception("No user information found"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        } else {
            Result.failure(Exception("User not authenticated"))
        }
    }

    suspend fun saveUserInformation(userInformation: UserInformationModel): Result<Unit> {
        val currentUser = auth.currentUser
        return if (currentUser != null) {
            try {
                db.collection(COLLECTION_USER_INFOS)
                    .document(currentUser.uid)
                    .set(userInformation)
                    .await()
                Result.success(Unit)
            } catch (e: Exception) {
                Result.failure(e)
            }
        } else {
            Result.failure(Exception("User not authenticated"))
        }
    }

    suspend fun clearUserInformation(): Result<Unit> {
        val currentUser = auth.currentUser
        return if (currentUser != null) {
            try {
                db.collection(COLLECTION_USER_INFOS)
                    .document(currentUser.uid)
                    .delete()
                    .await()
                Result.success(Unit)
            } catch (e: Exception) {
                Result.failure(e)
            }
        } else {
            Result.failure(Exception("User not authenticated"))
        }
    }
}
