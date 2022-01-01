package ca.josue.fishapp.data.repository

import androidx.lifecycle.LiveData
import ca.josue.fishapp.data.data_source.local.dao.MyOrderDao
import ca.josue.fishapp.domain.dto.MyOrderDTO
import ca.josue.fishapp.domain.repository.MyOrderRepository
import kotlinx.coroutines.flow.Flow

class MyOrderRepositoryImpl(
    private val dao : MyOrderDao
) : MyOrderRepository {
    override fun getMyOrders(): LiveData<List<MyOrderDTO>> {
        return dao.getMyOrders()
    }

    override suspend fun getMyOrderById(id: String): MyOrderDTO? {
        return dao.getMyOrderById(id)
    }

    override suspend fun insertMyOrder(myOrder: MyOrderDTO) {
        return dao.insertMyOrder(myOrder)
    }

    override suspend fun insertMyOrders(myOrderList: MutableList<MyOrderDTO>) {
        return dao.insertMyOrders(myOrderList)
    }

    override suspend fun deleteMyOrder(myOrder: MyOrderDTO) {
        return dao.deleteMyOrder(myOrder)
    }

    override suspend fun deleteMyOrders() {
        return dao.deleteMyOrders()
    }
}