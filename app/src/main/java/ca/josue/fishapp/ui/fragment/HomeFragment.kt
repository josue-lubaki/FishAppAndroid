package ca.josue.fishapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import ca.josue.fishapp.R
import ca.josue.fishapp.domain.repository.ProductResponseRepository
import ca.josue.fishapp.domain.viewModel.ProductResponseViewModel
import ca.josue.fishapp.ui.activity.MainActivity
import ca.josue.fishapp.ui.activity.Splash.Companion.fishListProduct
import ca.josue.fishapp.ui.adapter.FishAdapter
import ca.josue.fishapp.ui.adapter.FishItemDecoration
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

class HomeFragment(
    private val context : MainActivity,
    private val productRepository : ProductResponseRepository
    ): Fragment() {

    private var productResponseVM = ProductResponseViewModel(productRepository)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return if(fishListProduct.isNotEmpty()){
            inflater.inflate(R.layout.fragment_home, container, false)
        }else{
            inflater.inflate(R.layout.fragment_home_empty, container, false)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        runBlocking(Dispatchers.Default){
            productResponseVM.insertProdutResponses(fishListProduct)
        }

        productResponseVM.getProductResponses().observe(this.viewLifecycleOwner) { productResponseList ->
            if (productResponseList.isNotEmpty()) {

                // Retrieve RecyclerView Horizontal
                val horizontalRecyclerView: RecyclerView =
                    view.findViewById(R.id.horizontal_recyclerview)
                horizontalRecyclerView.adapter =
                    FishAdapter(context, productResponseList, R.layout.item_horizontal_article)

                // Retrieve Vertical RecyclerView
                val verticalRecyclerView: RecyclerView =
                    view.findViewById(R.id.vertical_recyclerview)
                verticalRecyclerView.adapter =
                    FishAdapter(context, productResponseList, R.layout.item_vertical_article)
                verticalRecyclerView.addItemDecoration(FishItemDecoration())
            }
        }

    }
}