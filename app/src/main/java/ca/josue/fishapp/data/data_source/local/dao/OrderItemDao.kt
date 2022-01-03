package ca.josue.fishapp.data.data_source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import ca.josue.fishapp.domain.model.OrderItemRoom
import ca.josue.fishapp.domain.model.ProductRoom

@Dao
interface OrderItemDao {

    @Query("SELECT P.* FROM orderitemroom AS O " +
            "INNER JOIN ProductRoom AS P " +
            "ON O.idProduct = P.idProduct " +
            "WHERE O.idOrder = :idOrder")
    fun getInfoProductByIdOrder(idOrder : String) : LiveData<List<ProductRoom>>
    
    @Query("SELECT * FROM OrderItemRoom")
    fun getOrdersItems() : LiveData<List<OrderItemRoom>>

    @Query("SELECT * FROM OrderItemRoom WHERE idOrderItem = :id")
    suspend fun getOrderItemById(id : String) :OrderItemRoom?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrderItemRoom(orderItemRoom : OrderItemRoom)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrderItemsRoom(orderItemRoom : MutableList<OrderItemRoom>)

    @Update
    suspend fun updateOrderItemRoom(orderItemRoom : OrderItemRoom)

    @Delete
    suspend fun deleteOrderItem(orderItemRoom : OrderItemRoom)

    @Query("DELETE FROM OrderItemRoom")
    suspend fun deleteOrdersItems()

}