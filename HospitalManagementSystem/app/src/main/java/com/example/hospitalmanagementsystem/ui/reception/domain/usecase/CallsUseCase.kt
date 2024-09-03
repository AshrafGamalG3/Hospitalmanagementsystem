package com.example.hospitalmanagementsystem.ui.reception.domain.usecase

import android.util.Log
import com.example.hospitalmanagementsystem.ui.reception.data.model.AllCalls
import com.example.hospitalmanagementsystem.ui.reception.data.model.CreationCall
import com.example.hospitalmanagementsystem.ui.reception.data.model.AllUsers


import com.example.hospitalmanagementsystem.ui.reception.domain.repo.ICallsRepo
import com.magdy.hospitalsystem.data.CallData
import javax.inject.Inject

class CallsUseCase @Inject constructor(private val iCallsRepo: ICallsRepo) {
    suspend operator fun invoke(date:String): AllCalls {
        Log.e("TAG", "invoke: return ",  )
        return iCallsRepo.getAllCalls(date)
    }
    suspend operator fun invoke(name: String,age: String,doctorId: Int,phone: String,description: String): CreationCall {
       return iCallsRepo.createCall(name,age, doctorId, phone, description)
    }
    suspend operator fun invoke(type:String,other:Int): AllUsers {
        return iCallsRepo.getAllDoctors(type)
    }
    suspend operator fun invoke(id:Int): CallData {
        return iCallsRepo.getCallById(id).data
    }
}