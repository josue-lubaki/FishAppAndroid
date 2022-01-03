package ca.josue.fishapp.data.data_source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import ca.josue.fishapp.domain.model.ProductRoom

@Dao
interface ProductRoomDao {
    @Query("SELECT * FROM productroom")
    fun getProductRooms() : LiveData<List<ProductRoom>>

    @Query("SELECT * FROM productroom WHERE idProduct = :id")
    suspend fun getProductRoomById(id : String) : ProductRoom?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProductRoom(productModel : ProductRoom)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProductRooms(productsModels : MutableList<ProductRoom>)


    @Update
    suspend fun updateProductRoom(productModel: ProductRoom)
    
    @Delete
    suspend fun deleteProductRoom(productModel : ProductRoom)

    @Query("DELETE FROM productroom")
    suspend fun deleteProductRooms()

}