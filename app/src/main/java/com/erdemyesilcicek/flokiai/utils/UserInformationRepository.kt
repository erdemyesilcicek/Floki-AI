package com.erdemyesilcicek.flokiai.utils

import android.content.SharedPreferences
import com.erdemyesilcicek.flokiai.datas.UserInformationModel

class UserInformationRepository(private val sharedPreferences: SharedPreferences) {

    companion object {
        private const val KEY_YOUR_NAME = "key_your_name"
        private const val KEY_AGE = "key_age"
        private const val KEY_GENDER = "key_gender"
        private const val KEY_DAD_NAME = "key_dad_name"
        private const val KEY_MOM_NAME = "key_mom_name"
        private const val KEY_SIBLING_NAME = "key_sibling_name"
    }

    fun getUserInformation(): UserInformationModel {
        val yourName = sharedPreferences.getString(KEY_YOUR_NAME, "") ?: ""
        val age = sharedPreferences.getInt(KEY_AGE, 0)
        val gender = sharedPreferences.getString(KEY_GENDER, "") ?: ""
        val dadName = sharedPreferences.getString(KEY_DAD_NAME, "") ?: ""
        val momName = sharedPreferences.getString(KEY_MOM_NAME, "") ?: ""
        val siblingName = sharedPreferences.getString(KEY_SIBLING_NAME, "") ?: ""
        return UserInformationModel(yourName, age, gender, dadName, momName, siblingName)
    }

    fun saveUserInformation(userInformation: UserInformationModel) {
        sharedPreferences.edit().apply {
            putString(KEY_YOUR_NAME, userInformation.yourName)
            putInt(KEY_AGE, userInformation.age)
            putString(KEY_GENDER, userInformation.gender)
            putString(KEY_DAD_NAME, userInformation.dadName)
            putString(KEY_MOM_NAME, userInformation.momName)
            putString(KEY_SIBLING_NAME, userInformation.siblingName)
            apply()
        }
    }
}