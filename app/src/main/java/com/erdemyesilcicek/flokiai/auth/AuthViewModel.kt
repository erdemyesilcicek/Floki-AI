package com.erdemyesilcicek.flokiai.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel(private val authRepository: AuthRepository) : ViewModel() {

    private val _authState = MutableStateFlow(AuthResult(success = false, message = ""))
    val authState: StateFlow<AuthResult> = _authState

    fun signUp(email: String, password: String, confirmPassword: String) {
        viewModelScope.launch {
            val result = authRepository.signUp(email, password, confirmPassword)
            _authState.value = result
        }
    }

    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            val result = authRepository.signIn(email, password)
            _authState.value = result
        }
    }

    fun signOut() {
        val result = authRepository.signOut()
        _authState.value = result
    }

    fun sendEmailVerification() {
        viewModelScope.launch {
            val result = authRepository.sendEmailVerification()
            _authState.value = result
        }
    }

    fun isEmailVerified(): Boolean {
        return authRepository.isEmailVerified()
    }
}
