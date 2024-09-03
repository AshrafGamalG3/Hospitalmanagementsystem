package com.example.hospitalmanagementsystem.ui.common.profile.presentaion

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hospitalmanagementsystem.ui.common.profile.data.mapper.UserMapperImplProfile
import com.example.hospitalmanagementsystem.ui.common.profile.domain.model.ModelProfile
import com.example.hospitalmanagementsystem.ui.common.profile.domain.usecase.ProfileUseCase

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
@HiltViewModel
class ProfileViewModel @Inject constructor(
    private  val profileUseCase: ProfileUseCase,
    private val userMapperProfile: UserMapperImplProfile
) :ViewModel() {
    var _mutableLiveData=MutableLiveData<ModelProfile>()
    val mutableLiveData get() = _mutableLiveData

    fun getProfileData(id:Int){
        viewModelScope.launch(IO) {

            try {
                val userModel=profileUseCase(id)
                val profile=userMapperProfile.mapUser(userModel)
                withContext(Main){
                    mutableLiveData.value=profile
                }
            }
            catch (e:Exception){
                Log.e("ProfileViewModel", "Error fetching profile data", e)
            }


        }
    }
}