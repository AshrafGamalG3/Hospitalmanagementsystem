package com.example.hospitalmanagementsystem.ui.reception.data.repo


import com.example.hospitalmanagementsystem.ui.reception.data.model.AllCalls
import com.example.hospitalmanagementsystem.ui.reception.data.model.CreationCall
import com.example.hospitalmanagementsystem.network.ApiCalls
import com.example.hospitalmanagementsystem.ui.reception.data.model.AllUsers


import com.example.hospitalmanagementsystem.ui.reception.domain.repo.ICallsRepo
import com.magdy.hospitalsystem.data.ModelShowCall
import javax.inject.Inject

class CallsRepo @Inject constructor(private val apiCalls: ApiCalls) : ICallsRepo {
    override suspend fun getAllCalls(date:String): AllCalls {
        return apiCalls.getAllCalls(date)
    }

    override suspend fun createCall(
        name: String,
        age: String,
        doctorId: Int,
        phone: String,
        description: String
    ): CreationCall {
        return apiCalls.createCallReception(name, age, doctorId, phone, description)
    }

    override suspend fun getAllDoctors(type: String): AllUsers {
        return apiCalls.getAllByType(type)
    }

    override suspend fun getCallById(id: Int): ModelShowCall {
        return apiCalls.getCallById(id)
    }

}