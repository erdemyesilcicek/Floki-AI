package com.erdemyesilcicek.flokiai.auth

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException

class AuthViewModel : ViewModel() {
    private val auth = FirebaseAuth.getInstance()

    fun signUp(
        email: String,
        password: String,
        confirmPassword: String,
        onSuccess: (Boolean) -> Unit,
        onError: (String) -> Unit
    ) {
        when {
            !validateFields(email, password, confirmPassword, onError) -> return
            password != confirmPassword -> {
                onError("Passwords do not match.")
                return
            }
        }

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    auth.currentUser?.let {
                        sendEmailVerification(onSuccess, onError)
                        onSuccess(true)
                    } ?: onError("User registration failed.")
                } else {
                    handleAuthError(task.exception, onError)
                }
            }
    }

    fun signIn(
        email: String,
        password: String,
        onSuccess: (Boolean) -> Unit,
        onError: (String) -> Unit
    ) {
        if (!validateFields(email, password, onError)) return

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onSuccess(auth.currentUser?.isEmailVerified == true)
                } else {
                    handleAuthError(task.exception, onError)
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
            onError(e.message ?: "An error occurred during sign out.")
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
                    onError("Failed to send password reset email. ${task.exception?.localizedMessage ?: ""}")
                }
            }
    }

    fun sendEmailVerification(
        onSuccess: (Boolean) -> Unit,
        onError: (String) -> Unit
    ) {
        auth.currentUser?.let { user ->
            user.sendEmailVerification().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onSuccess(true)
                } else {
                    onError(task.exception?.message ?: "Failed to send verification email.")
                }
            }
        }
    }

    private fun validateFields(email: String, password: String, onError: (String) -> Unit): Boolean {
        return when {
            email.isEmpty() || password.isEmpty() -> {
                onError("Please fill all the fields.")
                false
            }
            else -> true
        }
    }

    private fun validateFields(email: String, password: String, confirmPassword: String, onError: (String) -> Unit): Boolean {
        return when {
            email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() -> {
                onError("Please fill all the fields.")
                false
            }
            else -> true
        }
    }

    private fun handleAuthError(exception: Exception?, onError: (String) -> Unit) {
        val errorMessage = when {
            exception?.message?.contains("email address is already in use") == true -> 
                "This email address is already in use."
            exception?.message?.contains("least 6 characters") == true -> 
                "Password must be at least 6 characters."
            else -> exception?.localizedMessage ?: "Authentication failed."
        }
        onError(errorMessage)
    }
}