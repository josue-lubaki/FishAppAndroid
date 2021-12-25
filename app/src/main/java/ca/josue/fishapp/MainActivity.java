package ca.josue.fishapp;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import ca.josue.fishapp.fragment.CommandesFragment;
import ca.josue.fishapp.fragment.HomeFragment;
import ca.josue.fishapp.fragment.ProfileFragment;

public class MainActivity extends AppCompatActivity {
    private final String LOG_TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MeowBottomNavigation navBar = findViewById(R.id.navBar);

        // add menu item
        navBar.add(new MeowBottomNavigation.Model(1, R.drawable.ic_home));
        navBar.add(new MeowBottomNavigation.Model(2, R.drawable.ic_commandes));
        navBar.add(new MeowBottomNavigation.Model(3, R.drawable.ic_profil));

        navBar.setOnShowListener(item -> {
            Class<? extends Fragment> fragment = null;
            switch(item.getId()){
                case 1:
                    fragment = HomeFragment.class;
                    Log.i(LOG_TAG,"show Home.class");
                    break;
                case 2:
                    fragment = CommandesFragment.class;
                    Log.i(LOG_TAG,"show Home.class");
                    break;
                case 3:
                    fragment = ProfileFragment.class;
                    Log.i(LOG_TAG,"show Profil.class");
                    break;
            }

            // Appel de la methode showFragment()
            assert fragment != null;
            showFragment(fragment);
        });

        // Le Fragment qui s'affiche par Default
        navBar.show(1,true);

        // pour éviter les erreurs
        navBar.setOnClickMenuListener(item -> {});
        navBar.setOnReselectListener(item -> {});
    }



    /**
     * Methode qui permet de remplacer des Fragments sur le container principal de l'Activité
     * @param fragment le fragment dont il faut afficher
     *
     * @return void*/
    public void showFragment(Class<? extends Fragment> fragment) {
        try {
            Fragment currentFragment = getSupportFragmentManager().findFragmentByTag(fragment.getName());

            if(currentFragment == null)
                currentFragment = fragment.newInstance();

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, currentFragment, fragment.getName())
                    .commit();

        } catch(InstantiationException | IllegalAccessException e){
            e.printStackTrace();
            Log.d(LOG_TAG,"erreur au moment d'instancier fragment");
        }
    }
}