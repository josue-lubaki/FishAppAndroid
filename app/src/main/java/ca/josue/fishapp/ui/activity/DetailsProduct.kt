package ca.josue.fishapp.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import ca.josue.fishapp.R
import ca.josue.fishapp.domain.model.ProductRoom
import ca.josue.fishapp.ui.BaseApplication
import ca.josue.fishapp.ui.BaseApplication.Companion.PRODUCTVIEW
import ca.josue.fishapp.ui.adapter.util.Companion.whichState
import com.bumptech.glide.Glide

class DetailsProduct : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_product)

        supportActionBar?.hide()
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        val title : TextView = findViewById(R.id.detail_title_article)
        val price : TextView = findViewById(R.id.detail_price)
        val category : TextView = findViewById(R.id.detail_category)
        val status : TextView = findViewById(R.id.detail_status)
        val description : TextView = findViewById(R.id.detail_description)
        val image : ImageView = findViewById(R.id.detail_image)
        val closeBtn : TextView = findViewById(R.id.fermer_btn)

        if(PRODUCTVIEW != null){
            val product = PRODUCTVIEW!!
            title.text = product.name
            price.text = "$${product.price.toInt()}"
            category.text = product.category
            status.text = whichState(product.status)
            description.text = product.description
            Glide.with(this).load(product.imageUrl).into(image)

            closeBtn.setOnClickListener {
                val intent = Intent(this@DetailsProduct, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}