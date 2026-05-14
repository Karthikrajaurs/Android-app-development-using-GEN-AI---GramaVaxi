package com.village.gramavaxi.ui.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.village.gramavaxi.R
import com.village.gramavaxi.data.entity.Vaccination
import com.village.gramavaxi.databinding.ItemVaccinationBinding
import com.village.gramavaxi.utils.DateUtils

class VaccinationAdapter :
    ListAdapter<Vaccination, VaccinationAdapter.VaccinationViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VaccinationViewHolder {
        val binding = ItemVaccinationBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return VaccinationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VaccinationViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class VaccinationViewHolder(
        private val binding: ItemVaccinationBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(vaccination: Vaccination) {
            binding.apply {
                tvVaccineName.text = vaccination.vaccineName
                tvVaccineType.text = vaccination.vaccinationType
                tvDueDate.text = "Due: ${DateUtils.formatDate(vaccination.nextDueDate)}"
                tvLocation.text = "📍 ${vaccination.campLocation}"

                val isOverdue = DateUtils.isOverdue(vaccination.nextDueDate)
                val isCompleted = vaccination.isCompleted

                when {
                    isCompleted -> {
                        tvStatus.text = "Done"
                        tvStatus.setBackgroundResource(R.drawable.badge_background)
                        tvStatus.setBackgroundTintList(
                            root.context.getColorStateList(R.color.success)
                        )
                        viewDot.setBackgroundResource(R.drawable.circle_dot)
                        viewDot.backgroundTintList =
                            root.context.getColorStateList(R.color.success)
                    }
                    isOverdue -> {
                        tvStatus.text = "Overdue"
                        tvStatus.setBackgroundTintList(
                            root.context.getColorStateList(R.color.error)
                        )
                        viewDot.backgroundTintList =
                            root.context.getColorStateList(R.color.error)
                    }
                    else -> {
                        tvStatus.text = "Due"
                        tvStatus.setBackgroundTintList(
                            root.context.getColorStateList(R.color.warning)
                        )
                        viewDot.backgroundTintList =
                            root.context.getColorStateList(R.color.warning)
                    }
                }
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Vaccination>() {
        override fun areItemsTheSame(a: Vaccination, b: Vaccination) = a.id == b.id
        override fun areContentsTheSame(a: Vaccination, b: Vaccination) = a == b
    }
}