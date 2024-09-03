package com.example.hospitalmanagementsystem.ui.reception.data.mapper


import com.example.hospitalmanagementsystem.ui.reception.data.model.AllCalls
import com.example.hospitalmanagementsystem.ui.reception.domain.model.ModelCalls
import javax.inject.Inject

class CallsMapperImpl @Inject constructor() {
     suspend fun getAllCalls(allCalls: AllCalls): List<ModelCalls> {
    return allCalls.data.map { callsData ->
        ModelCalls(
            name = callsData.patient_name,
            date = callsData.created_at,
            id = callsData.id
        )
    }
    }
}