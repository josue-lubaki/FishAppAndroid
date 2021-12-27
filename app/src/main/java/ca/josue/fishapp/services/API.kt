package ca.josue.fishapp.services

import ca.josue.fishapp.BaseApplication
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class API {
    companion object{
        private var api: Retrofit? = null

        @JvmName("getApiFish")
        fun getApi(): Retrofit? {
            if(api == null){
                val gson = GsonBuilder().create()

                api = Retrofit.Builder()
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .baseUrl(BaseApplication.BASE_URL)
                        .build()
            }
            return api
        }
    }
}