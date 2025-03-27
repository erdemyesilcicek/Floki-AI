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
    val userInformation: LiveData<UserInformationModel?> = _userInformation

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    init {
        loadUserInformation()
    }

    fun loadUserInformation() {
        viewModelScope.launch {
            handleResult(repository.getUserInformation())
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
        viewModelScope.launch {
            repository.clearUserInformation()
                .onSuccess {
                    _userInformation.value = null
                    _error.value = null
                }
                .onFailure {
                    _error.value = it.message
                }
        }
    }
    
    private fun handleResult(result: Result<UserInformationModel?>) {
        result.onSuccess { model ->
            _userInformation.value = model
            _error.value = null
        }.onFailure { error ->
            _userInformation.value = null
            _error.value = error.message
        }
    }
}
