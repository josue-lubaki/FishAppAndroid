package ca.josue.fishapp.domain.viewModel

import androidx.lifecycle.ViewModel
import ca.josue.fishapp.domain.model.UserRoom
import ca.josue.fishapp.domain.repository.UserRepository
import javax.inject.Inject

class UserViewModel @Inject constructor(
    private val userRepository : UserRepository
) : ViewModel() {
    suspend fun getUser(user : UserRoom) : UserRoom? {
        return userRepository.getUserById(user.idUser)
    }

    suspend fun insertUser(user : UserRoom) {
        return userRepository.insertUser(user)
    }

    suspend fun deleteUser(user : UserRoom) {
        return userRepository.deleteUser(user)
    }

    suspend fun deleteUsers() {
        return userRepository.deleteUsers()
    }
}