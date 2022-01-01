package ca.josue.fishapp.domain.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import ca.josue.fishapp.domain.dto.MyOrderDTO
import ca.josue.fishapp.domain.repository.MyOrderRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MyOrderViewModel @Inject constructor(
    private val myOrderRepository: MyOrderRepository
) : ViewModel() {

    fun getMyOrders() : LiveData<List<MyOrderDTO>> {
        return myOrderRepository.getMyOrders()
    }

    suspend fun getMyOrdersById(id : String) : MyOrderDTO? {
        return myOrderRepository.getMyOrderById(id)
    }

    suspend fun insertMyOrder(myOrder : MyOrderDTO) {
        return myOrderRepository.insertMyOrder(myOrder)
    }

    suspend fun deleteMyOrder(myOrder : MyOrderDTO) {
        return myOrderRepository.deleteMyOrder(myOrder)
    }

    suspend fun deleteMyOrders() {
        return myOrderRepository.deleteMyOrders()
    }
}