package ca.josue.fishapp.domain.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.josue.fishapp.domain.model.ProductRoom
import ca.josue.fishapp.domain.repository.ProductRoomRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProductRoomViewModel @Inject constructor(
    private val productResponseRepository: ProductRoomRepository
) : ViewModel() {

    fun getProductRooms() : LiveData<List<ProductRoom>> {
        return productResponseRepository.getProductRooms()
    }

    suspend fun getProductRoomById(id : String) : ProductRoom? {
        return productResponseRepository.getProductRoomById(id)
    }

    suspend fun insertProdutRoom(productRoom : ProductRoom) {
        return productResponseRepository.insertProductRoom(productRoom)
    }

    suspend fun insertProdutRooms(productRooms : MutableList<ProductRoom>) = viewModelScope.launch {
        productResponseRepository.insertProductRooms(productRooms)
    }

    suspend fun deleteProdutRoom(productRooms : ProductRoom) = viewModelScope.launch  {
        productResponseRepository.deleteProductRoom(productRooms)
    }

    suspend fun deleteProdutRooms() = viewModelScope.launch  {
        productResponseRepository.deleteProductRooms()
    }
}