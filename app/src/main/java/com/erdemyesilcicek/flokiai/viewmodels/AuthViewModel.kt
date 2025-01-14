package com.erdemyesilcicek.flokiai.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class AuthViewModel : ViewModel() {

    // Firebase Auth instance
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    // UI State
    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState: StateFlow<AuthState> = _authState

    private val _errorMessage = MutableStateFlow("")
    val errorMessage: StateFlow<String> get() = _errorMessage

    val currentUser = mutableStateOf(auth.currentUser)


    fun updateErrorMessage(message: String) {
        _errorMessage.value = message
    }

    private val _isUserSignedUp = MutableStateFlow(false)
    val isUserSignedUp: StateFlow<Boolean> get() = _isUserSignedUp

    fun setUserSignedUp(isSignedUp: Boolean) {
        _isUserSignedUp.value = isSignedUp
    }


    // Sign Up
    fun signUp(email: String, password: String, confirmPassword: String) {
        if (password != confirmPassword) {
            _authState.value = AuthState.Error("Passwords do not match")
            return
        }
        setUserSignedUp(true)
        updateErrorMessage("")

        viewModelScope.launch {
            _authState.value = AuthState.Loading
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Send verification email
                    auth.currentUser?.sendEmailVerification()?.addOnCompleteListener { emailTask ->
                        if (emailTask.isSuccessful) {
                            _authState.value = AuthState.Success("Verification email sent")
                        } else {
                            _authState.value = AuthState.Error(emailTask.exception?.message ?: "Failed to send verification email")
                        }
                    }
                } else {
                    _authState.value = AuthState.Error(task.exception?.message ?: "Sign Up failed")
                }
            }
        }
    }

    // Sign In
    fun signIn(email: String, password: String) {

        viewModelScope.launch {
            _authState.value = AuthState.Loading
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    if (user != null && user.isEmailVerified) {
                        _authState.value = AuthState.Success("Welcome back!")
                        println("Welcome back!")
                    } else {
                        _authState.value = AuthState.Error("Please verify your email")
                        println("Please verify your email")
                    }
                } else {
                    _authState.value = AuthState.Error(task.exception?.message ?: "Sign In failed")
                    updateErrorMessage("Invalid email or password.")
                    println("Sign In failed")
                }
            }
        }
    }

    // Resend Verification Email
    fun resendVerificationEmail(value: String) {
        val user: FirebaseUser? = auth.currentUser
        if (user != null) {
            viewModelScope.launch {
                _authState.value = AuthState.Loading
                user.sendEmailVerification().addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        _authState.value = AuthState.Success("Verification email resent")
                        println("Verification email resent")
                    } else {
                        _authState.value = AuthState.Error(task.exception?.message ?: "Failed to resend verification email")
                        println("Failed to resend verification email")
                    }
                }
            }
        } else {
            _authState.value = AuthState.Error("No user is signed in")
            println("No user is signed in")
        }
    }

    // Sign Out
    fun signOut() {
        auth.signOut()
        _authState.value = AuthState.Idle
    }

    // UI State Management
    sealed class AuthState {
        data object Idle : AuthState()
        data object Loading : AuthState()
        data class Success(val message: String) : AuthState()
        data class Error(val message: String) : AuthState()
    }
}



/*
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

 */
