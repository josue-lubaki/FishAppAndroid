package ca.josue.fishapp.services

import ca.josue.fishapp.model.Product
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("products")
    fun getProducts() : Call<List<Product>>
}