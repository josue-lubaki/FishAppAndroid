package ca.josue.fishapp.data.repository

import androidx.lifecycle.LiveData
import ca.josue.fishapp.data.data_source.local.dao.ProductRoomDao
import ca.josue.fishapp.domain.model.ProductRoom
import ca.josue.fishapp.domain.repository.ProductRoomRepository

class ProductResponseRepositoryImpl(
    private val dao : ProductRoomDao
) : ProductRoomRepository {
    override fun getProductRooms(): LiveData<List<ProductRoom>> {
        return dao.getProductRooms()
    }

    override suspend fun getProductRoomById(id: String): ProductRoom? {
        return dao.getProductRoomById(id)
    }

    override suspend fun insertProductRoom(productRoom: ProductRoom) {
        return dao.insertProductRoom(productRoom)
    }

    override suspend fun insertProductRooms(productRooms: MutableList<ProductRoom>) {
        return dao.insertProductRooms(productRooms)
    }

    override suspend fun deleteProductRoom(productRoom: ProductRoom) {
        return dao.deleteProductRoom(productRoom)
    }

    override suspend fun deleteProductRooms() {
        return dao.deleteProductRooms()
    }


}