package ca.josue.fishapp.domain.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import ca.josue.fishapp.domain.util.IntConverter

@Entity
class MyOrderDTO {
    lateinit var name : String
    lateinit var price : Number
    lateinit var quantity : Number
    lateinit var category : String
    lateinit var description : String
    lateinit var status : String
    lateinit var imageURL : String
    var isFeature : Boolean = false
    @PrimaryKey
    lateinit var idOrderItem: String
}