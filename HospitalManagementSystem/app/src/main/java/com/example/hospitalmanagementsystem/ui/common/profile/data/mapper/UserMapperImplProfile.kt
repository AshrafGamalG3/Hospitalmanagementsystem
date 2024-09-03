package com.example.hospitalmanagementsystem.ui.common.profile.data.mapper

import com.example.hospitalmanagementsystem.ui.login.data.model.ModelUser
import com.example.hospitalmanagementsystem.ui.common.profile.domain.model.ModelProfile
import javax.inject.Inject

class UserMapperImplProfile @Inject constructor()  {
     fun mapUser(user: ModelUser): ModelProfile {
     return   ModelProfile(

            name = user.data.first_name+" "+user.data.last_name,
            type = user.data.type,
            gender = user.data.gender,
            birthday = user.data.birthday,
            address = user.data.address,
            status = user.data.status,
            email = user.data.email,
            mobile = user.data.mobile
        )
    }
}