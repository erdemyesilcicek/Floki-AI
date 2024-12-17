package com.erdemyesilcicek.flokiai.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class AuthViewModel : ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    val loginState = mutableStateOf<Boolean?>(null)
    val errorMessage = mutableStateOf("")

    fun login(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    loginState.value = true
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
                    println("REGISTER SUCCESS")
                } else {
                    loginState.value = false
                    errorMessage.value = task.exception?.message ?: "Registration failed"
                }
            }
    }

    fun logout() {
        auth.signOut()
        loginState.value = null
    }
}
