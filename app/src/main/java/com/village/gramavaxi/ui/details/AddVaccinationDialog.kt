package com.village.gramavaxi.ui.details

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.village.gramavaxi.data.entity.Vaccination
import com.village.gramavaxi.databinding.DialogAddVaccinationBinding
import java.text.SimpleDateFormat
import java.util.*

class AddVaccinationDialog(
    private val animalId: Long,
    private val onSave: (Vaccination) -> Unit
) : BottomSheetDialogFragment() {

    private var _binding: DialogAddVaccinationBinding? = null
    private val binding get() = _binding!!
    private var selectedAdminDate = System.currentTimeMillis()
    private var selectedDueDate = System.currentTimeMillis()
    private val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = DialogAddVaccinationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupVaccineTypeDropdown()
        setupDatePickers()
        setupButtons()

        // Set default dates
        binding.etAdministeredDate.setText(dateFormat.format(Date(selectedAdminDate)))
        binding.etNextDueDate.setText(dateFormat.format(Date(selectedDueDate)))
    }

    private fun setupVaccineTypeDropdown() {
        val vaccineTypes = listOf("FMD", "PPR", "HS", "BQ", "Anthrax", "Other")
        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_dropdown_item_1line,
            vaccineTypes
        )
        binding.spinnerVaccineType.setAdapter(adapter)
    }

    private fun setupDatePickers() {
        binding.etAdministeredDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            DatePickerDialog(
                requireContext(),
                { _, year, month, day ->
                    calendar.set(year, month, day)
                    selectedAdminDate = calendar.timeInMillis
                    binding.etAdministeredDate.setText(dateFormat.format(Date(selectedAdminDate)))
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        binding.etNextDueDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            DatePickerDialog(
                requireContext(),
                { _, year, month, day ->
                    calendar.set(year, month, day)
                    selectedDueDate = calendar.timeInMillis
                    binding.etNextDueDate.setText(dateFormat.format(Date(selectedDueDate)))
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
    }

    private fun setupButtons() {
        binding.btnSaveVaccination.setOnClickListener {
            val name = binding.etVaccineName.text.toString().trim()
            val type = binding.spinnerVaccineType.text.toString().trim()
            val location = binding.etCampLocation.text.toString().trim()

            if (name.isEmpty() || type.isEmpty() || location.isEmpty()) {
                Toast.makeText(requireContext(), "Fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val vaccination = Vaccination(
                animalId = animalId,
                vaccineName = name,
                vaccinationType = type,
                administeredDate = selectedAdminDate,
                nextDueDate = selectedDueDate,
                campLocation = location,
                isCompleted = true
            )

            onSave(vaccination)
            dismiss()
        }

        binding.btnCancelVaccination.setOnClickListener { dismiss() }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}