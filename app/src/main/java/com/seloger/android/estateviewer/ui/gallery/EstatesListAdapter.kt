package com.seloger.android.estateviewer.ui.gallery

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.seloger.android.estateviewer.R
import com.seloger.android.estateviewer.data.entity.Estate
import com.seloger.android.estateviewer.databinding.EstateItemBinding
import kotlin.math.roundToInt

class EstatesListAdapter(private val clicked: (Estate) -> Unit) :
    ListAdapter<Estate, EstatesListAdapter.ViewHolder>(ESTATES_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = EstateItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val estate = getItem(position)
        holder.bind(estate)
    }

    inner class ViewHolder(private val binding: EstateItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(estate: Estate) {
            binding.apply {
                estate.apply {
                    Glide.with(itemView)
                        .load(imageUrl)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .error(R.drawable.ic_image_error)
                        .into(imageViewEstate)

                    textViewTitle.text = "${price.roundToInt()}€, $propertyType à $city"
                    textViewDescription.text = "${rooms}p, ${area}m"
                }

                root.setOnClickListener {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        val item = getItem(position)
                        clicked(item)
                    }
                }
            }
        }
    }

    companion object {
        private val ESTATES_COMPARATOR = object : DiffUtil.ItemCallback<Estate>() {
            override fun areItemsTheSame(oldItem: Estate, newItem: Estate): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Estate, newItem: Estate): Boolean =
                oldItem == newItem
        }
    }
}