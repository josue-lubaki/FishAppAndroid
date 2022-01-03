package ca.josue.fishapp.ui.adapter

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ca.josue.fishapp.R
import ca.josue.fishapp.domain.model.ProductRoom
import ca.josue.fishapp.ui.BaseApplication.Companion.PRODUCTVIEW
import ca.josue.fishapp.ui.activity.DetailsProduct
import ca.josue.fishapp.ui.activity.MainActivity
import com.bumptech.glide.Glide

class FishAdapterOrder(
    private val context: MainActivity,
    private val listProduct : List<ProductRoom>
    ) : RecyclerView.Adapter<FishAdapterOrder.ViewHolder>(), IAdapter {

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val fishImage : ImageView = view.findViewById(R.id.order_item_image)
        val fishName : TextView = view.findViewById(R.id.order_item_title)
        val fishPrice : TextView = view.findViewById(R.id.order_item_price)
        val fishConsultButton : TextView = view.findViewById(R.id.order_item_consult)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_vertical_article, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentProduct : ProductRoom = listProduct[position]

        // bind data
        holder.fishName.text = currentProduct.name
        holder.fishPrice.text = "$${currentProduct.price.toInt()}"
        Glide.with(context).load(currentProduct.imageUrl).into(holder.fishImage)

        holder.fishConsultButton.setOnClickListener {
            val intent = Intent(context, DetailsProduct::class.java)
            PRODUCTVIEW = currentProduct
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return listProduct.size
    }

    override fun getContext(): MainActivity {
        return context
    }
}