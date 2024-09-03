package com.example.hospitalmanagementsystem.ui.login.data.repo

import com.example.hospitalmanagementsystem.utils.MySharedPreferences
import com.example.hospitalmanagementsystem.ui.login.data.model.ModelUser
import com.example.hospitalmanagementsystem.network.ApiCalls
import com.example.hospitalmanagementsystem.ui.login.domain.repo.ILoginRepo
import javax.inject.Inject

class LoginRepo @Inject constructor(private val api: ApiCalls) : ILoginRepo {

    override suspend fun login(email: String, password: String, deviceToken: String): ModelUser {
        val data = api.login(email, password, deviceToken)
        setUserData(data)
        return data
    }


  private  fun setUserData(user: ModelUser) {
        val editor = MySharedPreferences.getSharedPreferences().edit()
        user.data.apply {
            editor.putBoolean(MySharedPreferences.IS_LOGGED_IN,true)
            editor.putString(MySharedPreferences.USER_PHONE, mobile)
            editor.putString(MySharedPreferences.USER_EMAIL, email)
            editor.putString(MySharedPreferences.USER_NAME, "$first_name $last_name")
            editor.putInt(MySharedPreferences.USER_ID, id)
            editor.putString(MySharedPreferences.USER_TOKEN, access_token)
            editor.putString(MySharedPreferences.USER_ATTENDED, status)
            editor.putString(MySharedPreferences.USER_GENDER, gender)
            editor.putString(MySharedPreferences.USER_ADDRESS, address)
            editor.putString(MySharedPreferences.USER_DATE, birthday)
            editor.putString(MySharedPreferences.USER_STATE, status)
            editor.putString(MySharedPreferences.TYPE, type)
        }
        editor.apply()
    }
}