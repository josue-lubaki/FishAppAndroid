package ca.josue.fishapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

// timestamp : le dernier time de connection
@Entity
class UserRoom (
    @PrimaryKey
    val idUser: String,
    val email: String,
    var name: String,
    val token: String,
    val timestamp : Long
)