package ca.josue.fishapp

import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ca.josue.fishapp.fragment.CommandesFragment
import ca.josue.fishapp.fragment.HomeFragment
import ca.josue.fishapp.fragment.ProfileFragment
import com.etebarian.meowbottomnavigation.MeowBottomNavigation

class MainActivity : AppCompatActivity() {
    private val LOG_TAG = MainActivity::class.java.name

    companion object{
        lateinit var navBar : MeowBottomNavigation
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // get content bundle
//        val bundle = intent.extras
//
//        var goToCommandeFragment = bundle?.getBoolean(GOTO_COMMANDES)

        navBar = findViewById(R.id.navBar)
        supportActionBar?.hide()
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        // add menu item
        navBar.add(MeowBottomNavigation.Model(1, R.drawable.ic_home))
        navBar.add(MeowBottomNavigation.Model(2, R.drawable.ic_commandes))
        navBar.add(MeowBottomNavigation.Model(3, R.drawable.ic_profil))
        navBar.setOnShowListener { item: MeowBottomNavigation.Model ->
            when (item.id) {
                1 -> {
                    loadFragment(HomeFragment(this), R.string.home_page_vedette)
                    Log.i(LOG_TAG, "show Home.class")
                    return@setOnShowListener
                }
                2 -> {
                    loadFragment(CommandesFragment(this), R.string.commande_detail_page_title)
                    Log.i(LOG_TAG, "show commandes.class")
                    return@setOnShowListener
                }
                3 -> {
                    loadFragment(ProfileFragment(this), R.string.profile_page_title)
                    Log.i(LOG_TAG, "show Profil.class")
                    return@setOnShowListener
                }
            }
        }
//
//        if(goToCommandeFragment != null && goToCommandeFragment){
//            bundle?.clear()
//            navBar.show(2,true)
//        }else{
//            // Le Fragment qui s'affiche par Default
//            navBar.show(1, true)
//        }

        navBar.show(1, true)


        // pour éviter les erreurs
        navBar.setOnClickMenuListener { }
        navBar.setOnReselectListener { }
    }

    /**
     * Méthode qui permet de changer les fragments
     * @param fragment le fragment à replacer
     * @param titlePage l'identifiant de la textView qui fait reference au nom de la page
     * */
    fun loadFragment(fragment: Fragment, titlePage: Int) {

        // actualiser le titre de la page
        findViewById<TextView>(R.id.page_title).text = resources.getString(titlePage)

        // injecter le fragment dans notre boite
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.addToBackStack(null) // annuler le retour vers ce composant
        transaction.commit()
    }

}