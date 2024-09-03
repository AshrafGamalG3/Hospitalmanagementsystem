package com.example.hospitalmanagementsystem.ui.reception.domain.repo


import com.example.hospitalmanagementsystem.ui.reception.data.model.AllCalls
import com.example.hospitalmanagementsystem.ui.reception.data.model.CreationCall
import com.example.hospitalmanagementsystem.ui.reception.data.model.AllUsers


import com.magdy.hospitalsystem.data.ModelShowCall

interface ICallsRepo {

    suspend fun getAllCalls(data:String): AllCalls

    suspend fun createCall(name: String,age: String,doctorId: Int,phone: String,description: String): CreationCall

    suspend fun getAllDoctors(type: String): AllUsers

    suspend fun getCallById(id:Int): ModelShowCall


}