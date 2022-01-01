package ca.josue.fishapp.domain.dto

data class UserLoginResponse(
    val id: String,
    val email: String,
    val name: String,
    val token: String
)