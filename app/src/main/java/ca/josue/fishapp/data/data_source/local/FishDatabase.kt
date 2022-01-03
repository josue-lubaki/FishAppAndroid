package ca.josue.fishapp.data.data_source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ca.josue.fishapp.data.data_source.local.dao.ProductRoomDao
import ca.josue.fishapp.data.data_source.local.dao.MyOrderDao
import ca.josue.fishapp.data.data_source.local.dao.OrderItemDao
import ca.josue.fishapp.data.data_source.local.dao.UserDao
import ca.josue.fishapp.domain.model.ProductRoom
import ca.josue.fishapp.domain.model.MyOrdersRoom
import ca.josue.fishapp.domain.model.OrderItemRoom
import ca.josue.fishapp.domain.model.UserRoom
import ca.josue.fishapp.domain.util.IntConverter

@Database(
    entities = [UserRoom::class, ProductRoom::class, MyOrdersRoom::class, OrderItemRoom::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(IntConverter::class)
abstract class FishDatabase : RoomDatabase() {

    // les instances du DAO
    abstract val userDao : UserDao
    abstract val productDao : ProductRoomDao
    abstract val myOrderDao : MyOrderDao
    abstract val orderItemDao : OrderItemDao

    companion object {
        const val DATABASE_NAME = "fish_db"
    }
}