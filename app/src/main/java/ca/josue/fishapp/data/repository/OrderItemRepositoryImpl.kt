package ca.josue.fishapp.data.repository

import androidx.lifecycle.LiveData
import ca.josue.fishapp.data.data_source.local.dao.OrderItemDao
import ca.josue.fishapp.domain.model.OrderItemRoom
import ca.josue.fishapp.domain.model.ProductRoom
import ca.josue.fishapp.domain.repository.OrderItemRepository


class OrderItemRepositoryImpl (
    private val dao : OrderItemDao
    ) : OrderItemRepository {
    override fun getOrderItems(): LiveData<List<OrderItemRoom>> {
        return dao.getOrdersItems()
    }

    override suspend fun getOrderItemById(id: String): OrderItemRoom? {
        return dao.getOrderItemById(id)
    }

    override suspend fun insertOrderItem(orderItem: OrderItemRoom) {
        return dao.insertOrderItemRoom(orderItem)
    }

    override suspend fun updateOrderItem(orderItem: OrderItemRoom) {
        return dao.updateOrderItemRoom(orderItem)
    }

    override suspend fun insertOrderItems(orderItem: MutableList<OrderItemRoom>) {
        return dao.insertOrderItemsRoom(orderItem)
    }

    override suspend fun deleteOrderItem(orderItem: OrderItemRoom) {
        return dao.deleteOrderItem(orderItem)
    }

    override suspend fun deleteOrdersItems() {
        return dao.deleteOrdersItems()
    }

    override fun getInfoProductByIdOrder(idOrder: String) : LiveData<List<ProductRoom>> {
        return dao.getInfoProductByIdOrder(idOrder)
    }
}