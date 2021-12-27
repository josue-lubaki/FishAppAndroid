package ca.josue.fishapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import ca.josue.fishapp.BaseApplication
import ca.josue.fishapp.BaseApplication.Companion.APARTEMENT
import ca.josue.fishapp.BaseApplication.Companion.AVENUE
import ca.josue.fishapp.BaseApplication.Companion.BASE_URL
import ca.josue.fishapp.BaseApplication.Companion.PHONE
import ca.josue.fishapp.MainActivity
import ca.josue.fishapp.MainActivity.Companion.navBar
import ca.josue.fishapp.R

class ProfileFragment(private val context : MainActivity) : Fragment() {

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
        val deconnexionBtn : Button = view.findViewById(R.id.deconnexion_btn)

        // Setter les informations dont nous disposons déjà
        if(PHONE != null && APARTEMENT != null && AVENUE != null) {
            telephoneInput.text = PHONE.toString()
            adresse.text = "$APARTEMENT $AVENUE"
        }

        // deconnexion
        deconnexionBtn.setOnClickListener {
            BaseApplication.ID_USER_CURRENT = null
            BaseApplication.AVENUE = null
            BaseApplication.APARTEMENT = null
            BaseApplication.PASSWORD = null
            BaseApplication.PHONE = null
            BaseApplication.TOKEN = null
            BaseApplication.EMAIL = null

            context.loadFragment(CommandesFragment(context), R.string.commande_detail_page_title)
            navBar.show(2, true)
        }
    }

}