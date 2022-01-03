package ca.josue.fishapp.domain.model

import androidx.annotation.Nullable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class ProductRoom {
        @PrimaryKey
        lateinit var idProduct: String
        lateinit var name: String
        lateinit var category: String
        lateinit var price: Number
        lateinit var imageUrl: String
        lateinit var description: String
        var isFeature: Boolean = false
        lateinit var status: String
}