package ca.josue.fishapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import ca.josue.fishapp.ui.BaseApplication.Companion.APARTEMENT
import ca.josue.fishapp.ui.BaseApplication.Companion.AVENUE
import ca.josue.fishapp.ui.BaseApplication.Companion.EMAIL
import ca.josue.fishapp.ui.BaseApplication.Companion.ID_USER_CURRENT
import ca.josue.fishapp.ui.BaseApplication.Companion.NAME_USER
import ca.josue.fishapp.ui.BaseApplication.Companion.PASSWORD
import ca.josue.fishapp.ui.BaseApplication.Companion.PHONE
import ca.josue.fishapp.ui.BaseApplication.Companion.TOKEN
import ca.josue.fishapp.ui.activity.MainActivity
import ca.josue.fishapp.R
import ca.josue.fishapp.domain.repository.MyOrderRepository
import ca.josue.fishapp.ui.util.FragmentUtils.Companion.loadFragment
import javax.inject.Inject

class ProfileFragment(private val context : MainActivity) : Fragment() {

    @Inject
    lateinit var myOrderRepository: MyOrderRepository

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.profile_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
            ID_USER_CURRENT = null
            AVENUE = null
            APARTEMENT = null
            PASSWORD = null
            PHONE = null
            TOKEN = null
            EMAIL = null
            NAME_USER = null

            loadFragment(context, CommandesFragment(context, myOrderRepository), R.string.commande_detail_page_title)
            context.navBar.show(2, true)
        }
    }

}