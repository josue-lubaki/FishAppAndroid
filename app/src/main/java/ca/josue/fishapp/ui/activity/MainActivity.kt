package ca.josue.fishapp.ui.activity

import android.os.Bundle
import android.os.Parcelable
import android.os.PersistableBundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import ca.josue.fishapp.R
import ca.josue.fishapp.domain.dto.ProductResponse
import ca.josue.fishapp.domain.repository.MyOrderRepository
import ca.josue.fishapp.domain.repository.ProductResponseRepository
import ca.josue.fishapp.ui.fragment.CommandesFragment
import ca.josue.fishapp.ui.fragment.HomeFragment
import ca.josue.fishapp.ui.fragment.ProfileFragment
import ca.josue.fishapp.ui.util.FragmentUtils.Companion.loadFragment
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    companion object{
        lateinit var navBar : MeowBottomNavigation
    }

    @Inject
    lateinit var productRepository : ProductResponseRepository

    @Inject
    lateinit var myOrderRepository: MyOrderRepository

    lateinit var homeFragment : HomeFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        homeFragment = HomeFragment(this, productRepository)

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
                    loadFragment(this, homeFragment, R.string.home_page_vedette)
                    return@setOnShowListener
                }
                2 -> {
                    loadFragment(this,CommandesFragment(this, myOrderRepository), R.string.commande_detail_page_title)
                    return@setOnShowListener
                }
                3 -> {
                    loadFragment(this,ProfileFragment(this, myOrderRepository), R.string.profile_page_title)
                    return@setOnShowListener
                }
            }
        }
        // default Fragment
        navBar.show(1, true)

        // pour éviter les erreurs
        navBar.setOnClickMenuListener { }
        navBar.setOnReselectListener { }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
    }
}