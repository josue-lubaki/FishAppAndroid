package ca.josue.fishapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class OrderItemRoom {
    @PrimaryKey
    lateinit var idOrderItem: String
    lateinit var idProduct: String
    lateinit var quantity: Number
    lateinit var idOrder: String
}