package ca.josue.fishapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class UserRoom (
    @PrimaryKey
    val idUser: String,
    val email: String,
    var name: String,
    val token: String
)