package com.erdemyesilcicek.flokiai.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erdemyesilcicek.flokiai.datas.UserInformationModel
import com.erdemyesilcicek.flokiai.utils.UserInformationRepository
import kotlinx.coroutines.launch

class UserInformationViewModel(private val repository: UserInformationRepository) : ViewModel() {

    private val _userInformation = MutableLiveData<UserInformationModel?>()
    val userInformation: LiveData<UserInformationModel?> get() = _userInformation

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> get() = _error

    init {
        loadUserInformation()
    }

    fun loadUserInformation() {
        viewModelScope.launch {
            val result = repository.getUserInformation()
            result.onSuccess {
                _userInformation.value = it
                _error.value = null
                //println("User information loaded")
            }.onFailure {
                _userInformation.value = null
                _error.value = it.message
                //println("User information loading failed")
            }
        }
    }

    fun updateUserInformation(newUserInformation: UserInformationModel) {
        viewModelScope.launch {
            val result = repository.saveUserInformation(newUserInformation)
            result.onSuccess {
                _userInformation.value = newUserInformation
                _error.value = null
            }.onFailure {
                _error.value = it.message
            }
        }
    }

    fun clearUserInformation() {
        /*
        viewModelScope.launch {
            val result = repository.clearUserInformation()
            result.onSuccess {
                _userInformation.value = UserInformationModel()
                _error.value = null
            }.onFailure {
                _error.value = it.message
            }
        }

         */
        _userInformation.value = null
    }
}
