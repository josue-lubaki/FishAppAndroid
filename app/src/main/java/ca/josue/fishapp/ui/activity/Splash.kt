package ca.josue.fishapp.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import ca.josue.fishapp.R
import ca.josue.fishapp.data.data_source.network.RetrofitClient
import ca.josue.fishapp.domain.dto.ProductResponse
import ca.josue.fishapp.domain.model.Product
import ca.josue.fishapp.domain.repository.ProductResponseRepository
import com.google.android.material.progressindicator.CircularProgressIndicator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Thread.sleep
import javax.inject.Inject

@Suppress("DEPRECATION")
class Splash : AppCompatActivity() {
    private lateinit var myProgress: CircularProgressIndicator
    private lateinit var lottieWater : View
    private lateinit var lottieDoor : View

    companion object{
        val fishListProduct = arrayListOf<ProductResponse>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        // Desactiver le ToolBar
        supportActionBar?.hide()
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        //myProgress = findViewById(R.id.myProgressBar)
        lottieWater = findViewById(R.id.animationViewWater)
        CoroutineScope(Dispatchers.Default).launch {
                launch {
                    lottieWater.animate()
                        .translationY(lottieWater.height.toFloat()/3)
                        .alpha(1f)
                        .setListener(null)
                    getAllProductsViaAPI()
                }
            delay(2500)
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

                val list = (response.body() as MutableList<Product>?)!!
                list.forEach { product ->
                    val prod = ProductResponse(
                        product.id,
                        product.name,
                        product.category.name,
                        product.price.toDouble(),
                        product.image,
                        product.description
                    )
                    fishListProduct.add(prod)
                }
            }

            override fun onFailure(call: Call<List<Product>?>, t: Throwable) {
                println("Error retrofit ${t.message}")
            }
        })
    }
}