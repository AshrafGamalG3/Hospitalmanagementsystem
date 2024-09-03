package com.example.hospitalmanagementsystem.ui.login.presentaion

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.hospitalmanagementsystem.ui.login.data.mapper.UserMapperImpl
import com.example.hospitalmanagementsystem.ui.login.domain.model.UserCredentials
import com.example.hospitalmanagementsystem.ui.login.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val userMapper: UserMapperImpl
) : ViewModel() {

    private var _mutableLiveData = MutableLiveData<UserCredentials>()
    val mutableLiveData get() = _mutableLiveData

    fun login(email: String, pass: String, deviceToken: String) {
        viewModelScope.launch(IO) {
            try {
                val modelUser = loginUseCase(email, pass, deviceToken)
                val userCredentials = userMapper.mapUser(modelUser)
                withContext(Main) {
                    mutableLiveData.value = userCredentials
                }
            } catch (e: Exception) {
                throw e
            }
        }
    }
}