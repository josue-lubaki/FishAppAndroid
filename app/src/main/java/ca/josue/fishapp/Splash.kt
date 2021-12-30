package ca.josue.fishapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import ca.josue.fishapp.services.API
import ca.josue.fishapp.services.ApiInterface
import ca.josue.fishapp.fragment.HomeFragment.Companion.fishListProduct
import ca.josue.fishapp.model.dto.FishModelDTO
import ca.josue.fishapp.model.Product
import com.google.android.material.progressindicator.LinearProgressIndicator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Suppress("DEPRECATION")
class Splash : AppCompatActivity() {
    private lateinit var myProgress: LinearProgressIndicator
    var pStatus = 0
    private val handler = Handler()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        // Desactiver le ToolBar
        supportActionBar?.hide()
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        myProgress = findViewById(R.id.myProgressBar)
        Thread {
            // retrieve all Products
            getAllProductsViaAPI()

            while (pStatus < 100) {
                pStatus += 1
                handler.post { myProgress.progress = pStatus }
                try {
                    // Just to display the progress slowly
                    Thread.sleep(85)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }

            if (myProgress.progress == 100) {
                if(fishListProduct.isEmpty())
                    getAllProductsViaAPI()
                val intent = Intent(this@Splash, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }.start()
    }

    /**
     * Methode qui permet de récupèrer tous les produits
     * */
    private fun getAllProductsViaAPI() {
        val retrofitProduct = API.getApi()?.create(ApiInterface::class.java)?.getProducts()
        retrofitProduct?.enqueue(object : Callback<List<Product>?> {
            override fun onResponse(call: Call<List<Product>?>, response: Response<List<Product>?>) {
                if (!response.isSuccessful)
                    return

                val responseBody = response.body()!!
                for (product in responseBody){
                    val prod = FishModelDTO(
                            product.name,
                            product.category.name,
                            product.price,
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