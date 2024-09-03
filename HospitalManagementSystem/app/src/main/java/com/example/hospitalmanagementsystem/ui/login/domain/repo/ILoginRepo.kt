package com.example.hospitalmanagementsystem.ui.login.domain.repo

import com.example.hospitalmanagementsystem.ui.login.data.model.ModelUser

interface ILoginRepo {
    suspend fun login(email: String, password: String, deviceToken: String): ModelUser
}