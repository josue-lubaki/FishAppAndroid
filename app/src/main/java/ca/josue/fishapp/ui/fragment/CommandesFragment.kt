package ca.josue.fishapp.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ca.josue.fishapp.ui.BaseApplication.Companion.APARTEMENT
import ca.josue.fishapp.ui.BaseApplication.Companion.AVENUE
import ca.josue.fishapp.ui.BaseApplication.Companion.ID_USER_CURRENT
import ca.josue.fishapp.ui.BaseApplication.Companion.PHONE
import ca.josue.fishapp.ui.activity.Login
import ca.josue.fishapp.ui.activity.MainActivity
import ca.josue.fishapp.R
import ca.josue.fishapp.ui.adapter.CommandeAdapter
import ca.josue.fishapp.ui.adapter.FishItemDecoration
import ca.josue.fishapp.domain.model.*
import ca.josue.fishapp.domain.dto.MyOrderDTO
import ca.josue.fishapp.data.data_source.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommandesFragment(private val context : MainActivity) : Fragment() {

    companion object{
        val commandeList = arrayListOf<MyCommandesItem>()
        val productDTOList = arrayListOf<MyOrderDTO>()

        /***
         * Methode qui permet de récupèrer toutes les commandes en cours de l'Utilisateur connecté
         */
        fun getCommandesUser() {
            RetrofitClient
                .getApiService()
                .getMyCommands(ID_USER_CURRENT!!)
                .enqueue(object : Callback<List<MyCommandesItem>?> {
                    override fun onResponse(call: Call<List<MyCommandesItem>?>, response: Response<List<MyCommandesItem>?>) {
                        if (!response.isSuccessful)
                            return

                        val responseList = response.body()!!
                        for (order in responseList) {
                            commandeList.add(order)
                        }

                        responseList.forEach {myCommandItem ->
                            myCommandItem.orderItems.forEach {orderItem ->
                                val product = MyOrderDTO()
                                product.status = myCommandItem.status
                                product.quantity = orderItem.quantity
                                product.name = orderItem.product.name
                                product.description = orderItem.product.description
                                product.price = orderItem.product.price
                                product.imageURL = orderItem.product.image
                                product.idOrderItem = orderItem.id
                                product.category = orderItem.product.category.name

                                // Add to list
                                productDTOList.add(product)
                            }
                        }

                        // récupèrer le phone
                        PHONE = commandeList[0].phone
                        APARTEMENT = commandeList[0].apartment
                        AVENUE = commandeList[0].avenue

//                        if(commandeList.isNotEmpty()) {
//                            // Changer des Fragments puis revenir
//                            context.loadFragment(HomeFragment(context), R.string.home_page_vedette)
//                            context.loadFragment(CommandesFragment(context), R.string.commande_detail_page_title)
//                        }
                    }

                    override fun onFailure(call: Call<List<MyCommandesItem>?>, t: Throwable) {
                        println("Erreur lors de la recuperation des commandes : ${t.message}")
                    }
                })
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return when {
            productDTOList.isNotEmpty() && ID_USER_CURRENT != null  -> inflater.inflate(R.layout.commandes_fragment, container, false)
            ID_USER_CURRENT == null -> inflater.inflate(R.layout.commandes_fragment_to_login, container, false)
            else -> inflater.inflate(R.layout.commandes_fragment_empty, container, false)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(productDTOList.isNotEmpty()) {
            // Retrieve Vertical RecyclerView
            val commandesRecyclerView : RecyclerView= view.findViewById(R.id.vertical_recyclerview_commandes)
            commandesRecyclerView.adapter = CommandeAdapter(context, productDTOList)
            commandesRecyclerView.layoutManager = LinearLayoutManager(context)
            commandesRecyclerView.addItemDecoration(FishItemDecoration())
        }

        // Vérifier l'utilisateur est déjà connecté
        if(ID_USER_CURRENT != null){
            //commandeList.clear()
            //getCommandesUser()
        }else {
            val loginBtn : TextView = view.findViewById(R.id.ask_login_btn)

            loginBtn.setOnClickListener {
                // Si l'Utilisateur n'est pas encore connecté
                if(ID_USER_CURRENT == null) {
                    val intent = Intent(context.baseContext, Login::class.java)
                    startActivity(intent)
                    context.finish()
                }
                else{
                    // retrieve All commands for user
                    productDTOList.clear()
                    getCommandesUser()
                }
            }
        }
    }
}