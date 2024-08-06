package com.example.chatwiseassignment.ui.ProductDetail

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.chatwiseassignment.R
import com.example.chatwiseassignment.databinding.ActivityProductDetailedBinding
import com.example.chatwiseassignment.model.Product
import com.example.chatwiseassignment.utils.Constants

class ProductDetailedActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductDetailedBinding
    private var productDetails: Product? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityProductDetailedBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        if(intent.hasExtra(Constants.PRODUCT_DETAILS)) {
            productDetails = intent.getParcelableExtra(Constants.PRODUCT_DETAILS) as Product?
        }

        productDetails?.let {
            Glide
                .with(this)
                .load(it.thumbnail)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(binding.imgProductThumbnail)

            binding.txtProductTitle.text = it.title
            binding.txtProductPrice.text = "Price: ${it.price}"
            binding.txtProductRating.text = "Rating: ${it.rating}"
            binding.txtProductDescription.text = it.description
            binding.txtCategory.text = "Category: ${it.category}"
            binding.txtBrand.text = "Brand: ${it.brand}"
            binding.txtStock.text = "Stock: ${it.stock}"
            binding.txtReturnPolicy.text = "Return Policy: ${it.returnPolicy}"

            binding.recyclerViewImages.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true)
            binding.recyclerViewImages.setHasFixedSize(true)

            val imageAdapter = ImageAdapter(this, it.images)
            binding.recyclerViewImages.adapter = imageAdapter

        }
    }
}