package ca.josue.fishapp.domain.repository

import androidx.lifecycle.LiveData
import ca.josue.fishapp.domain.model.ProductRoom

interface ProductRoomRepository {
    fun getProductRooms() : LiveData<List<ProductRoom>>
    suspend fun getProductRoomById(id : String) : ProductRoom?
    suspend fun insertProductRoom(productRoom : ProductRoom)
    suspend fun deleteProductRoom(productRoom : ProductRoom)
    suspend fun deleteProductRooms()
    suspend fun insertProductRooms(productRooms: MutableList<ProductRoom>)
}