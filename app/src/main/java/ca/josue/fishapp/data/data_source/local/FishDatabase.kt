package ca.josue.fishapp.data.data_source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ca.josue.fishapp.data.data_source.local.dao.ProductResponseDao
import ca.josue.fishapp.data.data_source.local.dao.MyOrderDao
import ca.josue.fishapp.data.data_source.local.dao.UserDao
import ca.josue.fishapp.domain.dto.ProductResponse
import ca.josue.fishapp.domain.dto.MyOrderDTO
import ca.josue.fishapp.domain.model.User
import ca.josue.fishapp.domain.util.IntConverter

@Database(
    entities = [User::class, ProductResponse::class, MyOrderDTO::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(IntConverter::class)
abstract class FishDatabase : RoomDatabase() {

    // les instances du DAO
    abstract val userDao : UserDao
    abstract val productDao : ProductResponseDao
    abstract val myOrderDao : MyOrderDao

    companion object {
        const val DATABASE_NAME = "fish_db"
    }
}