// ImageAdapter.kt
package com.example.chatwiseassignment.ui.ProductDetail

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.chatwiseassignment.R
import com.example.chatwiseassignment.databinding.ItemImagesBinding

class ImageAdapter(
    private val context: Context,
    private val images: List<String>
) : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>()
{

    inner class ImageViewHolder( binding: ItemImagesBinding) : RecyclerView.ViewHolder(binding.root) {
        val image = binding.imageView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = ItemImagesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        if (holder is ImageViewHolder) {
            val productImage = images[position]
            Glide
                .with(context)
                .load(productImage)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(holder.image)
        }
    }

    override fun getItemCount(): Int = images.size
}
