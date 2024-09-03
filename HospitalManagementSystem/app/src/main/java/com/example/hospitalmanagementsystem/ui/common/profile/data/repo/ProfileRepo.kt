package com.example.hospitalmanagementsystem.ui.common.profile.data.repo

import com.example.hospitalmanagementsystem.ui.login.data.model.ModelUser
import com.example.hospitalmanagementsystem.network.ApiCalls
import com.example.hospitalmanagementsystem.ui.common.profile.domain.repo.IProfileRepo
import javax.inject.Inject

class ProfileRepo  @Inject constructor(private val api: ApiCalls) : IProfileRepo {
    override suspend fun getProfileData(id: Int): ModelUser {
            return api.getProfileData(id)
    }
}