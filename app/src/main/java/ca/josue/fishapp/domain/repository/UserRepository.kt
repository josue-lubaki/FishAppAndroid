package ca.josue.fishapp.domain.repository

import ca.josue.fishapp.domain.model.User

interface UserRepository {
    suspend fun getUserById(id : String) : User?
    suspend fun insertUser(user : User)
    suspend fun deleteUser(user: User)
    suspend fun updateUser(user: User)
    suspend fun deleteUsers()
}