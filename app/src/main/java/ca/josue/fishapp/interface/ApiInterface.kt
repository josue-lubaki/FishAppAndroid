package ca.josue.fishapp.`interface`

import ca.josue.fishapp.model.CommandeModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("")
    fun getData() : Call<List<CommandeModel>>
}