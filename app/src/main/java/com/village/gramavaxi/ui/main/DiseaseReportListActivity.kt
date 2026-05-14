package com.village.gramavaxi.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.village.gramavaxi.data.entity.DiseaseReport
import com.village.gramavaxi.databinding.ActivityListBinding
import com.village.gramavaxi.databinding.ItemDiseaseReportBinding
import com.village.gramavaxi.utils.DateUtils
import com.village.gramavaxi.viewmodel.AnimalViewModel

class DiseaseReportListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListBinding
    private lateinit var viewModel: AnimalViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[AnimalViewModel::class.java]

        setSupportActionBar(binding.toolbar)
        binding.toolbar.title = "Disease Reports"
        binding.toolbar.setNavigationOnClickListener { finish() }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val adapter = DiseaseReportAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        viewModel.pendingReports.observe(this) { reports ->
            adapter.submitList(reports)
        }
    }

    inner class DiseaseReportAdapter : RecyclerView.Adapter<DiseaseReportAdapter.VH>() {
        private var list = listOf<DiseaseReport>()
        fun submitList(newList: List<DiseaseReport>) {
            list = newList; notifyDataSetChanged()
        }
        override fun getItemCount() = list.size
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            VH(ItemDiseaseReportBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(list[position])

        inner class VH(private val b: ItemDiseaseReportBinding) : RecyclerView.ViewHolder(b.root) {
            fun bind(r: DiseaseReport) {
                b.tvSymptoms.text = "Symptoms: ${r.symptoms}"
                b.tvSeverity.text = "Severity: ${r.severity}"
                b.tvDate.text = "Reported: ${DateUtils.formatDate(r.reportDate)}"
                b.tvStatus.text = r.status
                b.tvVetNotified.text = if (r.vetNotified) "✅ Vet Notified" else "❌ Vet Not Notified"
            }
        }
    }
}