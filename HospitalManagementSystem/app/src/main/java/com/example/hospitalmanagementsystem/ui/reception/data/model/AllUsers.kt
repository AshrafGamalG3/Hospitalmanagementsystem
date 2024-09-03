package com.example.hospitalmanagementsystem.ui.reception.data.model

data class AllUsers(
    val `data`: List<AllUserData>,
    val message: String,
    val status: Int
)

data class AllUserData(
    val avatar: String,
    val first_name: String,
    val id: Int,
    val type: String
)