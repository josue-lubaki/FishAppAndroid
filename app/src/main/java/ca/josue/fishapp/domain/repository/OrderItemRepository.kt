package ca.josue.fishapp.domain.repository

import androidx.lifecycle.LiveData
import ca.josue.fishapp.domain.model.OrderItemRoom
import ca.josue.fishapp.domain.model.ProductRoom

interface OrderItemRepository {
    fun getOrderItems() : LiveData<List<OrderItemRoom>>
    suspend fun getOrderItemById(id : String) : OrderItemRoom?
    suspend fun insertOrderItem(orderItem : OrderItemRoom)
    suspend fun updateOrderItem(orderItem : OrderItemRoom)
    suspend fun insertOrderItems(orderItem : MutableList<OrderItemRoom>)
    suspend fun deleteOrderItem(orderItem: OrderItemRoom)
    suspend fun deleteOrdersItems()
    fun getInfoProductByIdOrder(idOrder : String) : LiveData<List<ProductRoom>>
}