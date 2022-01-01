package ca.josue.fishapp.data.repository

import ca.josue.fishapp.data.data_source.local.dao.UserDao
import ca.josue.fishapp.domain.model.User
import ca.josue.fishapp.domain.repository.UserRepository

class UserRepositoryImpl(
    private val dao : UserDao
) : UserRepository {

    override suspend fun getUserById(id: String): User? {
        return dao.getUserById(id)
    }

    override suspend fun insertUser(user: User) {
        return dao.insertUser(user)
    }

    override suspend fun deleteUser(user: User) {
        return dao.deleteUser(user)
    }

    override suspend fun updateUser(user: User) {
        return dao.updateUser(user)
    }

    override suspend fun deleteUsers() {
        return dao.deleteUsers()
    }
}