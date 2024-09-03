package com.example.hospitalmanagementsystem.ui.login.domain.model

data class UserCredentials(
    val type: String,
    val email: String,
    val password: String,
    val status:Int,


)