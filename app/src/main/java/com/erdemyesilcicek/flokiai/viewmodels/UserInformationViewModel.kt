package com.erdemyesilcicek.flokiai.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.erdemyesilcicek.flokiai.datas.UserInformationModel
import com.erdemyesilcicek.flokiai.utils.UserInformationRepository

class UserInformationViewModel(private val repository: UserInformationRepository) : ViewModel() {

    private val _userInformation = MutableLiveData<UserInformationModel>()
    val userInformation: LiveData<UserInformationModel> get() = _userInformation

    init {
        loadUserInformation()
    }

    fun loadUserInformation() {
        _userInformation.value = repository.getUserInformation()
    }

    fun updateUserInformation(newUserInformation: UserInformationModel) {
        repository.saveUserInformation(newUserInformation)
        _userInformation.value = newUserInformation
    }

    fun clearUserInformation() {
        repository.clearUserInformation()
        _userInformation.value = UserInformationModel()
    }
}
