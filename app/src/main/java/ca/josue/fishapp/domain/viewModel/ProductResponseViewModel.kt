package ca.josue.fishapp.domain.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.josue.fishapp.domain.dto.ProductResponse
import ca.josue.fishapp.domain.repository.ProductResponseRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProductResponseViewModel @Inject constructor(
    private val productResponseRepository: ProductResponseRepository
) : ViewModel() {

    fun getProductResponses() : LiveData<List<ProductResponse>> {
        return productResponseRepository.getProductResponses()
    }

    suspend fun getProductResponseById(id : String) : ProductResponse? {
        return productResponseRepository.getProductResponseById(id)
    }

    fun insertProdutResponse(productResponse : ProductResponse) {
        return productResponseRepository.insertProductResponse(productResponse)
    }

    suspend fun insertProdutResponses(productResponse : MutableList<ProductResponse>) = viewModelScope.launch {
        productResponseRepository.insertProductResponses(productResponse)
    }

    suspend fun deleteProdutResponse(productResponse : ProductResponse) = viewModelScope.launch  {
        productResponseRepository.deleteProductResponse(productResponse)
    }

    suspend fun deleteProdutResponses() = viewModelScope.launch  {
        productResponseRepository.deleteProductResponses()
    }
}