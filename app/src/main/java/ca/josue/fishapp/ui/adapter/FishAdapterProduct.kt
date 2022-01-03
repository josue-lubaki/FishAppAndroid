package ca.josue.fishapp.ui.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ca.josue.fishapp.ui.activity.MainActivity
import ca.josue.fishapp.R
import ca.josue.fishapp.ui.fragment.FishDetailsFragment
import ca.josue.fishapp.domain.model.ProductRoom
import ca.josue.fishapp.ui.BaseApplication
import ca.josue.fishapp.ui.activity.DetailsProduct
import com.bumptech.glide.Glide

class FishAdapterProduct(
    private val mainContext: MainActivity,
    private val layoutId : Int
        ) : RecyclerView.Adapter<FishAdapterProduct.ViewHolder>(), IAdapter{

    private var fishListDTO : List<ProductRoom> = arrayListOf()

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val fishImage : ImageView = view.findViewById(R.id.order_item_image)
        val fishName : TextView = view.findViewById(R.id.order_item_title)
        val fishPrice : TextView = view.findViewById(R.id.order_item_price)
        val fishConsultButton : TextView = view.findViewById(R.id.order_item_consult)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentFish = fishListDTO[position]

        Glide.with(mainContext).load(Uri.parse(currentFish.imageUrl)).into(holder.fishImage)
        holder.fishName.text = currentFish.name
        holder.fishPrice.text = "$${currentFish.price.toInt()}"

        // Intéragir lors du click sur l'image d'un poisson ou sur le button "consulte" et afficher le popup
        holder.itemView.setOnClickListener {
            BaseApplication.PRODUCTVIEW = currentFish
            val intent = Intent(mainContext, DetailsProduct::class.java)
            mainContext.startActivity(intent)
        }

        holder.fishConsultButton.setOnClickListener {
            BaseApplication.PRODUCTVIEW = currentFish
            val intent = Intent(mainContext, DetailsProduct::class.java)
            mainContext.startActivity(intent)
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    fun addProduct(list : List<ProductRoom>){
        fishListDTO = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = fishListDTO.size
    override fun getContext(): MainActivity {
        return mainContext
    }
}