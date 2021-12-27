package ca.josue.fishapp;

import ca.josue.fishapp.model.MyLogin;
import ca.josue.fishapp.model.UserDTO;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterfaceJ {

    @POST("users/login")
    Call<MyLogin> login(@Body UserDTO user);
}
