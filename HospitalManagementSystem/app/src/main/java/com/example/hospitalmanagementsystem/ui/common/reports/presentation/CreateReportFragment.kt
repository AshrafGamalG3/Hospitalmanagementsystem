package com.example.hospitalmanagementsystem.ui.common.reports.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.hospitalmanagementsystem.R
import com.example.hospitalmanagementsystem.databinding.FragmentCreateReportBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CreateReportFragment : Fragment() {
    private var _binding: FragmentCreateReportBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ReportsViewModel by viewModels()

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
        return inflater.inflate(R.layout.fragment_create_report, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentCreateReportBinding.bind(view)
        validation()
        onClick()
        observers()

    }

    private fun onClick() {
        binding.apply {
            btnCreateReport.setOnClickListener {
                validation()
            }
            btnBack.setOnClickListener {
                requireActivity().onBackPressed()
            }
        }
    }

    private fun observers() {
        viewModel.createReport.observe(viewLifecycleOwner) {
            requireActivity().onBackPressed()
        }
    }

    private fun validation() {
        val reportTittle = binding.editReportTittle.text.toString()
        val reportDescription = binding.editReportDescription.text.toString()

        if (reportTittle.isEmpty()) {
            binding.editReportTittle.error = getString(R.string.required)
        } else if (reportDescription.isEmpty()) {
            binding.editReportDescription.error = getString(R.string.required)
        } else {
            viewModel.createReport(reportTittle, reportDescription)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}