package com.village.gramavaxi.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.village.gramavaxi.data.entity.Vaccination
import com.village.gramavaxi.databinding.ActivityListBinding
import com.village.gramavaxi.databinding.ItemVaccinationSimpleBinding
import com.village.gramavaxi.utils.DateUtils
import com.village.gramavaxi.viewmodel.AnimalViewModel

class VaccinationCalendarActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListBinding
    private lateinit var viewModel: AnimalViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[AnimalViewModel::class.java]

        setSupportActionBar(binding.toolbar)
        binding.toolbar.title = "Vaccination Calendar"
        binding.toolbar.setNavigationOnClickListener { finish() }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val adapter = SimpleVaccinationAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        viewModel.nextVaccinations.observe(this) { vaccinations ->
            adapter.submitList(vaccinations)
        }
    }

    inner class SimpleVaccinationAdapter :
        RecyclerView.Adapter<SimpleVaccinationAdapter.VH>() {

        private var list = listOf<Vaccination>()

        fun submitList(newList: List<Vaccination>) {
            list = newList
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
            val binding = ItemVaccinationSimpleBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
            return VH(binding)
        }

        override fun onBindViewHolder(holder: VH, position: Int) {
            holder.bind(list[position])
        }

        override fun getItemCount() = list.size

        inner class VH(private val b: ItemVaccinationSimpleBinding) :
            RecyclerView.ViewHolder(b.root) {
            fun bind(v: Vaccination) {
                b.tvVaccineName.text = v.vaccineName
                b.tvDueDate.text = "Due: ${DateUtils.formatDate(v.nextDueDate)}"
                b.tvLocation.text = "📍 ${v.campLocation}"
                val overdue = DateUtils.isOverdue(v.nextDueDate)
                b.tvStatus.text = if (overdue) "⚠️ Overdue" else "📅 Upcoming"
            }
        }
    }
}