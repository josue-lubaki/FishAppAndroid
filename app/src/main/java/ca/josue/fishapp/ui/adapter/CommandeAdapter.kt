package ca.josue.fishapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ca.josue.fishapp.ui.activity.MainActivity
import ca.josue.fishapp.R
import ca.josue.fishapp.ui.fragment.FishDetailsFragment
import ca.josue.fishapp.domain.dto.ProductResponse
import ca.josue.fishapp.domain.dto.MyOrderDTO
import com.bumptech.glide.Glide

class CommandeAdapter(
    private val mainContext : MainActivity,
    private val productDTOList : List<MyOrderDTO>
        ) : RecyclerView.Adapter<CommandeAdapter.ViewHolder>(), IAdapter {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_vertical_article, parent, false )
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = productDTOList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentCommande = productDTOList[position]
        val currentFish = ProductResponse(
                id = currentCommande.idOrderItem,
                name = currentCommande.name,
                category = currentCommande.category,
                price = currentCommande.price as Double,
                imageUrl = currentCommande.imageURL,
                description = currentCommande.description,
                isFeature = currentCommande.isFeature
        )

        holder.name.text = currentCommande.name
        Glide.with(mainContext).load(currentCommande.imageURL).into(holder.image)
        holder.price.text = "$${currentCommande.price}"

        holder.consultBtn.setOnClickListener {
            FishDetailsFragment(this, currentFish).show()
        }

        //notifyItemChanged(position)
    }

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val name: TextView = view.findViewById(R.id.home_page_title_article)
        val image : ImageView = view.findViewById(R.id.home_page_image_article)
        val price : TextView = view.findViewById(R.id.home_page_price)
        val consultBtn : TextView = view.findViewById(R.id.home_page_consult_btn)
    }

    override fun getContext(): MainActivity {
        return mainContext
    }

}