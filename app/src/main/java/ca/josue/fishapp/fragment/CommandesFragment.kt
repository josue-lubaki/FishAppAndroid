package ca.josue.fishapp.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ca.josue.fishapp.BaseApplication.Companion.APARTEMENT
import ca.josue.fishapp.BaseApplication.Companion.AVENUE
import ca.josue.fishapp.BaseApplication.Companion.ID_USER_CURRENT
import ca.josue.fishapp.BaseApplication.Companion.PHONE
import ca.josue.fishapp.Login
import ca.josue.fishapp.MainActivity
import ca.josue.fishapp.R
import ca.josue.fishapp.adapter.CommandeAdapter
import ca.josue.fishapp.adapter.FishItemDecoration
import ca.josue.fishapp.fragment.CommandesFragment.Companion.commandeList
import ca.josue.fishapp.model.*
import ca.josue.fishapp.services.API
import ca.josue.fishapp.services.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommandesFragment(private val context : MainActivity) : Fragment() {

    companion object{
        val commandeList = arrayListOf<MyCommandesItem>()

        /***
         * Methode qui permet de récupèrer toutes les commandes en cours de l'Utilisateur connecté
         */
        fun getCommandesUser() {
            API.getApi()
                    ?.create(ApiInterface::class.java)
                    ?.getMyCommands(ID_USER_CURRENT!!)
                    ?.enqueue(object : Callback<List<MyCommandesItem>?> {
                        override fun onResponse(call: Call<List<MyCommandesItem>?>, response: Response<List<MyCommandesItem>?>) {
                            if (!response.isSuccessful)
                                return

                            val responseList = response.body()!!
                            for (commandItem in responseList) {
                                commandeList.add(commandItem)
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
            commandeList.isNotEmpty() && ID_USER_CURRENT != null  -> inflater.inflate(R.layout.commandes_fragment, container, false)
            ID_USER_CURRENT == null -> inflater.inflate(R.layout.commandes_fragment_to_login, container, false)
            else -> inflater.inflate(R.layout.commandes_fragment_empty, container, false)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(commandeList.isNotEmpty()) {
            // Retrieve Vertical RecyclerView
            val commandesRecyclerView : RecyclerView= view.findViewById(R.id.vertical_recyclerview_commandes)
            commandesRecyclerView.adapter = CommandeAdapter(context, commandeList)
            commandesRecyclerView.layoutManager = LinearLayoutManager(context)
            commandesRecyclerView.addItemDecoration(FishItemDecoration())
        }

        // Vérifier l'utilisateur est déjà connecté
        if(ID_USER_CURRENT != null){
            //commandeList.clear()
            //getCommandesUser()
        }else {
            val loginBtn : TextView = view.findViewById(R.id.ask_login_btn)

            loginBtn.setOnClickListener { v ->
                // Si l'Utilisateur n'est pas encore connecté
                if(ID_USER_CURRENT == null) {
                    val intent = Intent(context.baseContext, Login::class.java)
                    startActivity(intent)
                    context.finish()
                }
                else{
                    // retrieve All commands for user
                    commandeList.clear()
                    getCommandesUser()
                }
            }
        }
    }





}