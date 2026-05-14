package com.village.gramavaxi.ui.details

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.village.gramavaxi.R
import com.village.gramavaxi.data.entity.DiseaseReport
import com.village.gramavaxi.databinding.ActivityAnimalDetailsBinding
import com.village.gramavaxi.utils.DateUtils
import com.village.gramavaxi.viewmodel.AnimalViewModel
import java.io.File

class AnimalDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAnimalDetailsBinding
    private lateinit var viewModel: AnimalViewModel
    private lateinit var vaccinationAdapter: VaccinationAdapter
    private var animalId: Long = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimalDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[AnimalViewModel::class.java]
        animalId = intent.getLongExtra("ANIMAL_ID", -1)

        if (animalId == -1L) {
            finish()
            return
        }

        setupToolbar()
        setupVaccinationRecycler()
        observeAnimalData()
        setupClickListeners()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener { finish() }
    }

    private fun setupVaccinationRecycler() {
        vaccinationAdapter = VaccinationAdapter()
        binding.recyclerViewVaccinations.apply {
            layoutManager = LinearLayoutManager(this@AnimalDetailsActivity)
            adapter = vaccinationAdapter
            setHasFixedSize(false)
        }
    }

    private fun observeAnimalData() {
        // Observe animal details
        viewModel.getAnimalById(animalId).observe(this) { animal ->
            animal?.let {
                binding.tvAnimalName.text = it.name
                binding.tvAnimalType.text = "${it.type} • ${it.breed}"
                binding.tvTagNumber.text = "Tag: ${it.tagNumber}"
                binding.tvAge.text = "Age: ${it.age} months"
                binding.tvOwnerName.text = it.ownerName
                binding.tvOwnerPhone.text = it.ownerPhone

                // Load photo
                if (!it.photoPath.isNullOrEmpty()) {
                    val file = File(it.photoPath)
                    if (file.exists()) {
                        binding.ivAnimalPhoto.load(file) {
                            crossfade(true)
                            placeholder(R.drawable.ic_sheep)
                        }
                    }
                } else {
                    binding.ivAnimalPhoto.setImageResource(
                        when (it.type.lowercase()) {
                            "sheep" -> R.drawable.ic_sheep
                            "goat" -> R.drawable.ic_goat
                            "cow" -> R.drawable.ic_cow
                            else -> R.drawable.ic_buffalo
                        }
                    )
                }
            }
        }

        // Observe vaccinations
        viewModel.getVaccinationsByAnimal(animalId).observe(this) { vaccinations ->
            if (vaccinations.isEmpty()) {
                binding.tvNoVaccinations.visibility = View.VISIBLE
                binding.recyclerViewVaccinations.visibility = View.GONE
            } else {
                binding.tvNoVaccinations.visibility = View.GONE
                binding.recyclerViewVaccinations.visibility = View.VISIBLE
                vaccinationAdapter.submitList(vaccinations)
            }
        }
    }

    private fun setupClickListeners() {
        binding.btnAddVaccination.setOnClickListener {
            showAddVaccinationDialog()
        }

        binding.btnReportDisease.setOnClickListener {
            showReportDiseaseDialog()
        }
    }

    private fun showAddVaccinationDialog() {
        AddVaccinationDialog(animalId) { vaccination ->
            viewModel.insertVaccination(vaccination)
            Toast.makeText(this, getString(R.string.vaccination_added), Toast.LENGTH_SHORT).show()
        }.show(supportFragmentManager, "AddVaccinationDialog")
    }

    private fun showReportDiseaseDialog() {
        val symptoms = arrayOf(
            "Fever / High Temperature",
            "Loss of Appetite",
            "Limping / Foot problem",
            "Skin lesions / Blisters",
            "Diarrhea",
            "Difficulty Breathing",
            "Unusual discharge",
            "Other"
        )

        val selectedSymptoms = mutableListOf<String>()

        AlertDialog.Builder(this)
            .setTitle("🚨 Report Sick Animal")
            .setMultiChoiceItems(symptoms, null) { _, which, isChecked ->
                if (isChecked) selectedSymptoms.add(symptoms[which])
                else selectedSymptoms.remove(symptoms[which])
            }
            .setPositiveButton("Report to Vet") { _, _ ->
                if (selectedSymptoms.isEmpty()) {
                    Toast.makeText(this, "Please select at least one symptom", Toast.LENGTH_SHORT).show()
                    return@setPositiveButton
                }
                val report = DiseaseReport(
                    animalId = animalId,
                    symptoms = selectedSymptoms.joinToString(", "),
                    severity = if (selectedSymptoms.size >= 3) "Severe"
                    else if (selectedSymptoms.size >= 2) "Moderate"
                    else "Mild",
                    vetNotified = true,
                    status = "PENDING"
                )
                viewModel.insertDiseaseReport(report)
                Toast.makeText(
                    this,
                    "✅ ${getString(R.string.disease_reported)}",
                    Toast.LENGTH_LONG
                ).show()
            }
            .setNegativeButton(getString(R.string.cancel), null)
            .show()
    }
}