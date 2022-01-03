package ca.josue.fishapp.domain.repository

import androidx.lifecycle.LiveData
import ca.josue.fishapp.domain.model.MyOrdersRoom

interface MyOrderRepository {
    fun getMyOrders() : LiveData<List<MyOrdersRoom>>
    suspend fun getMyOrderById(id : String) : MyOrdersRoom?
    suspend fun insertMyOrder(myOrder : MyOrdersRoom)
    suspend fun insertMyOrders(myOrder : MutableList<MyOrdersRoom>)
    suspend fun deleteMyOrder(myOrder: MyOrdersRoom)
    suspend fun deleteMyOrders()
}