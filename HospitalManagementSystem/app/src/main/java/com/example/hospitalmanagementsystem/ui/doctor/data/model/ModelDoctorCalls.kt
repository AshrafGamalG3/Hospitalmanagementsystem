package com.example.hospitalmanagementsystem.ui.doctor.data.model

data class ModelDoctorCalls(
    val `data`: List<DataDoctorCalls>,
    val message: String,
    val status: Int
)

data class DataDoctorCalls(
    val created_at: String,
    val id: Int,
    val patient_name: String,
    var status: String
)