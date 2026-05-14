package com.village.gramavaxi.ui.register

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModelProvider
import com.village.gramavaxi.R
import com.village.gramavaxi.data.entity.Animal
import com.village.gramavaxi.data.entity.Vaccination
import com.village.gramavaxi.databinding.ActivityRegisterAnimalBinding
import com.village.gramavaxi.utils.DateUtils
import com.village.gramavaxi.utils.ImageHelper
import com.village.gramavaxi.utils.VaccinationScheduler
import com.village.gramavaxi.viewmodel.AnimalViewModel
import java.io.File

class RegisterAnimalActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterAnimalBinding
    private lateinit var viewModel: AnimalViewModel
    private var savedPhotoPath: String? = null
    private var cameraImageUri: Uri? = null

    // Camera launcher
    private val cameraLauncher = registerForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { success ->
        if (success) {
            cameraImageUri?.let { uri ->
                val bitmap = ImageHelper.uriToBitmap(this, uri)
                bitmap?.let {
                    savedPhotoPath = ImageHelper.saveImageToInternalStorage(this, it)
                    binding.ivAnimalPhoto.load(it)
                }
            }
        }
    }

    // Gallery launcher
    private val galleryLauncher = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri ->
        uri?.let {
            val bitmap = ImageHelper.uriToBitmap(this, it)
            bitmap?.let { bmp ->
                savedPhotoPath = ImageHelper.saveImageToInternalStorage(this, bmp)
                binding.ivAnimalPhoto.load(bmp)
            }
        }
    }

    // Camera permission launcher
    private val cameraPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) launchCamera()
        else Toast.makeText(this, "Camera permission required", Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterAnimalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[AnimalViewModel::class.java]

        setupToolbar()
        setupAnimalTypeDropdown()
        setupClickListeners()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener { finish() }
    }

    private fun setupAnimalTypeDropdown() {
        val animalTypes = listOf(
            getString(R.string.sheep),
            getString(R.string.goat),
            getString(R.string.cow),
            getString(R.string.buffalo)
        )
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, animalTypes)
        binding.spinnerAnimalType.setAdapter(adapter)
    }

    private fun setupClickListeners() {
        binding.btnTakePhoto.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    this, Manifest.permission.CAMERA
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                launchCamera()
            } else {
                cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
            }
        }

        binding.btnSelectPhoto.setOnClickListener {
            galleryLauncher.launch("image/*")
        }

        binding.btnSave.setOnClickListener {
            saveAnimal()
        }

        binding.btnCancel.setOnClickListener {
            finish()
        }
    }

    private fun launchCamera() {
        val photoFile = File(
            cacheDir,
            "temp_photo_${System.currentTimeMillis()}.jpg"
        )
        cameraImageUri = FileProvider.getUriForFile(
            this,
            "${packageName}.fileprovider",
            photoFile
        )
        cameraLauncher.launch(cameraImageUri)
    }

    private fun saveAnimal() {
        // Validate inputs
        val name = binding.etAnimalName.text.toString().trim()
        val type = binding.spinnerAnimalType.text.toString().trim()
        val breed = binding.etBreed.text.toString().trim()
        val ageText = binding.etAge.text.toString().trim()
        val tagNumber = binding.etTagNumber.text.toString().trim()
        val ownerName = binding.etOwnerName.text.toString().trim()
        val ownerPhone = binding.etOwnerPhone.text.toString().trim()

        // Field validation
        if (name.isEmpty()) {
            binding.etAnimalName.error = "Required"
            return
        }
        if (type.isEmpty()) {
            Toast.makeText(this, getString(R.string.fill_all_fields), Toast.LENGTH_SHORT).show()
            return
        }
        if (breed.isEmpty()) {
            binding.etBreed.error = "Required"
            return
        }
        if (ageText.isEmpty()) {
            binding.etAge.error = "Required"
            return
        }
        if (tagNumber.isEmpty()) {
            binding.etTagNumber.error = "Required"
            return
        }
        if (ownerName.isEmpty()) {
            binding.etOwnerName.error = "Required"
            return
        }
        if (ownerPhone.isEmpty()) {
            binding.etOwnerPhone.error = "Required"
            return
        }

        val age = ageText.toIntOrNull() ?: 0

        // Create animal object
        val animal = Animal(
            name = name,
            type = type,
            breed = breed,
            age = age,
            tagNumber = tagNumber,
            ownerName = ownerName,
            ownerPhone = ownerPhone,
            photoPath = savedPhotoPath
        )

        // Save animal and auto-generate vaccinations
        viewModel.insertAnimalAndScheduleVaccinations(animal) { animalId ->
            runOnUiThread {
                Toast.makeText(
                    this,
                    getString(R.string.animal_registered),
                    Toast.LENGTH_SHORT
                ).show()
                finish()
            }
        }
    }

    // Extension to load image into ShapeableImageView
    private fun android.widget.ImageView.load(bitmap: Bitmap) {
        setImageBitmap(bitmap)
    }
}