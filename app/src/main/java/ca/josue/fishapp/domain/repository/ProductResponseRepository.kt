package ca.josue.fishapp.domain.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ca.josue.fishapp.domain.dto.ProductResponse
import ca.josue.fishapp.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductResponseRepository {
    fun getProductResponses() : LiveData<List<ProductResponse>>
    suspend fun getProductResponseById(id : String) : ProductResponse?
    fun insertProductResponse(productResponse : ProductResponse)
    suspend fun deleteProductResponse(productResponse : ProductResponse)
    suspend fun deleteProductResponses()
    suspend fun insertProductResponses(productResponse: MutableList<ProductResponse>)
}