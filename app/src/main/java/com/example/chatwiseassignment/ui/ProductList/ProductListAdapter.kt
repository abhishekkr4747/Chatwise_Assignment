package com.example.chatwiseassignment.ui.ProductList

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.chatwiseassignment.R
import com.example.chatwiseassignment.databinding.HeaderItemBinding
import com.example.chatwiseassignment.databinding.ItemProductsBinding
import com.example.chatwiseassignment.model.Product

class ProductListAdapter(
    private val context: Context,
    private val items: List<Product>
): RecyclerView.Adapter<RecyclerView.ViewHolder>()
{

    private var onItemClickListener: OnItemClickListener? = null

    companion object {
        private const val TYPE_HEADER = 0
        private const val TYPE_PRODUCT = 1
    }

    class HeaderViewHolder(binding: HeaderItemBinding) : RecyclerView.ViewHolder(binding.root)

    class ProductViewHolder( binding: ItemProductsBinding) : RecyclerView.ViewHolder(binding.root) {
        val productTitle = binding.productTitle
        val productDescription = binding.productDescription
        val productThumbnail = binding.productThumbnail
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) TYPE_HEADER else TYPE_PRODUCT
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_HEADER -> {
                val binding = HeaderItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                HeaderViewHolder(binding)
            }
            TYPE_PRODUCT -> {
                val binding = ItemProductsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ProductViewHolder(binding)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HeaderViewHolder -> { }

            is ProductViewHolder -> {
                val product = items[position]
                holder.productTitle.text = product.title
                holder.productDescription.text = product.description

                Glide
                    .with(context)
                    .load(product.thumbnail)
                    .centerCrop()
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .into(holder.productThumbnail)

                holder.itemView.setOnClickListener {
                    if (onItemClickListener != null) {
                        onItemClickListener!!.OnItemClick(product)
                    }
                }
            }
        }
    }

    interface OnItemClickListener {
        fun OnItemClick(product: Product)
    }

    fun setOnClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }
}



