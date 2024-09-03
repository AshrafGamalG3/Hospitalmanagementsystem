package com.example.hospitalmanagementsystem.ui.doctor.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.hospitalmanagementsystem.R
import com.example.hospitalmanagementsystem.data.showToast
import com.example.hospitalmanagementsystem.databinding.FragmentRequestAnalysisBinding
import com.magdy.hospitalsystem.adapters.AdapterRecyclerAnalysis
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RequestAnalysisFragment : Fragment() {

    private var _binding: FragmentRequestAnalysisBinding? = null
    private val binding get() = _binding!!
    private val adapterRecyclerAnalysis = AdapterRecyclerAnalysis()
    private val viewModel: DoctorViewModel by viewModels()
    private var analystId = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRequestAnalysisBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val caseId = RequestAnalysisFragmentArgs.fromBundle(requireArguments()).caseId

        binding.recyclerMedicalRecord.adapter = adapterRecyclerAnalysis
        onClick(caseId)
        observe()
    }


    private fun onClick(caseId: Int) {
        adapterRecyclerAnalysis.onRemoveClick = object : AdapterRecyclerAnalysis.OnRemoveClick {
            override fun onClick(name: String) {
                viewModel.removeItem(name)
            }
        }

        binding.apply {
            btnBack.setOnClickListener {
                requireActivity().onBackPressed()
            }

            btnSend.setOnClickListener {
                if (viewModel.analysisList.value.isNullOrEmpty()) {
                    showToast(getString(R.string.analysis_warn))
                    return@setOnClickListener
                } else if (analystId == 0) {
                    showToast(getString(R.string.analysis_emp_warn))
                    return@setOnClickListener
                }
                viewModel.requestAnalysis(caseId, analystId, binding.editNoteTask.text.toString(), viewModel.analysisList.value!!)
            }

            editDoctor.setOnClickListener {
                val action = RequestAnalysisFragmentDirections.actionRequestAnalysisFragmentToSelectDoctorForCallsFragment("Analysis")
                findNavController().navigate(action)
            }

            btnAdd.setOnClickListener {
                val analysis = binding.editMeasurement.text.toString()
                if (analysis.isBlank()) {
                    binding.editMeasurement.error = getString(R.string.required)
                    return@setOnClickListener
                }
                viewModel.addItem(analysis)
                binding.editMeasurement.setText("")
            }
        }
    }

    private fun observe() {
        viewModel.analysisList.observe(viewLifecycleOwner) { analysisList ->
            adapterRecyclerAnalysis.list = analysisList
            adapterRecyclerAnalysis.notifyDataSetChanged()
        }

        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<Int>("doctorId")
            ?.observe(viewLifecycleOwner) { id ->
                analystId = id
            }

        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<String>("doctorName")
            ?.observe(viewLifecycleOwner) { result ->
                binding.editDoctor.text = result
            }

        viewModel.callSuccess.observe(viewLifecycleOwner) {
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
