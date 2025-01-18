package com.erdemyesilcicek.flokiai.auth

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

data class AuthResult(val success: Boolean, val message: String)

class AuthRepository(private val firebaseAuth: FirebaseAuth) {

    suspend fun signUp(email: String, password: String, confirmPassword: String): AuthResult {
        if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            return AuthResult(success = false, message = "Please fill all the fields.")
        }
        if (password != confirmPassword) {
            return AuthResult(success = false, message = "Passwords do not match.")
        }
        return try {
            firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            if (firebaseAuth.currentUser != null) {
                sendEmailVerification()
                AuthResult(
                    success = true,
                    message = "User registered successfully, please verify your email address."
                )
            } else {
                AuthResult(
                    success = false,
                    message = "User registration failed."
                )
            }
            //AuthResult(success = true, message = "User registered successfully.")
        } catch (e: Exception) {
            if(e.message?.contains("email address is already in use") == true) {
                return AuthResult(
                    success = false,
                    message = "This email address is already in use."
                )
            }
            if (e.message?.contains("least 6 characters") == true) {
                return AuthResult(
                    success = false,
                    message = "Password must be at least 6 characters."
                )
            }
            AuthResult(
                success = false,
                message = e.message ?: "Unknown error occurred."
            )
        }
    }

    suspend fun signIn(email: String, password: String): AuthResult {
        if (email.isEmpty() || password.isEmpty()) {
            return AuthResult(success = false, message = "Please fill all the fields.")
        }
        return try {
            firebaseAuth.signInWithEmailAndPassword(email, password).await()
            if (firebaseAuth.currentUser != null && isEmailVerified()) {
                return AuthResult(success = true, message = "User signed in successfully.")
            } else {
                return AuthResult(success = false, message = "Please verify your email address.")
            }
            //AuthResult(success = true, message = "User signed in successfully.")
        } catch (e: Exception) {
            AuthResult(success = false, message = e.message ?: "Unknown error occurred.")
        }
    }

    fun signOut(): AuthResult {
        return try {
            firebaseAuth.signOut()
            AuthResult(success = true, message = "User signed out successfully.")
        } catch (e: Exception) {
            AuthResult(success = false, message = e.message ?: "Unknown error occurred.")
        }
    }

    suspend fun sendEmailVerification(): AuthResult {
        return try {
            if (firebaseAuth.currentUser == null) {
                return AuthResult(success = false, message = "")
            }
            firebaseAuth.currentUser?.sendEmailVerification()?.await()
            AuthResult(success = true, message = "Verification email sent.")
        } catch (e: Exception) {
            AuthResult(success = false, message = e.message ?: "Unknown error occurred.")
        }
    }

    fun isEmailVerified(): Boolean {
        return firebaseAuth.currentUser?.isEmailVerified == true
    }
}