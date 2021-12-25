package ca.josue.fishapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ca.josue.fishapp.MainActivity
import ca.josue.fishapp.R
import ca.josue.fishapp.model.CommandeModel

class CommandeAdapter(private val context : MainActivity, private val commandeList : List<CommandeModel>) : RecyclerView.Adapter<CommandeAdapter.ViewHolder>() {
    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val name = view.findViewById<TextView>(R.id.home_page_title_article)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_vertical_article, parent, false )
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = commandeList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentCommande = commandeList[position]
        holder.name.text = currentCommande.user.name
    }

}