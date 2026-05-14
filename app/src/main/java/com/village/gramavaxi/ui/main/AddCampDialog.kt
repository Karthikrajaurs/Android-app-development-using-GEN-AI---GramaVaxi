package com.village.gramavaxi.ui.main

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.village.gramavaxi.data.entity.VaccinationCamp
import com.village.gramavaxi.databinding.DialogAddCampBinding
import java.text.SimpleDateFormat
import java.util.*

class AddCampDialog(
    private val onSave: (VaccinationCamp) -> Unit
) : BottomSheetDialogFragment() {

    private var _binding: DialogAddCampBinding? = null
    private val binding get() = _binding!!
    private var selectedCampDate = System.currentTimeMillis()
    private val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = DialogAddCampBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.etCampDate.setText(dateFormat.format(Date(selectedCampDate)))

        binding.etCampDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            DatePickerDialog(requireContext(), { _, y, m, d ->
                calendar.set(y, m, d)
                selectedCampDate = calendar.timeInMillis
                binding.etCampDate.setText(dateFormat.format(Date(selectedCampDate)))
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)).show()
        }

        binding.btnSaveCamp.setOnClickListener {
            val location = binding.etLocation.text.toString().trim()
            val vetName = binding.etVetName.text.toString().trim()
            val contact = binding.etContact.text.toString().trim()
            val vaccines = binding.etVaccines.text.toString().trim()
            val startTime = binding.etStartTime.text.toString().trim()
            val endTime = binding.etEndTime.text.toString().trim()

            if (location.isEmpty() || vetName.isEmpty() || contact.isEmpty()
                || vaccines.isEmpty() || startTime.isEmpty() || endTime.isEmpty()) {
                Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            onSave(VaccinationCamp(
                campDate = selectedCampDate,
                location = location,
                vaccineTypes = vaccines,
                startTime = startTime,
                endTime = endTime,
                veterinarianName = vetName,
                contactNumber = contact
            ))
            dismiss()
        }

        binding.btnCancelCamp.setOnClickListener { dismiss() }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}