package ca.josue.fishapp.domain.viewModel

import androidx.lifecycle.ViewModel
import ca.josue.fishapp.domain.model.User
import ca.josue.fishapp.domain.repository.UserRepository
import javax.inject.Inject

class UserViewModel @Inject constructor(
    private val userRepository : UserRepository
) : ViewModel() {
    suspend fun getUser(user : User) : User? {
        return userRepository.getUserById(user.id)
    }

    suspend fun insertUser(user : User) {
        return userRepository.insertUser(user)
    }

    suspend fun deleteUser(user : User) {
        return userRepository.deleteUser(user)
    }

    suspend fun deleteUsers() {
        return userRepository.deleteUsers()
    }
}