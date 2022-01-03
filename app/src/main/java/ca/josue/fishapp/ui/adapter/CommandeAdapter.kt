package ca.josue.fishapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import ca.josue.fishapp.ui.activity.MainActivity
import ca.josue.fishapp.R
import ca.josue.fishapp.ui.fragment.FishDetailsFragment
import ca.josue.fishapp.domain.model.ProductRoom
import ca.josue.fishapp.domain.model.MyOrdersRoom
import ca.josue.fishapp.domain.repository.OrderItemRepository
import ca.josue.fishapp.ui.adapter.util.Companion.whichState
import ca.josue.fishapp.ui.util.FragmentUtils.Companion.loadFragment
import com.bumptech.glide.Glide

class CommandeAdapter(
    private val mainContext : MainActivity,
    private val orderListRoom : List<MyOrdersRoom>,
    private val orderItemRepository : OrderItemRepository
        ) : RecyclerView.Adapter<CommandeAdapter.ViewHolder>(), IAdapter {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_vertical_commande, parent, false )
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = orderListRoom.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentCommande = orderListRoom[position]
        val currentFish = ProductRoom()
        currentFish.idProduct = currentCommande.idOrder
        holder.totalPrice.text = "$${currentCommande.totalPrice.toInt()}"
        holder.status.text = whichState(currentCommande.status)
        val date = currentCommande.dateOrdered
        holder.dateOrdered.text = date

        holder.consultBtn.setOnClickListener {
            loadFragment(mainContext, FishDetailsFragment(mainContext, currentCommande.idOrder, orderItemRepository),R.string.commande_page_title)

        }

        holder.blocOrder.setOnClickListener {
            loadFragment(mainContext, FishDetailsFragment(mainContext, currentCommande.idOrder, orderItemRepository),R.string.commande_page_title)
        }
    }

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val totalPrice : TextView = view.findViewById(R.id.commande_page_total_price)
        val status : TextView = view.findViewById(R.id.commande_page_status)
        val consultBtn : TextView = view.findViewById(R.id.commande_page_consult_btn)
        val dateOrdered : TextView = view.findViewById(R.id.commande_page_date_ordered)
        val blocOrder : ConstraintLayout = view.findViewById(R.id.bloc_order)
    }

    override fun getContext(): MainActivity {
        return mainContext
    }

}