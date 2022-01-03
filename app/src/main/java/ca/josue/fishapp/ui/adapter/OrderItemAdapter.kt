package ca.josue.fishapp.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import ca.josue.fishapp.R
import ca.josue.fishapp.domain.model.OrderItemRoom
import ca.josue.fishapp.domain.model.ProductRoom
import ca.josue.fishapp.ui.BaseApplication
import ca.josue.fishapp.ui.activity.DetailsProduct
import ca.josue.fishapp.ui.activity.MainActivity
import com.bumptech.glide.Glide

class OrderItemAdapter(
    private val context : MainActivity,
    private val listOrderItem : List<ProductRoom>
) : RecyclerView.Adapter<OrderItemAdapter.ViewHolder>(), IAdapter{


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.order_vertical_commande, parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val orderCurrent : ProductRoom = listOrderItem[position]
        holder.title.text = orderCurrent.name
        holder.price.text = orderCurrent.price.toString()
        Glide.with(context).load(orderCurrent.imageUrl).into(holder.imageProduct)

        holder.consultBtn.setOnClickListener {
            BaseApplication.PRODUCTVIEW = orderCurrent
            val intent = Intent(context, DetailsProduct::class.java)
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return listOrderItem.size
    }

    override fun getContext(): MainActivity {
        return context
    }

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val title : TextView = view.findViewById(R.id.order_item_title)
        val price : TextView = view.findViewById(R.id.order_item_price)
        val consultBtn : TextView = view.findViewById(R.id.order_item_consult)
        val imageProduct : ImageView = view.findViewById(R.id.order_item_image)
    }

}