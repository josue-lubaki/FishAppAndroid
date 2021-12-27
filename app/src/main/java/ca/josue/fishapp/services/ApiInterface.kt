package ca.josue.fishapp.services

import ca.josue.fishapp.model.MyLogin
import ca.josue.fishapp.model.Product
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterface {
    @GET("products")
    fun getProducts() : Call<List<Product>>

    @POST("login")
    fun login(email : String, password : String) : Call<MyLogin>
}