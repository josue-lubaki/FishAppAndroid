package ca.josue.fishapp;

import java.util.List;

import ca.josue.fishapp.model.Product;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("products")
    abstract Call<List<Product>> getProducts();
}
