package com.example.hospitalmanagementsystem.ui.common.calls_cases

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.hospitalmanagementsystem.R
import com.example.hospitalmanagementsystem.databinding.FragmentSelectDoctorForCallsBinding
import com.example.hospitalmanagementsystem.ui.reception.data.model.AllUserData
import com.example.hospitalmanagementsystem.ui.reception.presentaion.ReceptionViewModel

import com.example.hospitalmanagementsystem.ui.reception.presentaion.adapter.AdapterRecyclerSelectDoctor
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelectDoctorForCallsFragment : Fragment() {
    private var doctorId = 0
    private var doctorName = ""
    private var _binding: FragmentSelectDoctorForCallsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ReceptionViewModel by viewModels()
    private val adapterRecyclerSelectDoctor = AdapterRecyclerSelectDoctor()
    private var originalDoctorList: List<AllUserData> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_select_doctor_for_calls, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSelectDoctorForCallsBinding.bind(view)
        val type=SelectDoctorForCallsFragmentArgs.fromBundle(requireArguments()).type
        binding.textView3.text= "Select $type"
        binding.btnSelectDoctor.text="Select $type"
        onClicks()
        observe()
        getAllDoctors(type.lowercase())
        setupSearchView()
    }

    private fun getAllDoctors(type: String) {
        viewModel.getAllDoctors(type)
    }

    private fun observe() {
        viewModel.selectDoctorData.observe(viewLifecycleOwner) {
            adapterRecyclerSelectDoctor.rowIndex = -1
            originalDoctorList = it
            adapterRecyclerSelectDoctor.list = it as ArrayList<AllUserData>?
            binding.recyclerDoctors.adapter = adapterRecyclerSelectDoctor
        }
    }

    private fun onClicks() {
        adapterRecyclerSelectDoctor.onUserClick = object : AdapterRecyclerSelectDoctor.OnUserClick {
            override fun onClick(id: Int, name: String) {
                doctorId = id
                doctorName = name
            }
        }

        binding.apply {
            btnBack.setOnClickListener {
                requireActivity().onBackPressed()
            }

            btnSelectDoctor.setOnClickListener {
                if (doctorId == 0) {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.select_doctor_warn),
                        Toast.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }
                findNavController().previousBackStackEntry?.savedStateHandle?.set(
                    "doctorId",
                    doctorId
                )
                findNavController().previousBackStackEntry?.savedStateHandle?.set(
                    "doctorName",
                    doctorName
                )
                findNavController().popBackStack()
            }
        }
    }

    private fun setupSearchView() {
        binding.searchButton.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterDoctors(newText)
                return true
            }
        })
    }

    private fun filterDoctors(query: String?) {
        val filteredList = if (query.isNullOrEmpty()) {
            originalDoctorList
        } else {
            originalDoctorList.filter {
                it.first_name.contains(query, ignoreCase = true)
            }
        }
        adapterRecyclerSelectDoctor.list = filteredList as ArrayList<AllUserData>?
        adapterRecyclerSelectDoctor.notifyDataSetChanged()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
