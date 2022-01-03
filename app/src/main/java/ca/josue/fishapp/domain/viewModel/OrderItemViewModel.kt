package ca.josue.fishapp.domain.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import ca.josue.fishapp.domain.model.OrderItemRoom
import ca.josue.fishapp.domain.model.ProductRoom
import ca.josue.fishapp.domain.repository.OrderItemRepository
import javax.inject.Inject

class OrderItemViewModel @Inject constructor(
    val repository : OrderItemRepository
    ) : ViewModel() {

        fun getOrderItems() : LiveData<List<OrderItemRoom>> {
            return repository.getOrderItems()
        }

    suspend fun getOrderItemById(id : String) : OrderItemRoom?{
        return repository.getOrderItemById(id)
    }

    suspend fun insertOrderItem(OrderItem : OrderItemRoom) {
        return repository.insertOrderItem(OrderItem)
    }

    suspend fun insertOrderItems(OrderItems : MutableList<OrderItemRoom>) {
        return repository.insertOrderItems(OrderItems)
    }

    suspend fun updateOrderItem(OrderItem : OrderItemRoom) {
        return repository.updateOrderItem(OrderItem)
    }


    suspend fun deleteOrderItem(OrderItem : OrderItemRoom) {
        return repository.deleteOrderItem(OrderItem)
    }

    suspend fun deleteOrderItems() {
        return repository.deleteOrdersItems()
    }

    fun getInfoProductByIdOrder(idOrder : String) : LiveData<List<ProductRoom>>{
        return repository.getInfoProductByIdOrder(idOrder)
    }
}