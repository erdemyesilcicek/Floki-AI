package com.erdemyesilcicek.flokiai.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth

class AuthViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val firebaseAuth = FirebaseAuth.getInstance()
        val repository = AuthRepository(firebaseAuth)
        return AuthViewModel(repository) as T
    }
}