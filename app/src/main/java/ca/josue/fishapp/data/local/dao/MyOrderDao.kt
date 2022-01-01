package ca.josue.fishapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ca.josue.fishapp.domain.dto.MyOrderDTO
import kotlinx.coroutines.flow.Flow

@Dao
interface MyOrderDao {
    @Query("SELECT * FROM myorderdto")
    fun getMyOrders() : Flow<List<MyOrderDTO>>

    @Query("SELECT * FROM myorderdto WHERE idOrderItem = :idOrder")
    suspend fun getMyOrder(idOrder : String) : MyOrderDTO?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMyOrder(myOrder : MyOrderDTO)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMyOrders(myOrders : List<MyOrderDTO>)
}