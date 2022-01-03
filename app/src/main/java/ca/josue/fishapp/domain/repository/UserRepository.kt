package ca.josue.fishapp.domain.repository

import ca.josue.fishapp.domain.model.UserRoom

interface UserRepository {
    suspend fun getUserById(id : String) : UserRoom?
    suspend fun insertUser(user : UserRoom)
    suspend fun deleteUser(user: UserRoom)
    suspend fun updateUser(user: UserRoom)
    suspend fun deleteUsers()
}