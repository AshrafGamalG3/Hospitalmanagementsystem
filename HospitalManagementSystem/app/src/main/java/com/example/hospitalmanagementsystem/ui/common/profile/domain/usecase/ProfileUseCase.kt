package com.example.hospitalmanagementsystem.ui.common.profile.domain.usecase

import com.example.hospitalmanagementsystem.ui.login.data.model.ModelUser
import com.example.hospitalmanagementsystem.ui.common.profile.domain.repo.IProfileRepo
import javax.inject.Inject

class ProfileUseCase @Inject constructor(private val iProfileRepo: IProfileRepo){
    suspend operator fun invoke(id:Int): ModelUser {
        return iProfileRepo.getProfileData(id)
    }
}