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
import ca.josue.fishapp.domain.model.MyOrdersRoom
import ca.josue.fishapp.data.data_source.network.RetrofitClient
import ca.josue.fishapp.domain.api.OrderItemAPI
import ca.josue.fishapp.domain.api.OrdersAPI
import ca.josue.fishapp.domain.model.OrderItemRoom
import ca.josue.fishapp.domain.repository.MyOrderRepository
import ca.josue.fishapp.domain.repository.OrderItemRepository
import ca.josue.fishapp.domain.viewModel.MyOrderViewModel
import ca.josue.fishapp.domain.viewModel.OrderItemViewModel
import ca.josue.fishapp.ui.BaseApplication.Companion.EMAIL
import ca.josue.fishapp.ui.BaseApplication.Companion.NAME_USER
import ca.josue.fishapp.ui.activity.Login.Companion.getInformationUser
import ca.josue.fishapp.ui.activity.Splash
import ca.josue.fishapp.ui.util.FragmentUtils.Companion.convertDate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommandesFragment(
    private val context : MainActivity,
    myOrderRepository : MyOrderRepository,
    private val orderItemRepository : OrderItemRepository
    ) : Fragment() {

    private var myOrderVM = MyOrderViewModel(myOrderRepository)
    private var orderItemVM = OrderItemViewModel(orderItemRepository)
    private var commandesRecyclerView : RecyclerView? = null

    companion object{
        val ordersItemsListRoom = arrayListOf<OrderItemRoom>()
        val ordersListRoom = arrayListOf<MyOrdersRoom>()

        /***
         * Methode qui permet de récupèrer toutes les commandes en cours de l'Utilisateur connecté
         */
        fun getCommandesUser() {
            RetrofitClient
                .getApiService()
                .getMyCommands(ID_USER_CURRENT!!)
                .enqueue(object : Callback<List<OrdersAPI>?> {
                    override fun onResponse(call: Call<List<OrdersAPI>?>, response: Response<List<OrdersAPI>?>) {
                        if (!response.isSuccessful)
                            return

                        val responseList = response.body()!!

                        responseList.forEach { commande : OrdersAPI ->
                            val orderCurrentUser = MyOrdersRoom()
                            orderCurrentUser.idUser = commande.user
                            orderCurrentUser.apartment = commande.apartment
                            orderCurrentUser.avenue = commande.avenue
                            orderCurrentUser.idOrder = commande.id
                            orderCurrentUser.city = commande.city
                            orderCurrentUser.dateOrdered = convertDate(commande.dateOrdered)
                            orderCurrentUser.phone = commande.phone
                            orderCurrentUser.status = commande.status
                            orderCurrentUser.totalPrice = commande.totalPrice
                            // Add to list
                            ordersListRoom.add(orderCurrentUser)

                            // Pour Chaque Item de la commande
                            commande.orderItems.forEach { orderItem : OrderItemAPI ->
                                val orderItemCurrentUser = OrderItemRoom()
                                orderItemCurrentUser.idOrder = commande.id
                                orderItemCurrentUser.idOrderItem = orderItem.id
                                orderItemCurrentUser.idProduct = orderItem.product.id
                                orderItemCurrentUser.quantity = orderItem.quantity

                                // Add to list
                                ordersItemsListRoom.add(orderItemCurrentUser)
                            }
                        }

                        if (ordersListRoom.isNotEmpty()){
                            // récupèrer le phone
                            PHONE = ordersListRoom[0].phone
                            APARTEMENT = ordersListRoom[0].apartment
                            AVENUE = ordersListRoom[0].avenue
                        }
                    }

                    override fun onFailure(call: Call<List<OrdersAPI>?>, t: Throwable) {
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
        return when{
            ordersListRoom.isNotEmpty() && ID_USER_CURRENT != null  -> inflater.inflate(R.layout.commandes_fragment, container, false)
            ID_USER_CURRENT == null -> inflater.inflate(R.layout.commandes_fragment_to_login, container, false)
            ordersListRoom.isEmpty() && ID_USER_CURRENT != null -> inflater.inflate(R.layout.commandes_fragment_empty, container, false)
            else -> inflater.inflate(R.layout.commandes_fragment_to_login, container, false)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val loginBtn : TextView = view.findViewById(R.id.ask_login_btn)

       if(ordersListRoom.isNotEmpty() && ID_USER_CURRENT != null){

           commandesRecyclerView = view.findViewById(R.id.vertical_recyclerview_commandes)

           commandesRecyclerView?.layoutManager = LinearLayoutManager(context)
           commandesRecyclerView?.addItemDecoration(FishItemDecoration())

           // enregistrer les contenus dans Room
           runBlocking(Dispatchers.Default){
               myOrderVM.insertMyOrders(ordersListRoom)
               orderItemVM.insertOrderItems(ordersItemsListRoom)
           }

           myOrderVM.getMyOrders().observe(this.viewLifecycleOwner) { myOrderList ->
               commandesRecyclerView?.adapter = CommandeAdapter(context, myOrderList, orderItemRepository)
           }
       }

        loginBtn.setOnClickListener {
            // Si l'Utilisateur n'est pas encore connecté
            if(ID_USER_CURRENT == null) {
                val intent = Intent(context.baseContext, Login::class.java)
                startActivity(intent)
            }
            else{
                // retrieve All commands for user
                ordersListRoom.clear()
                getCommandesUser()
                ID_USER_CURRENT?.let{ getInformationUser(it) }
                MainActivity.navBar.show(1, true)
                Toast.makeText(context, "Connexion réussie", Toast.LENGTH_LONG).show()
            }
        }
    }
}