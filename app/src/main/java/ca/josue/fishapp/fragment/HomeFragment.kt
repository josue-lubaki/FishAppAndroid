package ca.josue.fishapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import ca.josue.fishapp.MainActivity
import ca.josue.fishapp.R
import ca.josue.fishapp.adapter.FishAdapter
import ca.josue.fishapp.adapter.FishItemDecoration
import ca.josue.fishapp.model.FishModel

class HomeFragment(private val context : MainActivity): Fragment() {

    private val fishList = arrayListOf<FishModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view =  inflater.inflate(R.layout.fragment_home, container, false)

        fishList.add(FishModel(
                "Mpiodi",
                "poisson_gris",
                20,
                "https://cdn.pixabay.com/photo/2016/11/29/09/43/koi-fish-1868779_960_720.jpg",
                "Le poisson salé est un poisson qui a subi une salaison dans un but de conservation.\n"
        ))

        fishList.add(FishModel(
                "RichardFish",
                "poisson_bleu",
                25,
                "https://cdn.pixabay.com/photo/2012/03/03/23/54/animal-21668_960_720.jpg",
                "Utilisée surtout pour faire de la brandade ou en portugais Bacalhau, cette morue de Nouvelle-Écosse se présente en filet, sans peau et sans arrêtes. Elle doit être dessalée pendant 12 à 24 heures, dépendamment de la fréquence du changement de l'eau."
        ))

        fishList.add(FishModel(
                "Saumon",
                "poisson_jaune",
                45,
                "https://cdn.pixabay.com/photo/2015/04/08/13/13/food-712665_960_720.jpg",
                "Le saumon est un poisson de mer de la famille des Salmonidés. Avec 5 espèces vivant dans le Pacifique et 1 dans l'Atlantique, le saumon se présente avec un dos bleuté parsemé de taches noires, des flancs et un abdomen dorés. Au corps allongé recouvert d'écailles lisses, ce poisson peut mesurer 90 cm et peser 15 kg."
        ))


        fishList.add(FishModel(
                "Capitaine",
                "poisson_vert",
                55,
                "https://cdn.pixabay.com/photo/2018/04/15/17/45/fish-3322230_960_720.jpg",
                "Le capitaine (Polydactylus quadrifilis), aussi appelé Gros capitaine pour le distinguer des autres capitaines, notamment du Petit capitaine (Galeoides decadactylus), est un poisson de la famille des Polynemidae. Le capitaine est un poisson à chair blanche et fine. Il se cuisine comme le bar."
        ))



        // Retrieve RecyclerView Horizontal
        val horizontalRecyclerView : RecyclerView = view.findViewById(R.id.horizontal_recyclerview)
        horizontalRecyclerView.adapter = FishAdapter(context, fishList, R.layout.item_horizontal_article)

        // Retrieve Vertical RecyclerView
        val verticalRecyclerView : RecyclerView = view.findViewById(R.id.vertical_recyclerview)
        verticalRecyclerView.adapter = FishAdapter(context, fishList, R.layout.item_vertical_article)
        verticalRecyclerView.addItemDecoration(FishItemDecoration())

        return view
    }

}