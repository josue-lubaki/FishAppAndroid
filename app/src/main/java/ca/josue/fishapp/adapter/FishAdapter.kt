package ca.josue.fishapp.adapter

import android.net.Uri
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ca.josue.fishapp.MainActivity
import ca.josue.fishapp.R
import ca.josue.fishapp.fragment.FishDetailsFragment
import ca.josue.fishapp.model.FishModel
import com.bumptech.glide.Glide

class FishAdapter(
        val context: MainActivity,
        private val fishList : List<FishModel>,
        private val layoutId : Int
        ) : RecyclerView.Adapter<FishAdapter.ViewHolder>(){

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val fishImage : ImageView = view.findViewById(R.id.home_page_image_article)
        val fishName : TextView = view.findViewById(R.id.home_page_title_article)
        val fishPrice : TextView = view.findViewById(R.id.home_page_price)
        val fishConsultButton : TextView = view.findViewById(R.id.home_page_consult_btn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentFish = fishList[position]

        Glide.with(context).load(Uri.parse(currentFish.imageUrl)).into(holder.fishImage)
        holder.fishName.text = currentFish.name
        holder.fishPrice.text = "$${currentFish.price}"

        // Intéragir lors du click sur l'image d'un poisson ou sur le button "consulte" et afficher le popup
        holder.itemView.setOnClickListener {
            FishDetailsFragment(this, currentFish).show()
        }

        holder.fishConsultButton.setOnClickListener {
            FishDetailsFragment(this, currentFish).show()
        }
    }

    override fun getItemCount(): Int = fishList.size
}