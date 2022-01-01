package ca.josue.fishapp.ui.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
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
import ca.josue.fishapp.domain.dto.UserLoginDTO
import ca.josue.fishapp.domain.repository.MyOrderRepository
import ca.josue.fishapp.domain.viewModel.MyOrderViewModel
import ca.josue.fishapp.ui.BaseApplication.Companion.EMAIL
import ca.josue.fishapp.ui.BaseApplication.Companion.NAME_USER
import ca.josue.fishapp.ui.activity.Splash
import ca.josue.fishapp.ui.adapter.FishAdapter
import ca.josue.fishapp.ui.util.FragmentUtils.Companion.loadFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class CommandesFragment(
    private val context : MainActivity,
    val myOrderRepository : MyOrderRepository
    ) : Fragment() {

    private var myOrderVM = MyOrderViewModel(myOrderRepository)
    private var commandesRecyclerView : RecyclerView? = null

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

                    }

                    override fun onFailure(call: Call<List<MyCommandesItem>?>, t: Throwable) {
                        println("Erreur lors de la recuperation des commandes : ${t.message}")
                    }
                })
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context as MainActivity).title = R.string.commande_page_title.toString()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return when {
            productDTOList.isNotEmpty() && ID_USER_CURRENT != null  -> inflater.inflate(R.layout.commandes_fragment, container, false)
            ID_USER_CURRENT == null -> inflater.inflate(R.layout.commandes_fragment_to_login, container, false)
            productDTOList.isEmpty() && ID_USER_CURRENT == null -> inflater.inflate(R.layout.commandes_fragment_empty, container, false)
            else -> inflater.inflate(R.layout.commandes_fragment_to_login, container, false)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Vérifier les données de la préference
        val loginBtn : TextView = view.findViewById(R.id.ask_login_btn)
        val preferences = context.getSharedPreferences(Login.NAME_PREFERENCE, Context.MODE_PRIVATE)
        val emailPrefs = preferences.getString(getString(R.string.save_username_key), null)
        val passwordPrefs = preferences.getString(getString(R.string.save_password_key), null)
        val idUserCurrent = preferences.getString("ID_USER_CURRENT", null)
        val nameUser = preferences.getString("NAME_USER", null)

        if(emailPrefs != null && passwordPrefs != null && idUserCurrent != null && nameUser != null) {
            ID_USER_CURRENT = idUserCurrent
            NAME_USER = nameUser
            EMAIL = emailPrefs
        }

       if(productDTOList.isNotEmpty()){

           commandesRecyclerView = view.findViewById(R.id.vertical_recyclerview_commandes)

           commandesRecyclerView?.layoutManager = LinearLayoutManager(context)
           commandesRecyclerView?.addItemDecoration(FishItemDecoration())

           runBlocking(Dispatchers.Default){
               myOrderVM.insertMyOrders(productDTOList)
           }
       }

        myOrderVM.getMyOrders().observe(this.viewLifecycleOwner) { myOrderList ->
            commandesRecyclerView?.adapter = CommandeAdapter(context, myOrderList)
        }

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
                MainActivity.navBar.show(1, true)
                Toast.makeText(context, "Connexion réussie", Toast.LENGTH_LONG).show()
            }
        }
    }
}