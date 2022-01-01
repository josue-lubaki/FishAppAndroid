package ca.josue.fishapp.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import ca.josue.fishapp.R
import ca.josue.fishapp.data.network.RetrofitClient
import ca.josue.fishapp.ui.fragment.HomeFragment.Companion.fishListProduct
import ca.josue.fishapp.domain.dto.FishModelResponse
import ca.josue.fishapp.domain.model.Product
import com.airbnb.lottie.Lottie
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.google.android.material.progressindicator.LinearProgressIndicator
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Suppress("DEPRECATION")
class Splash : AppCompatActivity() {
    private lateinit var myProgress: CircularProgressIndicator
    private lateinit var lottieWater : View
    private lateinit var lottieDoor : View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        // Desactiver le ToolBar
        supportActionBar?.hide()
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        //myProgress = findViewById(R.id.myProgressBar)
        lottieWater = findViewById(R.id.animationViewWater)

        CoroutineScope(Dispatchers.Main).launch {
            launch {
                lottieWater.animate()
                    .translationY(lottieWater.height.toFloat()/3)
                    .alpha(1f)
                    .setListener(null)
                getAllProductsViaAPI()
            }
            delay(2000)
            val intent = Intent(this@Splash, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    /**
     * Methode qui permet de récupèrer tous les produits
     * */
    private fun getAllProductsViaAPI() {
        val retrofitProduct = RetrofitClient.getApiService().getProducts()
        retrofitProduct.enqueue(object : Callback<List<Product>?> {
            override fun onResponse(call: Call<List<Product>?>, response: Response<List<Product>?>) {
                if (!response.isSuccessful)
                    return

                val responseBody = response.body()!!
                for (product in responseBody){
                    val prod = FishModelResponse(
                            product.name,
                            product.category.name,
                            product.price,
                            product.image,
                            product.description
                    )
                    fishListProduct.add(prod)
                }

                // TODO enregistrer tous les produits
            }

            override fun onFailure(call: Call<List<Product>?>, t: Throwable) {
                println("Error retrofit ${t.message}")
            }
        })
    }
}