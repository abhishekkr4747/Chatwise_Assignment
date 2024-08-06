package com.example.chatwiseassignment.ui.ProductList

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatwiseassignment.ui.ChatwiseApplication
import com.example.chatwiseassignment.R
import com.example.chatwiseassignment.databinding.ActivityProductListBinding
import com.example.chatwiseassignment.model.ApiResponse
import com.example.chatwiseassignment.model.Product
import com.example.chatwiseassignment.ui.ProductDetail.ProductDetailedActivity
import com.example.chatwiseassignment.utils.Constants
import javax.inject.Inject

class ProductListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductListBinding
    lateinit var viewModel: ProductViewModel

    @Inject
    lateinit var productViewModelFactory: ProductViewModelFactory


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityProductListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        (application as ChatwiseApplication).applicationComponent.inject(this)

        viewModel = ViewModelProvider(this, productViewModelFactory).get(ProductViewModel::class.java)

        viewModel.getDataFromApi()
        observeData()
    }

    private fun observeData() {
        viewModel.productsLiveData.observe(this) {
            showDataOnScreen(it)
        }
    }

    private fun showDataOnScreen(data: ApiResponse) {
        binding.recyclerViewProductList.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewProductList.setHasFixedSize(true)

        val productAdapter = ProductListAdapter(this, data.products)
        binding.recyclerViewProductList.adapter = productAdapter

        productAdapter.setOnClickListener(object : ProductListAdapter.OnItemClickListener{
            override fun OnItemClick(product: Product) {
                val intent = Intent(this@ProductListActivity, ProductDetailedActivity::class.java)
                intent.putExtra(Constants.PRODUCT_DETAILS, product)
                startActivity(intent)
            }

        })
    }
}