package ca.josue.fishapp.ui.fragment

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import ca.josue.fishapp.R
import ca.josue.fishapp.domain.repository.MyOrderRepository
import ca.josue.fishapp.domain.repository.OrderItemRepository
import ca.josue.fishapp.ui.BaseApplication.Companion.APARTEMENT
import ca.josue.fishapp.ui.BaseApplication.Companion.AVENUE
import ca.josue.fishapp.ui.BaseApplication.Companion.EMAIL
import ca.josue.fishapp.ui.BaseApplication.Companion.ID_USER_CURRENT
import ca.josue.fishapp.ui.BaseApplication.Companion.NAME_USER
import ca.josue.fishapp.ui.BaseApplication.Companion.PASSWORD
import ca.josue.fishapp.ui.BaseApplication.Companion.PHONE
import ca.josue.fishapp.ui.BaseApplication.Companion.TOKEN
import ca.josue.fishapp.ui.activity.Login
import ca.josue.fishapp.ui.activity.MainActivity
import ca.josue.fishapp.ui.util.FragmentUtils.Companion.loadFragment


class ProfileFragment(
    private val context : MainActivity,
    private val myOrderRepository : MyOrderRepository,
    private val orderItemRepository : OrderItemRepository
    ) : Fragment() {

    private lateinit var prefs : SharedPreferences

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.profile_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prefs = context.getSharedPreferences(Login.NAME_PREFERENCE, MODE_PRIVATE);

        // Récupèrer tous les composants
        val nameInput : TextView = view.findViewById(R.id.profile_name_input)
        val emailInput : TextView = view.findViewById(R.id.profile_email_input)
        val adresse : TextView = view.findViewById(R.id.profile_adresse_input)
        val telephoneInput : TextView = view.findViewById(R.id.profile_telephone_input)
        val deconnexionBtn : TextView = view.findViewById(R.id.deconnexion_btn)

        // Setter les informations dont nous disposons déjà
        if(PHONE != null && APARTEMENT != null && AVENUE != null && EMAIL != null) {
            telephoneInput.text = PHONE.toString()
            adresse.text = "$APARTEMENT $AVENUE"
            emailInput.text = EMAIL.toString()
            nameInput.text = NAME_USER.toString()
        }

        // deconnexion
        deconnexionBtn.setOnClickListener {

            // Effacer les informations de la Preferences
            val editor: SharedPreferences.Editor = prefs.edit()
            editor.clear()
            editor.apply()

            Toast.makeText(context, "${NAME_USER.toString()} logged out...", Toast.LENGTH_SHORT).show()

            ID_USER_CURRENT = null
            AVENUE = null
            APARTEMENT = null
            PASSWORD = null
            PHONE = null
            TOKEN = null
            EMAIL = null
            NAME_USER = null

            loadFragment(context, CommandesFragment(context, myOrderRepository, orderItemRepository), R.string.commande_detail_page_title)
            MainActivity.navBar.show(2, true)
        }
    }

}