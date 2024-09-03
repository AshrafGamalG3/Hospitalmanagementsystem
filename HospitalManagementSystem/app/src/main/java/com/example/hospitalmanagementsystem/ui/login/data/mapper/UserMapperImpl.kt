package com.example.hospitalmanagementsystem.ui.login.data.mapper

import com.example.hospitalmanagementsystem.ui.login.data.model.ModelUser
import com.example.hospitalmanagementsystem.ui.login.domain.model.UserCredentials
import javax.inject.Inject


class UserMapperImpl @Inject constructor() {
    fun mapUser(modelUser: ModelUser): UserCredentials {
        if (modelUser.status==0) {
            return UserCredentials("","","",0)
        }
        val type = modelUser.data.type ?: " "
        val email = modelUser.data.email ?: " "
        val status = modelUser.status


        return UserCredentials(
            type = type,
            email = email,
            password = "",
            status = status
        )
    }


}