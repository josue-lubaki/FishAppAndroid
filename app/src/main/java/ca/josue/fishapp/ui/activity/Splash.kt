package ca.josue.fishapp.ui.activity

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import ca.josue.fishapp.R
import ca.josue.fishapp.data.data_source.network.RetrofitClient
import ca.josue.fishapp.domain.api.ProductAPI
import ca.josue.fishapp.domain.model.ProductRoom
import ca.josue.fishapp.ui.activity.Login.Companion.NAME_PREFERENCE
import com.google.android.material.progressindicator.CircularProgressIndicator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@AndroidEntryPoint
class Splash : AppCompatActivity() {
    private lateinit var myProgress: CircularProgressIndicator
    private lateinit var lottieWater : View
    private lateinit var lottieDoor : View
    lateinit var prefs : SharedPreferences

    companion object{
        val fishListProduct = arrayListOf<ProductRoom>()
        /**
         * Methode qui permet de récupèrer tous les produits
         * */
        fun getAllProductsViaAPI() {
            val retrofitProduct = RetrofitClient.getApiService().getProducts()
            retrofitProduct.enqueue(object : Callback<List<ProductAPI>?> {
                override fun onResponse(call: Call<List<ProductAPI>?>, response: Response<List<ProductAPI>?>) {
                    if (!response.isSuccessful)
                        return

                    val list = (response.body() as MutableList<ProductAPI>?)!!
                    list.forEach { product ->
                        val prod = ProductRoom()
                        prod.idProduct = product.id
                        prod.name = product.name
                        prod.category = product.category.name
                        prod.price = product.price.toDouble()
                        prod.imageUrl = product.image
                        prod.description = product.description
                        prod.isFeature = product.isFeatured
                        //prod.dateOrdered = product.dateCreated
                        prod.status = null.toString()

                        fishListProduct.add(prod)
                    }
                }

                override fun onFailure(call: Call<List<ProductAPI>?>, t: Throwable) {
                    println("Error retrofit ${t.message}")
                }
            })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        // Desactiver le ToolBar
        supportActionBar?.hide()
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        myProgress = findViewById(R.id.myProgressBar)
        myProgress.animate()

        prefs = applicationContext.getSharedPreferences(NAME_PREFERENCE, MODE_PRIVATE)

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


}