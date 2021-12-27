package ca.josue.fishapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ca.josue.fishapp.MainActivity
import ca.josue.fishapp.R
import ca.josue.fishapp.adapter.CommandeAdapter
import ca.josue.fishapp.adapter.FishItemDecoration
import ca.josue.fishapp.model.CommandeModel
import ca.josue.fishapp.model.UserModel

class CommandesFragment(private val context : MainActivity) : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

       val commandeList = arrayListOf<CommandeModel>()
//        val orderItem = arrayListOf<String>()
//        orderItem.add("61785b87e5cdff001d816474")
//
//        val user = UserModel(
//                "616de5c0c4da77001dab5a3d",
//                "616de5c0c4da77001dab5a3d",
//                "La Tulipe"
//        )
//        commandeList.add(CommandeModel(
//                0,
//                "61785b87e5cdff001d816478",
//                "1",
//                "De la Paix",
//                "Kinshasa",
//                "Mont-Ngafula",
//                "CD",
//                "2021-10-26T19:48:23.020Z",
//                "61785b87e5cdff001d816478",
//                "Aucune note",
//                orderItem,
//                "2021-10-26T19:48:23.020Z",
//                "61785b87e5cdff001d816478",
//                "1",
//                120,
//                user
//        ))

        val view  = if(commandeList.isNotEmpty())
            inflater.inflate(R.layout.commandes_fragment, container, false)
        else
            inflater.inflate(R.layout.commandes_fragment_empty, container, false)

        if(commandeList.isNotEmpty()) {
            // Retrieve Vertical RecyclerView
            val commandesRecyclerView: RecyclerView = view.findViewById(R.id.vertical_recyclerview_commandes)
            commandesRecyclerView.adapter = CommandeAdapter(context, commandeList)
            commandesRecyclerView.layoutManager = LinearLayoutManager(context)
            commandesRecyclerView.addItemDecoration(FishItemDecoration())
        }

        return view
    }

}