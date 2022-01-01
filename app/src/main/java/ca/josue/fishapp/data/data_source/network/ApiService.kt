package ca.josue.fishapp.data.data_source.network

import ca.josue.fishapp.domain.model.*
import ca.josue.fishapp.domain.dto.UserLoginResponse
import ca.josue.fishapp.domain.dto.UserLoginDTO
import ca.josue.fishapp.domain.dto.UserInfoResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("products")
    fun getProducts() : Call<List<Product>>

    // https://fish-sales-application.herokuapp.com/api/v1/users/login
    @POST("users/login")
    fun login(@Body user : UserLoginDTO) : Call<UserLoginResponse>

    // https://fish-sales-application.herokuapp.com/api/v1/orders/get/userorder/{id}
    @GET("orders/get/userorder/{id}")
    fun getMyCommands(
            @Path("id") id_user_current : String
    ) : Call<List<MyCommandesItem>>

    @GET("users/{id}")
    fun getInfoUser(
            @Path("id") idUser : String
    ) : Call<UserInfoResponse>

}