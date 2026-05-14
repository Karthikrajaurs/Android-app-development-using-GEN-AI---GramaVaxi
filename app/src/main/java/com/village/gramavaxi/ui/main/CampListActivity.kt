package com.village.gramavaxi.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.village.gramavaxi.R
import com.village.gramavaxi.data.entity.VaccinationCamp
import com.village.gramavaxi.databinding.ActivityListBinding
import com.village.gramavaxi.databinding.ItemCampBinding
import com.village.gramavaxi.utils.DateUtils
import com.village.gramavaxi.viewmodel.AnimalViewModel

class CampListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListBinding
    private lateinit var viewModel: AnimalViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[AnimalViewModel::class.java]

        setSupportActionBar(binding.toolbar)
        binding.toolbar.title = "Vaccination Camps"
        binding.toolbar.setNavigationOnClickListener { finish() }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val adapter = CampAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        viewModel.upcomingCamps.observe(this) { camps ->
            adapter.submitList(camps)
        }

        binding.fab.setOnClickListener {
            AddCampDialog { camp ->
                viewModel.insertCamp(camp)
            }.show(supportFragmentManager, "AddCampDialog")
        }
    }

    inner class CampAdapter : RecyclerView.Adapter<CampAdapter.VH>() {
        private var list = listOf<VaccinationCamp>()
        fun submitList(newList: List<VaccinationCamp>) {
            list = newList; notifyDataSetChanged()
        }
        override fun getItemCount() = list.size
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            VH(ItemCampBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(list[position])

        inner class VH(private val b: ItemCampBinding) : RecyclerView.ViewHolder(b.root) {
            fun bind(camp: VaccinationCamp) {
                b.tvCampDate.text = "📅 ${DateUtils.formatDate(camp.campDate)}"
                b.tvCampLocation.text = "📍 ${camp.location}"
                b.tvCampTime.text = "⏰ ${camp.startTime} - ${camp.endTime}"
                b.tvVetName.text = "👨‍⚕️ Dr. ${camp.veterinarianName}"
                b.tvVaccines.text = "💉 ${camp.vaccineTypes}"
            }
        }
    }
}