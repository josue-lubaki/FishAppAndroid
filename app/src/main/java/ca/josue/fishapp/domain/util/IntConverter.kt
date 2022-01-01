package ca.josue.fishapp.domain.util

import androidx.room.TypeConverter

class IntConverter {
   companion object{
       @TypeConverter
       @JvmStatic
       fun toDouble(number : Number): Double {
           return number.toDouble()
       }
   }
}