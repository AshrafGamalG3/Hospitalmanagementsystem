package com.example.hospitalmanagementsystem.ui.hr.data

import com.example.hospitalmanagementsystem.network.ApiCalls
import com.example.hospitalmanagementsystem.ui.hr.domain.repo.IHrRepo
import com.example.hospitalmanagementsystem.ui.login.data.model.ModelUser
import com.example.hospitalmanagementsystem.ui.login.domain.repo.ILoginRepo
import javax.inject.Inject

class HrRepo  @Inject constructor(private val api: ApiCalls) : IHrRepo {
    override suspend fun createUser(
        email: String,
        password: String,
        fName: String,
        lName: String,
        gender: String,
        specialist: String,
        birthday: String,
        status: String,
        address: String,
        mobile: String,
        type: String
    ): ModelUser {
        return api.registerUser(email, password, fName, lName, gender, specialist, birthday, status, address, mobile, type)
    }
}