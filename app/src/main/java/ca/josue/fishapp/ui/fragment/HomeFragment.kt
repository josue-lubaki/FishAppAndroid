package ca.josue.fishapp.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import ca.josue.fishapp.R
import ca.josue.fishapp.domain.repository.ProductResponseRepository
import ca.josue.fishapp.domain.viewModel.ProductResponseViewModel
import ca.josue.fishapp.ui.activity.MainActivity
import ca.josue.fishapp.ui.activity.Splash.Companion.fishListProduct
import ca.josue.fishapp.ui.activity.Splash.Companion.getAllProductsViaAPI
import ca.josue.fishapp.ui.adapter.FishAdapter
import ca.josue.fishapp.ui.adapter.FishItemDecoration
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class HomeFragment(
    private val context : MainActivity,
    productRepository : ProductResponseRepository
    ): Fragment() {

    var containerThis : ViewGroup? = null

    private var productResponseVM = ProductResponseViewModel(productRepository)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        containerThis = container
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context as MainActivity).title = R.string.home_page_vedette.toString()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        runBlocking(Dispatchers.Default){
            getAllProductsViaAPI()
            productResponseVM.insertProdutResponses(fishListProduct)
        }

        // Retrieve RecyclerView Horizontal
        val horizontalRecyclerView: RecyclerView =
            view.findViewById(R.id.horizontal_recyclerview)
        val llm = LinearLayoutManager(context)
        llm.orientation = LinearLayoutManager.HORIZONTAL
        horizontalRecyclerView.setHasFixedSize(true)
        horizontalRecyclerView.layoutManager = llm
        horizontalRecyclerView.adapter =
            FishAdapter(context, R.layout.item_horizontal_article)

        // Retrieve Vertical RecyclerView
        val verticalRecyclerView: RecyclerView =
            view.findViewById(R.id.vertical_recyclerview)
        verticalRecyclerView.setHasFixedSize(true)
        verticalRecyclerView.addItemDecoration(FishItemDecoration())
        verticalRecyclerView.adapter =
            FishAdapter(context, R.layout.item_vertical_article)

        productResponseVM.getProductResponses().observe(this.viewLifecycleOwner) { productResponseList ->

            if(productResponseList.isEmpty()) {
                LayoutInflater.from(context).inflate(R.layout.fragment_home_empty, containerThis, false)
            }
            else {
                (horizontalRecyclerView.adapter as FishAdapter).addProduct(productResponseList.filter { it.isFeature })
                (verticalRecyclerView.adapter as FishAdapter).addProduct(productResponseList)
            }
        }
    }
}