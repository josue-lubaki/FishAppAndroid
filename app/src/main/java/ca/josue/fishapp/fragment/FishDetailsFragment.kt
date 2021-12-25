package ca.josue.fishapp.fragment

import android.app.Dialog
import android.net.Uri
import android.os.Bundle
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import ca.josue.fishapp.R
import ca.josue.fishapp.adapter.FishAdapter
import ca.josue.fishapp.model.FishModel
import com.bumptech.glide.Glide

class FishDetailsFragment(
        private val adapter : FishAdapter,
        private val currentFish : FishModel
        ) : Dialog(adapter.context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.popup_fish_details)
        setComponent()
    }

    private fun setComponent() {
        // Actualiser les informations du poisson
        val fishImage : ImageView = findViewById(R.id.commande_detail_image)
        Glide.with(adapter.context).load(Uri.parse(currentFish.imageUrl)).into(fishImage)
        findViewById<TextView>(R.id.commande_detail_title_article).text = currentFish.name
        findViewById<TextView>(R.id.commande_detail_description).text = currentFish.description
        findViewById<TextView>(R.id.commande_detail_price).text = "$${currentFish.price}"
    }

}