package com.example.hospitalmanagementsystem.ui.reception.data.model

data class AllCalls(
    val `data`: List<CallsData>,
    val message: String,
    val status: Int
)

data class CallsData(
    val created_at: String,
    val id: Int,
    val patient_name: String,
    val status: String
)