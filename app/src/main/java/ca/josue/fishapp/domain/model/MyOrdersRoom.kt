package ca.josue.fishapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class MyOrdersRoom {
    @PrimaryKey
    lateinit var idOrder: String
    lateinit var totalPrice : Number
    lateinit var dateOrdered : String
    lateinit var avenue : String
    lateinit var apartment : String
    lateinit var city : String
    lateinit var phone : String
    lateinit var idUser: String
    lateinit var status : String
}