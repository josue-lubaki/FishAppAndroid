//package ca.josue.fishapp;
//
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;
//import retrofit2.converter.scalars.ScalarsConverterFactory;
//
//public class MyApi {
//    public static Retrofit api = null;
//
//    public static Retrofit getApi(){
//        if(api == null){
//            Gson gson = new GsonBuilder().create();
//
//            api =  new Retrofit.Builder()
//                    .baseUrl("https://fish-sales-application.herokuapp.com/api/v1/")
//                    .addConverterFactory(ScalarsConverterFactory.create())
//                    .addConverterFactory(GsonConverterFactory.create(gson))
//                    .build();
//        }
//
//        return api;
//    }
//
//}
