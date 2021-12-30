package ca.josue.fishapp.services

import ca.josue.fishapp.model.*
import ca.josue.fishapp.model.dto.MyLoginDTO
import ca.josue.fishapp.model.dto.UserDTO
import ca.josue.fishapp.model.dto.UserInfoDTO
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {
    @GET("products")
    fun getProducts() : Call<List<Product>>

    // https://fish-sales-application.herokuapp.com/api/v1/users/login
    @POST("users/login")
    fun login(@Body user : UserDTO) : Call<MyLoginDTO>

    // https://fish-sales-application.herokuapp.com/api/v1/orders/get/userorder/{id}
    @GET("orders/get/userorder/{id}")
    fun getMyCommands(
            @Path("id") id_user_current : String
    ) : Call<List<MyCommandesItem>>

    @GET("users/{id}")
    fun getInfoUser(
            @Path("id") idUser : String
    ) : Call<UserInfoDTO>

}