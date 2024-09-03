package com.example.hospitalmanagementsystem.ui.nurse.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.hospitalmanagementsystem.R
import com.example.hospitalmanagementsystem.databinding.FragmentNurseDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NurseDetailsFragment : Fragment() {
private var _binding: FragmentNurseDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: NurseViewModel by viewModels()
    private var doctorId : Int ?= null
    private var patientName : String ?= null
    private var caseId = 0
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
        return inflater.inflate(R.layout.fragment_nurse_details, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentNurseDetailsBinding.bind(view)
        caseId = NurseDetailsFragmentArgs.fromBundle(requireArguments()).id
        onClicks()

    }

    private fun onClicks() {

        binding.apply {
            btnBack.setOnClickListener {
                findNavController().popBackStack()
            }
            layoutCaseDetailsNurse.btnCallDoctor.setOnClickListener {

                if (doctorId == null)
                    return@setOnClickListener

                viewModel.sendNotification(doctorId!! ,"Emergency"
                    , "come to patient $patientName")
            }


            layoutMedicalMeasurement.btnAddMeasurement.setOnClickListener {
                val suger = layoutMedicalMeasurement.editSuger.text.toString()
                val bloodPressure = layoutMedicalMeasurement.editBloodPressure.text.toString()
                val heartRate = layoutMedicalMeasurement.editHeartRate.text.toString()
                val fluidBalance = layoutMedicalMeasurement.editFluidBalance.text.toString()
                val respiratoryRate = layoutMedicalMeasurement.editRespiratoryRate.text.toString()
                val temp = layoutMedicalMeasurement.editTemp.text.toString()
                val note= layoutMedicalMeasurement.editNoteMeasurement.text.toString()
                if (bloodPressure.isEmpty()){
                    layoutMedicalMeasurement.editBloodPressure.error = getString(R.string.required)
                }else if (suger.isEmpty()){
                    layoutMedicalMeasurement.editSuger.error = getString(R.string.required)

                }else if (temp.isEmpty()){
                    layoutMedicalMeasurement.temp.error = getString(R.string.required)

                }else if (fluidBalance.isEmpty()){
                    layoutMedicalMeasurement.editFluidBalance.error = getString(R.string.required)

                }else if (respiratoryRate.isEmpty()){
                    layoutMedicalMeasurement.editRespiratoryRate.error = getString(R.string.required)

                }else if (heartRate.isEmpty()){
                    layoutMedicalMeasurement.editHeartRate.error = getString(R.string.required)

                }else{
                    viewModel.uploadMeasurement(caseId,bloodPressure
                        ,suger,temp,fluidBalance,respiratoryRate
                        ,heartRate,note,"done")
                }
            }

            btnCase.setOnClickListener {
                binding.apply {
                    layoutCaseDetailsNurse.parentCaseDetailsNurse.visibility=View.VISIBLE
                    layoutMedicalMeasurement.parentLayoutMedicalMeasurementNurse.visibility=View.GONE

                }
                btnMedicalMeasurement.background =
                    ContextCompat.getDrawable(requireContext(), R.drawable.rounded_gray_strock)
                btnCase.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.main_color))

            }
            btnMedicalMeasurement.setOnClickListener {
                btnCase.background =
                    ContextCompat.getDrawable(requireContext(), R.drawable.rounded_gray_strock)

                binding.apply {
                    layoutCaseDetailsNurse.parentCaseDetailsNurse.visibility=View.GONE
                    layoutMedicalMeasurement.parentLayoutMedicalMeasurementNurse.visibility=View.VISIBLE

                }
                btnMedicalMeasurement.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext()
                     ,
                        R.color.main_color
                    )
                )

            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}