package ca.josue.fishapp.domain.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class ProductResponse(
        @PrimaryKey val id : String,
        val name : String,
        val category : String,
        val price : Double,
        val imageUrl : String,
        val description : String,
        val isFeature : Boolean
)