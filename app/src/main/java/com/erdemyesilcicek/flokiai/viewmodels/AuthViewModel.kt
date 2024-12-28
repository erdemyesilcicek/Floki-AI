package com.erdemyesilcicek.flokiai.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AuthViewModel : ViewModel() {
    val auth = Firebase.auth

    val loginState = mutableStateOf<Boolean?>(null)
    val errorMessage = mutableStateOf("")
    val currentUser = mutableStateOf(auth.currentUser)

    fun login(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    loginState.value = true
                    currentUser.value = auth.currentUser
                } else {
                    loginState.value = false
                    errorMessage.value = task.exception?.message ?: "Login failed"
                }
            }
    }

    fun register(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    loginState.value = true
                    currentUser.value = auth.currentUser
                } else {
                    println(task.exception?.message)
                    loginState.value = false
                    errorMessage.value = task.exception?.message ?: "Registration failed"
                }
            }
    }

    fun logout() {
        auth.signOut()
        loginState.value = null
        currentUser.value = null
    }
}
