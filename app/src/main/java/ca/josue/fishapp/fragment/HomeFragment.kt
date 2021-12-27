package ca.josue.fishapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import ca.josue.fishapp.MainActivity
import ca.josue.fishapp.R
import ca.josue.fishapp.`interface`.API
import ca.josue.fishapp.`interface`.ApiInterface
import ca.josue.fishapp.adapter.FishAdapter
import ca.josue.fishapp.adapter.FishItemDecoration
import ca.josue.fishapp.model.FishModel
import ca.josue.fishapp.model.Product
import ca.josue.fishapp.myAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment(private val context : MainActivity): Fragment() {

    companion object{
        private val fishListProduct = arrayListOf<FishModel>()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view =  inflater.inflate(R.layout.fragment_home, container, false)
        getMyData()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        println("La liste n'est pas vide ${fishListProduct.size}")

        // Retrieve RecyclerView Horizontal
        val horizontalRecyclerView : RecyclerView = view.findViewById(R.id.horizontal_recyclerview)
        horizontalRecyclerView.adapter = FishAdapter(context, fishListProduct, R.layout.item_horizontal_article)

        // Retrieve Vertical RecyclerView
        val verticalRecyclerView : RecyclerView = view.findViewById(R.id.vertical_recyclerview)
        verticalRecyclerView.adapter = FishAdapter(context, fishListProduct, R.layout.item_vertical_article)
        verticalRecyclerView.addItemDecoration(FishItemDecoration())

    }

    private fun getMyData() {
        val retrofitProduct = API.getApi()?.create(ApiInterface::class.java)?.getProducts()
        //val retrofitProduct = myAPI.getApi().create(ApiInterface::class.java).getProducts()
        retrofitProduct?.enqueue(object : Callback<List<Product>?> {
            override fun onResponse(call: Call<List<Product>?>, response: Response<List<Product>?>) {
                if (!response.isSuccessful)
                    return

                val responseBody = response.body()!!
                for (product in responseBody){
                    val prod = FishModel(
                            product.name,
                            product.category.name,
                            product.price,
                            product.image,
                            product.description
                    )
                    fishListProduct.add(prod)
                }
            }

            override fun onFailure(call: Call<List<Product>?>, t: Throwable) {
                println("Error retrofit ${t.message}")
            }
        })
    }
}