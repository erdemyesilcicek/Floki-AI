package com.erdemyesilcicek.flokiai.auth

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class AuthViewModel : ViewModel() {
    private val auth = FirebaseAuth.getInstance()

    fun signUp(
        email: String,
        password: String,
        confirmPassword: String,
        onSuccess: (Boolean) -> Unit,
        onError: (String) -> Unit
    ) {
        if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            onError("Please fill all the fields.")
            return
        }
        if (password != confirmPassword) {
            onError("Passwords do not match.")
            return
        }

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    if (auth.currentUser != null) {
                        sendEmailVerification(onSuccess, onError)
                        onSuccess(true)
                    } else {
                        onError("User registration failed.")
                    }
                } else {
                    if (task.exception?.message?.contains("email address is already in use") == true) {
                        onError("This email address is already in use.")
                    } else if (task.exception?.message?.contains("least 6 characters") == true) {
                        onError("Password must be at least 6 characters.")
                    } else {
                        onError(task.exception?.localizedMessage ?: "Unknown error occurred.")
                    }
                }
            }
    }

    fun signIn(
        email: String,
        password: String,
        onSuccess: (Boolean) -> Unit,
        onError: (String) -> Unit
    ) {
        if (email.isEmpty() || password.isEmpty()) {
            onError("Please fill all the fields.")
            return
        }
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    if (user?.isEmailVerified == true) {
                        onSuccess(true)
                    } else {
                        onSuccess(false)
                    }
                } else {
                    onError(task.exception?.localizedMessage ?: "Giriş başarısız.")
                }
            }
    }

    fun signOut(
        onSuccess: (Boolean) -> Unit,
        onError: (String) -> Unit
    ) {
        try {
            auth.signOut()
            onSuccess(true)
        } catch (e: Exception) {
            onError(e.message ?: "Unknown error occurred.")
        }
    }

    fun resetPassword(
        email: String,
        onSuccess: (Boolean) -> Unit,
        onError: (String) -> Unit
    ) {
        if (email.isEmpty()) {
            onError("Please enter your email address.")
            return
        }
        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onSuccess(true)
                } else {
                    onError("Reset password email could not be sent.")
                }
            }
    }

    fun sendEmailVerification(
        onSuccess: (Boolean) -> Unit,
        onError: (String) -> Unit
    ) {
        if (auth.currentUser == null) {
            return
        }
        auth.currentUser?.sendEmailVerification()!!.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                onSuccess(true)
            } else {
                onError(task.exception?.message ?: "Unknown error occurred.")
            }
        }
    }
}