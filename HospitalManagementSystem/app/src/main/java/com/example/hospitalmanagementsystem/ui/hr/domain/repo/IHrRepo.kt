package com.example.hospitalmanagementsystem.ui.hr.domain.repo

import com.example.hospitalmanagementsystem.ui.login.data.model.ModelUser

interface IHrRepo {

    suspend fun createUser( email: String,
                            password: String,
                            fName: String,
                            lName: String,
                            gender: String,
                            specialist: String,
                            birthday: String,
                            status: String,
                            address: String,
                            mobile: String,
                            type: String) : ModelUser
}