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

class HomeFragment(private val context : MainActivity): Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view =  inflater.inflate(R.layout.fragment_home, container, false)

        // Retrieve RecyclerView Horizontal
        val horizontalRecyclerView : RecyclerView = view.findViewById(R.id.horizontal_recyclerview)
        horizontalRecyclerView.adapter = FishAdapter(context, R.layout.item_horizontal_article)

        // Retrieve Vertical RecyclerView
        val verticalRecyclerView : RecyclerView = view.findViewById(R.id.vertical_recyclerview)
        verticalRecyclerView.adapter = FishAdapter(context, R.layout.item_vertical_article)

        return view
    }

}