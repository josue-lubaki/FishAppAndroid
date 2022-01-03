package ca.josue.fishapp.data.data_source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import ca.josue.fishapp.domain.model.MyOrdersRoom

@Dao
interface MyOrderDao {
    @Query("SELECT * FROM myordersroom")
    fun getMyOrders() : LiveData<List<MyOrdersRoom>>

    @Query("SELECT * FROM myordersroom WHERE idOrder = :idOrder")
    suspend fun getMyOrderById(idOrder : String) : MyOrdersRoom?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMyOrder(myOrder : MyOrdersRoom)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMyOrders(myOrders : MutableList<MyOrdersRoom>)

    @Update
    suspend fun updateMyOrder(myOrder : MyOrdersRoom)
    
    @Delete
    suspend fun deleteMyOrder(myOrder : MyOrdersRoom)

    @Query("DELETE FROM myordersroom")
    suspend fun deleteMyOrders()
}