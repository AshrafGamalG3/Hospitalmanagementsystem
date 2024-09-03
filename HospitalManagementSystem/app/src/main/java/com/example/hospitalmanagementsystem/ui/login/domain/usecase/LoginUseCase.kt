package com.example.hospitalmanagementsystem.ui.login.domain.usecase

import com.example.hospitalmanagementsystem.ui.login.data.model.ModelUser
import com.example.hospitalmanagementsystem.ui.login.domain.repo.ILoginRepo
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val iLoginRepo: ILoginRepo) {
    suspend operator fun invoke(email: String, pass: String, deviceToken: String): ModelUser {

            return iLoginRepo.login(email, pass, deviceToken)
    }
}