package com.example.hospitalmanagementsystem.ui.hr.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hospitalmanagementsystem.ui.hr.domain.usecase.HrUseCase
import com.example.hospitalmanagementsystem.ui.login.data.mapper.UserMapperImpl
import com.example.hospitalmanagementsystem.ui.login.data.model.ModelUser
import com.example.hospitalmanagementsystem.ui.login.domain.model.UserCredentials
import com.example.hospitalmanagementsystem.ui.login.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HrViewModel @Inject constructor(
    private val useCase: HrUseCase

) :ViewModel() {
    private var _mutableLiveData = MutableLiveData<ModelUser>()
    val mutableLiveData get() = _mutableLiveData


    fun createUser( email: String,
                    password: String,
                    fName: String,
                    lName: String,
                    gender: String,
                    specialist: String,
                    birthday: String,
                    status: String,
                    address: String,
                    mobile: String,
                    type: String){
        try {
            viewModelScope.launch(IO) {

                val data=useCase(email, password, fName, lName, gender, specialist, birthday, status, address, mobile, type)
                withContext(Main){
                    mutableLiveData.value=data
                }

            }
        }
        catch (e:Exception){
            throw e
        }

    }


}