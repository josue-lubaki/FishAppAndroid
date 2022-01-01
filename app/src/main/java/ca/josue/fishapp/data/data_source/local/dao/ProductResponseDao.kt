package ca.josue.fishapp.data.data_source.local.dao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import ca.josue.fishapp.domain.dto.ProductResponse
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductResponseDao {
    @Query("SELECT * FROM productresponse")
    fun getProductResponses() : LiveData<List<ProductResponse>>

    @Query("SELECT * FROM productresponse WHERE id = :id")
    suspend fun getProductResponseById(id : String) : ProductResponse?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProductResponse(productModel : ProductResponse)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProductResponses(productsModels : MutableList<ProductResponse>)

    @Update
    suspend fun updateProductResponse(productModel: ProductResponse)
    
    @Delete
    suspend fun deleteProductResponse(productModel : ProductResponse)

    @Query("DELETE FROM productresponse")
    suspend fun deleteProductResponses()

}