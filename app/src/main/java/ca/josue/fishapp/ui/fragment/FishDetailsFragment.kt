package ca.josue.fishapp.ui.fragment

import android.app.Dialog
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil.setContentView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ca.josue.fishapp.R
import ca.josue.fishapp.domain.model.MyOrdersRoom
import ca.josue.fishapp.ui.adapter.IAdapter
import ca.josue.fishapp.domain.model.ProductRoom
import ca.josue.fishapp.domain.repository.OrderItemRepository
import ca.josue.fishapp.domain.viewModel.OrderItemViewModel
import ca.josue.fishapp.ui.activity.MainActivity
import ca.josue.fishapp.ui.adapter.FishAdapterOrder
import ca.josue.fishapp.ui.adapter.FishItemDecoration
import com.bumptech.glide.Glide

class FishDetailsFragment(
    private val context : MainActivity,
    private val idOrder : String,
    orderItemRepository : OrderItemRepository
        ) : Fragment() {

    private var fishDetailsRecyclerView : RecyclerView? = null
    private var orderItemVM = OrderItemViewModel(orderItemRepository)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.popup_fish_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fishDetailsRecyclerView = view.findViewById(R.id.recyclervier_detail_list)
        fishDetailsRecyclerView?.layoutManager = LinearLayoutManager(context)
        fishDetailsRecyclerView?.addItemDecoration(FishItemDecoration())

        orderItemVM.getInfoProductByIdOrder(idOrder).observe(this.viewLifecycleOwner){ listProduct ->
            fishDetailsRecyclerView?.adapter = FishAdapterOrder(context, listProduct)
        }
    }

}