package ca.josue.fishapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ca.josue.fishapp.domain.dto.FishModelResponse
import ca.josue.fishapp.domain.model.Product
import kotlinx.coroutines.flow.Flow

@Dao
interface FishModelDao {
    @Query("SELECT * FROM fishmodelresponse")
    fun getProducts() : Flow<List<FishModelResponse>>

    @Query("SELECT * FROM fishmodelresponse WHERE id = :id")
    suspend fun getProduct(id : String) : FishModelResponse?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProductModel(productModel : FishModelResponse)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProductsModels(productsModels : List<FishModelResponse>)

}