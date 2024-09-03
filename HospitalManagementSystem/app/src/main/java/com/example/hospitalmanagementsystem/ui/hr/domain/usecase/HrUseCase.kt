package com.example.hospitalmanagementsystem.ui.hr.domain.usecase

import com.example.hospitalmanagementsystem.ui.hr.domain.repo.IHrRepo
import com.example.hospitalmanagementsystem.ui.login.data.model.ModelUser
import com.example.hospitalmanagementsystem.ui.login.domain.repo.ILoginRepo
import javax.inject.Inject

class HrUseCase @Inject constructor(private val iHrRepo: IHrRepo) {

   suspend operator fun invoke ( email: String,
                                 password: String,
                                 fName: String,
                                 lName: String,
                                 gender: String,
                                 specialist: String,
                                 birthday: String,
                                 status: String,
                                 address: String,
                                 mobile: String,
                                 type: String) :ModelUser{
       return iHrRepo.createUser(email, password, fName, lName, gender, specialist, birthday, status, address, mobile, type)
   }
}