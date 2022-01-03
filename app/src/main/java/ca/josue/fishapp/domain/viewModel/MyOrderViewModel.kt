package ca.josue.fishapp.domain.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import ca.josue.fishapp.domain.model.MyOrdersRoom
import ca.josue.fishapp.domain.repository.MyOrderRepository
import javax.inject.Inject

class MyOrderViewModel @Inject constructor(
    private val myOrderRepository: MyOrderRepository
) : ViewModel() {

    fun getMyOrders() : LiveData<List<MyOrdersRoom>> {
        return myOrderRepository.getMyOrders()
    }

    suspend fun getMyOrdersById(id : String) : MyOrdersRoom? {
        return myOrderRepository.getMyOrderById(id)
    }

    suspend fun insertMyOrder(myOrder : MyOrdersRoom) {
        return myOrderRepository.insertMyOrder(myOrder)
    }

    suspend fun insertMyOrders(myOrders : MutableList<MyOrdersRoom>) {
        return myOrderRepository.insertMyOrders(myOrders)
    }


    suspend fun deleteMyOrder(myOrder : MyOrdersRoom) {
        return myOrderRepository.deleteMyOrder(myOrder)
    }

    suspend fun deleteMyOrders() {
        return myOrderRepository.deleteMyOrders()
    }
}