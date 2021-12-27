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
import ca.josue.fishapp.model.FishModelDTO

class HomeFragment(private val context : MainActivity): Fragment() {

    companion object{
        val fishListProduct = arrayListOf<FishModelDTO>()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view =  inflater.inflate(R.layout.fragment_home, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Retrieve RecyclerView Horizontal
        val horizontalRecyclerView : RecyclerView = view.findViewById(R.id.horizontal_recyclerview)
        horizontalRecyclerView.adapter = FishAdapter(context, fishListProduct, R.layout.item_horizontal_article)

        // Retrieve Vertical RecyclerView
        val verticalRecyclerView : RecyclerView = view.findViewById(R.id.vertical_recyclerview)
        verticalRecyclerView.adapter = FishAdapter(context, fishListProduct, R.layout.item_vertical_article)
        verticalRecyclerView.addItemDecoration(FishItemDecoration())

    }


}