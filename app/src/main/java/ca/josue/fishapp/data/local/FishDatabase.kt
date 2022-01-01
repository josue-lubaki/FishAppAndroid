package ca.josue.fishapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ca.josue.fishapp.data.local.dao.FishModelDao
import ca.josue.fishapp.data.local.dao.MyOrderDao
import ca.josue.fishapp.data.local.dao.UserDao
import ca.josue.fishapp.domain.dto.FishModelResponse
import ca.josue.fishapp.domain.dto.MyOrderDTO
import ca.josue.fishapp.domain.model.Product
import ca.josue.fishapp.domain.model.User
import ca.josue.fishapp.domain.util.IntConverter

@Database(
    entities = [User::class, FishModelResponse::class, MyOrderDTO::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(IntConverter::class)
abstract class FishDatabase : RoomDatabase() {

    // les instances du DAO
    abstract val userDao : UserDao
    abstract val fishModelDao : FishModelDao
    abstract val myOrderDao : MyOrderDao

    companion object {
        const val DATABASE_NAME = "fish_db"
    }
}