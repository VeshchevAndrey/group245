package com.example.application245

import kotlinx.serialization.Serializable

@Serializable
data class UserProfile(
    val name: String = "",
    val phone: String = "",
    val email: String = ""
)