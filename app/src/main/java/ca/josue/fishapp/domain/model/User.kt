package ca.josue.fishapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

// timestamp : le dernier time de connection
@Entity
class User (
    @PrimaryKey
    val id: String,
    val email: String,
    val name: String,
    val token: String,
    val timestamp : Long
)