package ca.josue.fishapp.domain.repository

import androidx.lifecycle.LiveData
import ca.josue.fishapp.domain.dto.MyOrderDTO

interface MyOrderRepository {
    fun getMyOrders() : LiveData<List<MyOrderDTO>>
    suspend fun getMyOrderById(id : String) : MyOrderDTO?
    suspend fun insertMyOrder(myOrder : MyOrderDTO)
    suspend fun insertMyOrders(myOrder : MutableList<MyOrderDTO>)
    suspend fun deleteMyOrder(myOrder: MyOrderDTO)
    suspend fun deleteMyOrders()
}