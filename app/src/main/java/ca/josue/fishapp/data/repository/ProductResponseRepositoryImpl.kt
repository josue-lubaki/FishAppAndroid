package ca.josue.fishapp.data.repository

import androidx.lifecycle.LiveData
import ca.josue.fishapp.data.data_source.local.dao.ProductResponseDao
import ca.josue.fishapp.domain.dto.ProductResponse
import ca.josue.fishapp.domain.model.Product
import ca.josue.fishapp.domain.repository.ProductResponseRepository

class ProductResponseRepositoryImpl(
    private val dao : ProductResponseDao
) : ProductResponseRepository {
    override fun getProductResponses(): LiveData<List<ProductResponse>> {
        return dao.getProductResponses()
    }

    override suspend fun getProductResponseById(id: String): ProductResponse? {
        return dao.getProductResponseById(id)
    }

    override fun insertProductResponse(productResponse: ProductResponse) {
        return dao.insertProductResponse(productResponse)
    }

    override suspend fun deleteProductResponse(productResponse: ProductResponse) {
        return dao.deleteProductResponse(productResponse)
    }

    override suspend fun deleteProductResponses() {
        return dao.deleteProductResponses()
    }

    override suspend fun insertProductResponses(productResponses: MutableList<ProductResponse>) {
        return dao.insertProductResponses(productResponses)
    }
}