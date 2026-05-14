package com.village.gramavaxi.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.village.gramavaxi.R
import com.village.gramavaxi.data.entity.Animal
import com.village.gramavaxi.databinding.ItemAnimalBinding
import java.io.File

class AnimalAdapter(
    private val onItemClick: (Animal) -> Unit
) : ListAdapter<Animal, AnimalAdapter.AnimalViewHolder>(AnimalDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        val binding = ItemAnimalBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return AnimalViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class AnimalViewHolder(
        private val binding: ItemAnimalBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(animal: Animal) {
            binding.apply {
                tvAnimalName.text = animal.name
                tvAnimalType.text = "${animal.type} • ${animal.breed}"
                tvAnimalAge.text = "Age: ${animal.age} months"

                // Load animal photo or show icon
                if (!animal.photoPath.isNullOrEmpty()) {
                    val file = File(animal.photoPath)
                    if (file.exists()) {
                        ivAnimalPhoto.load(file) {
                            crossfade(true)
                            placeholder(getAnimalIcon(animal.type))
                            error(getAnimalIcon(animal.type))
                        }
                    } else {
                        ivAnimalPhoto.setImageResource(getAnimalIcon(animal.type))
                    }
                } else {
                    ivAnimalPhoto.setImageResource(getAnimalIcon(animal.type))
                }

                // Set card background color based on animal type
                root.setCardBackgroundColor(
                    root.context.getColor(getAnimalColor(animal.type))
                )

                // Click listener
                root.setOnClickListener { onItemClick(animal) }
            }
        }

        private fun getAnimalIcon(type: String): Int {
            return when (type.lowercase()) {
                "sheep" -> R.drawable.ic_sheep
                "goat" -> R.drawable.ic_goat
                "cow" -> R.drawable.ic_cow
                "buffalo" -> R.drawable.ic_buffalo
                else -> R.drawable.ic_sheep
            }
        }

        private fun getAnimalColor(type: String): Int {
            return when (type.lowercase()) {
                "sheep" -> R.color.sheep_color
                "goat" -> R.color.goat_color
                "cow" -> R.color.cow_color
                "buffalo" -> R.color.buffalo_color
                else -> R.color.card_background
            }
        }
    }

    class AnimalDiffCallback : DiffUtil.ItemCallback<Animal>() {
        override fun areItemsTheSame(oldItem: Animal, newItem: Animal): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Animal, newItem: Animal): Boolean {
            return oldItem == newItem
        }
    }
}