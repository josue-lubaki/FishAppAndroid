package ca.josue.fishapp.data.repository

import androidx.lifecycle.LiveData
import ca.josue.fishapp.data.data_source.local.dao.MyOrderDao
import ca.josue.fishapp.domain.model.MyOrdersRoom
import ca.josue.fishapp.domain.repository.MyOrderRepository

class MyOrderRepositoryImpl(
    private val dao : MyOrderDao
) : MyOrderRepository {
    override fun getMyOrders(): LiveData<List<MyOrdersRoom>> {
        return dao.getMyOrders()
    }

    override suspend fun getMyOrderById(id: String): MyOrdersRoom? {
        return dao.getMyOrderById(id)
    }

    override suspend fun insertMyOrder(myOrder: MyOrdersRoom) {
        return dao.insertMyOrder(myOrder)
    }

    override suspend fun insertMyOrders(myOrderList: MutableList<MyOrdersRoom>) {
        return dao.insertMyOrders(myOrderList)
    }

    override suspend fun deleteMyOrder(myOrder: MyOrdersRoom) {
        return dao.deleteMyOrder(myOrder)
    }

    override suspend fun deleteMyOrders() {
        return dao.deleteMyOrders()
    }
}