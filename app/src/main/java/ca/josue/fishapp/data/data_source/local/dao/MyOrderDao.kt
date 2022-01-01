package ca.josue.fishapp.data.data_source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import ca.josue.fishapp.domain.dto.MyOrderDTO
import kotlinx.coroutines.flow.Flow

@Dao
interface MyOrderDao {
    @Query("SELECT * FROM myorderdto")
    fun getMyOrders() : LiveData<List<MyOrderDTO>>

    @Query("SELECT * FROM myorderdto WHERE idOrderItem = :idOrder")
    suspend fun getMyOrderById(idOrder : String) : MyOrderDTO?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMyOrder(myOrder : MyOrderDTO)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMyOrders(myOrders : List<MyOrderDTO>)

    @Update
    suspend fun updateMyOrder(myOrder : MyOrderDTO)
    
    @Delete
    suspend fun deleteMyOrder(myOrder : MyOrderDTO)

    @Query("DELETE FROM myorderdto")
    suspend fun deleteMyOrders()
}