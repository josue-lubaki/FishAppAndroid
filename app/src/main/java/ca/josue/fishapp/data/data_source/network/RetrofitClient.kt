package ca.josue.fishapp.data.data_source.network

import ca.josue.fishapp.ui.BaseApplication
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class RetrofitClient {
    companion object{
        fun getApiService(): ApiService {
            val gson = GsonBuilder().create()

            return Retrofit
                .Builder()
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(BaseApplication.BASE_URL)
                .build()
                .create(ApiService::class.java)
        }
    }
}