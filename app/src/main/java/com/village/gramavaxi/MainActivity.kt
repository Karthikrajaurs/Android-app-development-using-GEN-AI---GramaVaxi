package com.village.gramavaxi

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.village.gramavaxi.databinding.ActivityMainBinding
import com.village.gramavaxi.ui.main.AnimalAdapter
import com.village.gramavaxi.ui.main.CampListActivity
import com.village.gramavaxi.ui.main.DiseaseReportListActivity
import com.village.gramavaxi.ui.main.VaccinationCalendarActivity
import com.village.gramavaxi.ui.register.RegisterAnimalActivity
import com.village.gramavaxi.ui.details.AnimalDetailsActivity
import com.village.gramavaxi.viewmodel.AnimalViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: AnimalViewModel
    private lateinit var animalAdapter: AnimalAdapter

    private val notificationPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (!isGranted) {
            Snackbar.make(
                binding.root,
                "Notification permission needed for vaccination alerts",
                Snackbar.LENGTH_LONG
            ).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        viewModel = ViewModelProvider(this)[AnimalViewModel::class.java]

        requestNotificationPermission()
        setupRecyclerView()
        observeData()
        setupClickListeners()
    }

    private fun requestNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                notificationPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }

    private fun setupRecyclerView() {
        animalAdapter = AnimalAdapter { animal ->
            val intent = Intent(this, AnimalDetailsActivity::class.java)
            intent.putExtra("ANIMAL_ID", animal.id)
            startActivity(intent)
        }

        binding.recyclerViewAnimals.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = animalAdapter
            setHasFixedSize(false)
        }
    }

    private fun observeData() {
        viewModel.allAnimals.observe(this) { animals ->
            animalAdapter.submitList(animals)
            if (animals.isEmpty()) {
                binding.emptyState.visibility = View.VISIBLE
                binding.recyclerViewAnimals.visibility = View.GONE
            } else {
                binding.emptyState.visibility = View.GONE
                binding.recyclerViewAnimals.visibility = View.VISIBLE
            }
            binding.tvTotalAnimals.text = animals.size.toString()
        }

        viewModel.nextVaccinations.observe(this) { vaccinations ->
            binding.tvUpcomingVaccines.text = vaccinations.size.toString()
        }

        viewModel.upcomingCamps.observe(this) { camps ->
            binding.tvUpcomingCamps.text = camps.size.toString()
        }
    }

    private fun setupClickListeners() {
        binding.fabAddAnimal.setOnClickListener {
            startActivity(Intent(this, RegisterAnimalActivity::class.java))
        }

        binding.cardAddAnimal.setOnClickListener {
            startActivity(Intent(this, RegisterAnimalActivity::class.java))
        }

        binding.cardVaccinationCalendar.setOnClickListener {
            startActivity(Intent(this, VaccinationCalendarActivity::class.java))
        }

        binding.cardUpcomingCamps.setOnClickListener {
            startActivity(Intent(this, CampListActivity::class.java))
        }

        binding.cardDiseaseReports.setOnClickListener {
            startActivity(Intent(this, DiseaseReportListActivity::class.java))
        }
        binding.cardHealthHistory.setOnClickListener {
            startActivity(Intent(this, VaccinationCalendarActivity::class.java))
        }

        binding.cardVetContact.setOnClickListener {
            showVetContactDialog()
        }

        binding.btnAlertDetails.setOnClickListener {
            startActivity(Intent(this, CampListActivity::class.java))
        }
    }
    private fun showVetContactDialog() {
        androidx.appcompat.app.AlertDialog.Builder(this)
            .setTitle("ವೈದ್ಯ ಸಂಪರ್ಕ / Vet Contact")
            .setMessage(
                "ಡಾ. ಶಿವಕುಮಾರ್ / Dr. Shivakumar\n" +
                        "📞 Ph: 9988776655\n\n" +
                        "ಗ್ರಾಮ ಪಶು ವೈದ್ಯಾಧಿಕಾರಿ\n" +
                        "Village Veterinary Officer\n\n" +
                        "ಸಮಯ / Timing: 9 AM – 5 PM"
            )
            .setPositiveButton("ಸರಿ / OK", null)
            .show()
    }
}