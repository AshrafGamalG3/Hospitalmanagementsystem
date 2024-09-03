package com.example.hospitalmanagementsystem.ui.reception.presentaion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController

import com.example.hospitalmanagementsystem.R
import com.example.hospitalmanagementsystem.databinding.FragmentCreateCallBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateCallFragment : Fragment() {
    private var _binding: FragmentCreateCallBinding? = null
    val binding get() = _binding!!
    val viewModel: ReceptionViewModel by viewModels()
    private var doctorId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_call, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentCreateCallBinding.bind(view)
        onClick()
        observeCreateCallLiveData()
    }


    fun onClick() {
        binding.editDoctor.setOnClickListener {
            val action =
                CreateCallFragmentDirections.actionCreateCallFragmentToSelectDoctorForCallsFragment(
                    "Doctor"
                )
            findNavController().navigate(action)
        }
        binding.btnBack.setOnClickListener {
            val action =
                CreateCallFragmentDirections.actionCreateCallFragmentToReceptionCallsFragment()
            findNavController().navigate(action)
        }


        binding.btnCreateCall.setOnClickListener {
            validation()


        }
    }

    private fun validation() {
        val patientName = binding.editPatientName.text.toString()
        val patientAge = binding.editPatientAge.text.toString()
        val patientPhone = binding.editPatientPhone.text.toString()
        val patientDescription = binding.editCaseDescription.text.toString()

        if (patientName.isEmpty()) {
            binding.editPatientName.error = getString(R.string.required)
        } else if (patientAge.isEmpty()) {
            binding.editPatientAge.error = getString(R.string.required)
        } else if (patientPhone.isEmpty()) {
            binding.editPatientPhone.error = getString(R.string.required)
        } else if (doctorId == 0) {

            Toast.makeText(
                requireContext(),
                getString(R.string.select_doctor_warn),
                Toast.LENGTH_SHORT
            ).show()
        } else if (patientDescription.isEmpty()) {
            binding.editCaseDescription.error = getString(R.string.required)
        } else {

            viewModel.createCall(
                patientName,
                patientAge,
                doctorId,
                patientPhone,
                patientDescription

            )
        }

    }

    private fun observeCreateCallLiveData() {
        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<Int>("doctorId")
            ?.observe(
                viewLifecycleOwner
            ) { result ->
                doctorId = result
            }
        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<String>("doctorName")
            ?.observe(
                viewLifecycleOwner
            ) { result ->
                binding.editDoctor.text = result
            }

        viewModel.creationCall.observe(viewLifecycleOwner) {
            val data = it
            if (data?.status == 1) {
                Toast.makeText(requireContext(), "Created successfully", Toast.LENGTH_SHORT).show()
                val action =
                    CreateCallFragmentDirections.actionCreateCallFragmentToRequestCompleteFragment()
                findNavController().navigate(action)
            } else {
                Toast.makeText(requireContext(), "Created Failed", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}