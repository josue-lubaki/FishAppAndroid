package ca.josue.fishapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ca.josue.fishapp.BaseApplication.Companion.EMAIL
import ca.josue.fishapp.BaseApplication.Companion.ID_USER_CURRENT
import ca.josue.fishapp.BaseApplication.Companion.PASSWORD
import ca.josue.fishapp.BaseApplication.Companion.TOKEN
import ca.josue.fishapp.MainActivity
import ca.josue.fishapp.R
import ca.josue.fishapp.adapter.CommandeAdapter
import ca.josue.fishapp.adapter.FishItemDecoration
import ca.josue.fishapp.model.*
import ca.josue.fishapp.services.API
import ca.josue.fishapp.services.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommandesFragment(private val context : MainActivity) : Fragment() {

    companion object{
        val commandeList = arrayListOf<MyCommandesItem>()
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
        println("Valeur de ID_USER : $ID_USER_CURRENT" )
        val user = UserDTO("josuelubaki@gmail.com","Heroes")
        val loginBtn : Button = view.findViewById(R.id.ask_login_btn)

        loginBtn.setOnClickListener { v ->
            // Login user
            getLoginUser(user, v)
        }
    }

    /***
     * Methode qui permet de logger un Utilisateur
     * @param user les informations servant de connexion (i.e: email and password)
     */
    private fun getLoginUser(user: UserDTO, view: View) {
        API.getApi()
                ?.create(ApiInterface::class.java)
                ?.login(user)
                ?.enqueue(object : Callback<MyLogin?> {
                    override fun onResponse(call: Call<MyLogin?>, response: Response<MyLogin?>) {
                        if(!response.isSuccessful)
                            return
                        val responseLogin = response.body()!!

                        // Setter l'ID de l'utilisateur courant
                        ID_USER_CURRENT = responseLogin.id
                        TOKEN = responseLogin.token
                        EMAIL = responseLogin.email
                        PASSWORD = user.password

                        println("Je viens de me logger avec succès")
                        println("Voici à present l'id : $ID_USER_CURRENT")
                        if(ID_USER_CURRENT != null){
                            getCommandesUser(view)
                        }
                    }

                    override fun onFailure(call: Call<MyLogin?>, t: Throwable) {
                        println("Erreur lors de Login : ${t.message}")
                    }
                })
    }

    /***
     * Methode qui permet de récupèrer toutes les commandes en cours de l'Utilisateur connecté
     */
    private fun getCommandesUser(view : View) {
        API.getApi()
                ?.create(ApiInterface::class.java)
                ?.getMyCommands(ID_USER_CURRENT!!)
                ?.enqueue(object : Callback<List<MyCommandesItem>?> {
                    override fun onResponse(call: Call<List<MyCommandesItem>?>, response: Response<List<MyCommandesItem>?>) {
                        if(!response.isSuccessful)
                            return

                        val responseList = response.body()!!
                        for (commandItem in responseList){
                            commandeList.add(commandItem)
                        }

                        if(commandeList.isNotEmpty()) {
                            // Changer des Fragments puis revenir
                            context.loadFragment(HomeFragment(context), R.string.home_page_vedette)
                            context.loadFragment(CommandesFragment(context), R.string.commande_detail_page_title)
                        }
                    }

                    override fun onFailure(call: Call<List<MyCommandesItem>?>, t: Throwable) {
                        println("Erreur lors de la recuperation des commandes : ${t.message}")
                    }
                })
    }
}