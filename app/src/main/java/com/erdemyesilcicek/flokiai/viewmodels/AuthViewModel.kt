package com.erdemyesilcicek.flokiai.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AuthViewModel : ViewModel() {
    val auth = Firebase.auth

    val loginState = mutableStateOf<Boolean?>(false)
    val errorMessage = mutableStateOf("")
    val currentUser = mutableStateOf(auth.currentUser)

    fun login(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    if (checkIfEmailVerified()) {
                        loginState.value = true
                        //currentUser.value = auth.currentUser
                    } else {
                        errorMessage.value = "E-posta doğrulaması gerekli!"
                        loginState.value = false
                        auth.signOut()
                    }
                } else {
                    loginState.value = false
                    errorMessage.value = task.exception?.message ?: "Login failed"
                }
            }
    }

    private fun checkIfEmailVerified(): Boolean {
        val user = auth.currentUser
        if (user != null) {
            if (user.isEmailVerified) {
                println("E-posta doğrulandı!")
                errorMessage.value = "E-posta doğrulandı!"
                return true
            } else {
                return false
                errorMessage.value = "E-posta henüz doğrulanmadı!"
            }
        }
        return false
    }

    fun resendVerificationEmail() {
        val user = auth.currentUser
        user?.sendEmailVerification()
            ?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    println("Doğrulama e-postası tekrar gönderildi.")
                } else {
                    task.exception?.message?.let { errorMessage ->
                        println("Error: $errorMessage")
                    }
                }
            }
    }

    fun register(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    loginState.value = false
                    currentUser.value = null
                    sendEmailVerification()
                } else {
                    println(task.exception?.message)
                    loginState.value = false
                    errorMessage.value = task.exception?.message ?: "Registration failed"
                }
            }
    }

    private fun sendEmailVerification() {
        val user = auth.currentUser
        user?.sendEmailVerification()
            ?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    println("Doğrulama e-postası gönderildi!")
                } else {
                    task.exception?.message?.let { errorMessage ->
                        println("Error: $errorMessage")
                    }
                }
            }
    }

    fun logout() {
        auth.signOut()
        loginState.value = null
        currentUser.value = null
    }
}
