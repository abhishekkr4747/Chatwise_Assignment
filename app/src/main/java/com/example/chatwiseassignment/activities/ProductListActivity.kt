package com.example.chatwiseassignment.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatwiseassignment.R
import com.example.chatwiseassignment.adapter.ProductListAdapter
import com.example.chatwiseassignment.databinding.ActivityProductListBinding
import com.example.chatwiseassignment.model.ApiResponse
import com.example.chatwiseassignment.model.Product
import com.example.chatwiseassignment.utils.Constants

class ProductListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductListBinding
    private val viewModel: ProductViewModel by viewModels()


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

        viewModel.getDataFromApi()
        observeData()
    }

    private fun observeData() {
        viewModel.response.observe(this) {
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