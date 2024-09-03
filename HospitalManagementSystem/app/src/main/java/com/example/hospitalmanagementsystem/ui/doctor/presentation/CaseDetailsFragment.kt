
package com.example.hospitalmanagementsystem.ui.doctor.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.hospitalmanagementsystem.R
import com.example.hospitalmanagementsystem.databinding.FragmentCaseDetailsBinding
import com.example.hospitalmanagementsystem.ui.doctor.data.repo.IDoctorRepo
import com.example.hospitalmanagementsystem.utils.Const
import com.example.hospitalmanagementsystem.utils.ProgressLoading
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CaseDetailsFragment : Fragment() {

    private var _binding: FragmentCaseDetailsBinding? = null
    private val binding get() = _binding!!

    private val viewModel : DoctorViewModel by viewModels()
    var nurse=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_case_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding=FragmentCaseDetailsBinding.bind(view)

        val caseId=CaseDetailsFragmentArgs.fromBundle(requireArguments()).id
        ProgressLoading.show(requireActivity())
        getCaseById(caseId)
        observe(caseId)
        onCLick(caseId)
        Log.e("TAG", "observe:  , caseId $caseId ", )
    }
    private fun getCaseById(caseId:Int){
        viewModel.getCaseById(caseId)
    }
    private fun addNurse(caseId:Int,doctorId:Int){
        viewModel.addNurse(caseId,doctorId)
    }
    private fun observe(caseId:Int) {

        if (nurse==""){
            binding.layoutCaseDetails.textPatientNurse.visibility=View.GONE
            binding.layoutCaseDetails.patientNurse.visibility=View.GONE
            binding.layoutCaseDetails.btnRequest2.visibility=View.GONE

        }else{
            binding.layoutCaseDetails.textPatientNurse.visibility=View.VISIBLE
            binding.layoutCaseDetails.patientNurse.visibility=View.VISIBLE
            binding.layoutCaseDetails.btnAddNurse.visibility=View.GONE
            binding.layoutCaseDetails.btnRequest.visibility=View.GONE
            binding.layoutCaseDetails.btnRequest2.visibility=View.VISIBLE

        }


        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<Int>("doctorId")
            ?.observe(
                viewLifecycleOwner
            ) { id ->
                if (id != 0) {
                    Log.e("TAG", "observe: doctorId $id , caseId $caseId ", )
                    addNurse(caseId, id)
                    findNavController().currentBackStackEntry?.savedStateHandle?.set("doctorId", 0)
                }
            }
        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<String>("doctorName")
            ?.observe(
                viewLifecycleOwner
            ) { result ->
                Log.e("TAG", "observe: $result", )
                nurse=result
            }

        viewModel.callSuccess.observe(viewLifecycleOwner){
            if (nurse==""){
                binding.layoutCaseDetails.textPatientNurse.visibility=View.GONE
                binding.layoutCaseDetails.patientNurse.visibility=View.GONE
                binding.layoutCaseDetails.btnRequest2.visibility=View.GONE

            }else{
                binding.layoutCaseDetails.textPatientNurse.visibility=View.VISIBLE
                binding.layoutCaseDetails.patientNurse.visibility=View.VISIBLE
                binding.layoutCaseDetails.btnAddNurse.visibility=View.GONE
                binding.layoutCaseDetails.btnRequest.visibility=View.GONE
                binding.layoutCaseDetails.btnRequest2.visibility=View.VISIBLE

            }
        }

        viewModel.caseById.observe(viewLifecycleOwner) {
            val data = it.data
            ProgressLoading.dismiss()
            binding.layoutMedicalMeasurement.apply {
                data.apply {
                    textDate.text = created_at
                    textDetails.text = measurement_note
                    textBloodPressure.text = blood_pressure
                    textSuger.text = sugar_analysis
                    textTemp.text = tempreture
                    textFluidBalance.text = fluid_balance
                    textRespiratoryRate.text = respiratory_rate
                    textHeartRate.text = heart_rate
                    textUserName.text=nurse_id
                }
            }
            binding.layoutCaseDetails.apply {
                data.apply {

                    textPatientAge.text = age + " " + getString(R.string.years)
                    textPatientDate.text = created_at
                    textPatientPhone.text = phone
                    textPatientName.text = patient_name
                    textPatientDesc.text = description
                    textPatientStatus.text = status
                    textPatientDoctor.text = doctor_id
                    textPatientNurse.text = nurse_id
                    if (status == Const.STATUS_LOGOUT) {
                        imageCondition.setImageResource(R.drawable.ic_check)
                    } else {
                        imageCondition.setImageResource(R.drawable.ic_delay)

                    }
                }
            }
            binding.layoutMedicalRecord.apply {
                data.apply {
                    textDate.text = created_at
                    textDetails.text = description
                    textUserName.text=doctor_id

                }
            }

        }
    }
    private fun onCLick(caseId: Int){
        binding.apply {
            btnBack.setOnClickListener {
                requireActivity().onBackPressed()
            }
            layoutCaseDetails.btnRequest.setOnClickListener {
                val action=CaseDetailsFragmentDirections.actionCaseDetailsFragmentToRequestAnalysisFragment(caseId)
                findNavController().navigate(action)
            }
            binding.layoutCaseDetails.btnRequest2.setOnClickListener {
                val action=CaseDetailsFragmentDirections.actionCaseDetailsFragmentToRequestAnalysisFragment(caseId)
                findNavController().navigate(action)
            }
            layoutCaseDetails.btnAddNurse.setOnClickListener {
                val action=CaseDetailsFragmentDirections.actionCaseDetailsFragmentToSelectDoctorForCallsFragment("Nurse")
                findNavController().navigate(action)
            }

            layoutCaseDetails.btnRequest.setOnClickListener {
                val action=CaseDetailsFragmentDirections.actionCaseDetailsFragmentToRequestAnalysisFragment(caseId)
                findNavController().navigate(action)
            }

            btnCase.setOnClickListener {
                btnMedicalRecord.background =
                    ContextCompat.getDrawable(requireContext(), R.drawable.rounded_gray_strock)
                binding.apply {
                    layoutCaseDetails.layoutCaseDetails.visibility=View.VISIBLE
                    layoutMedicalMeasurement.parentLayoutMedicalMeasurement.visibility=View.GONE
                    layoutMedicalRecord.parentMedicalRecordLayout.visibility=View.GONE
                }
                btnMedicalMeasurement.background =
                    ContextCompat.getDrawable(requireContext(), R.drawable.rounded_gray_strock)
                btnCase.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.main_color))

            }
            btnMedicalMeasurement.setOnClickListener {
                btnCase.background =
                    ContextCompat.getDrawable(requireContext(), R.drawable.rounded_gray_strock)

                binding.apply {
                    layoutCaseDetails.layoutCaseDetails.visibility=View.GONE
                    layoutMedicalMeasurement.parentLayoutMedicalMeasurement.visibility=View.VISIBLE
                    layoutMedicalRecord.parentMedicalRecordLayout.visibility=View.GONE
                }
                btnMedicalRecord.background =
                    ContextCompat.getDrawable(requireContext(), R.drawable.rounded_gray_strock)
                btnMedicalMeasurement.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.main_color
                    )
                )

            }
            btnMedicalRecord.setOnClickListener {
                btnCase.background =
                    ContextCompat.getDrawable(requireContext(), R.drawable.rounded_gray_strock)
                binding.apply {
                    layoutCaseDetails.layoutCaseDetails.visibility=View.GONE
                    layoutMedicalMeasurement.parentLayoutMedicalMeasurement.visibility=View.GONE
                    layoutMedicalRecord.parentMedicalRecordLayout.visibility=View.VISIBLE
                }
                btnMedicalMeasurement.background =
                    ContextCompat.getDrawable(requireContext(), R.drawable.rounded_gray_strock)
                btnMedicalRecord.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.main_color
                    )
                )

            }
        }

    }


    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}
