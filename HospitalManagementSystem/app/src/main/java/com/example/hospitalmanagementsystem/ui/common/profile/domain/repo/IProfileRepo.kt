package com.example.hospitalmanagementsystem.ui.common.profile.domain.repo

import com.example.hospitalmanagementsystem.ui.login.data.model.ModelUser

interface IProfileRepo {

  suspend  fun getProfileData(id:Int): ModelUser
}