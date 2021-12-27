package ca.josue.fishapp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class myAPI {
    public static Retrofit api = null;

    public static Retrofit getApi(){
       if(api == null){
           Gson gson = new GsonBuilder().create();

           api = new Retrofit.Builder()
                   .baseUrl(BaseApplication.BASE_URL)
                   .addConverterFactory(GsonConverterFactory.create(gson))
                   .build();
       }

       return api;
    }
}
