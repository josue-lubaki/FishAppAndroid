package ca.josue.fishapp;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import ca.josue.fishapp.model.Product;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Main extends AppCompatActivity {
    private ImageView logo;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logo = findViewById(R.id.imageView);

        logo.setOnClickListener(v ->
           getData()
        );
    }

    private void getData() {
        myAPI.getApi().create(ApiInterface.class).getProducts().enqueue(new Callback<List<Product>>(){

            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if(!response.isSuccessful()){
                    return;
                }

                List<Product> prod = response.body();
                assert prod != null;
                System.out.println("La Taille de la liste est " + prod.size());
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });
    }
}
