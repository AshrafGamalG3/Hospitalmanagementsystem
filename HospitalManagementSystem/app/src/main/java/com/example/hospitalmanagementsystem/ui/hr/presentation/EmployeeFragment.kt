package com.example.hospitalmanagementsystem.ui.hr.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController

import com.example.hospitalmanagementsystem.R
import com.example.hospitalmanagementsystem.utils.Const
import com.example.hospitalmanagementsystem.utils.MySharedPreferences
import com.example.hospitalmanagementsystem.databinding.FragmentEmployeeBinding
import com.example.hospitalmanagementsystem.ui.hr.presentation.adapter.AdapterRecyclerEmployee
import com.example.hospitalmanagementsystem.ui.hr.presentation.adapter.AdapterRecyclerTypes
import com.example.hospitalmanagementsystem.ui.reception.data.model.AllUserData

import com.example.hospitalmanagementsystem.ui.reception.presentaion.ReceptionViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EmployeeFragment : Fragment() {
    private var _binding: FragmentEmployeeBinding? = null
    private val binding get() = _binding!!
    private val typesList = ArrayList<String>()
    private var type = "All"
    private val viewModel: ReceptionViewModel by viewModels()
    private val adapterRecyclerTypes = AdapterRecyclerTypes()
    private val adapterRecyclerEmployee = AdapterRecyclerEmployee()
    private var originalDoctorList: ArrayList<AllUserData> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_employee, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentEmployeeBinding.bind(view)

        getAllUser(type)
        observeAllUserLiveData()
        onClick()
        initView()
        setupSearchView()
    }

    private fun observeAllUserLiveData() {
        viewModel.selectDoctorData.observe(viewLifecycleOwner) { doctorList ->
            originalDoctorList = ArrayList(doctorList)
            adapterRecyclerEmployee.list = originalDoctorList
            binding.recyclerEmployee.adapter = adapterRecyclerEmployee
        }
    }

    private fun onClick() {
        binding.btnAddUser.setOnClickListener {
            val action = EmployeeFragmentDirections.actionEmployeeFragmentToNewUserFragment()
            findNavController().navigate(action)
        }
        binding.btnBack.setOnClickListener {
            val action = EmployeeFragmentDirections.actionEmployeeFragmentToHrHomeFragment()
            findNavController().navigate(action)
        }
        adapterRecyclerTypes.onTapClick = object : AdapterRecyclerTypes.OnTapClick {
            override fun onClick(type: String) {
                this@EmployeeFragment.type = type
                viewModel.getAllDoctors(this@EmployeeFragment.type)
            }
        }
        adapterRecyclerEmployee.onUserClick = object : AdapterRecyclerEmployee.OnUserClick {
            override fun onClick(id: Int) {
                val action = EmployeeFragmentDirections.actionEmployeeFragmentToProfileFragment(id)
                findNavController().navigate(action)
            }
        }
    }

    private fun getAllUser(type: String) {
        viewModel.getAllDoctors(type)
    }

    private fun initView() {
        binding.apply {
            typesList.add("All")
            typesList.add(Const.DOCTOR)
            typesList.add(Const.NURSE)
            typesList.add(Const.ANALYSIS)
            typesList.add(Const.RECEPTIONIST)
            typesList.add(Const.MANAGER)
            typesList.add(Const.HR)
            adapterRecyclerTypes.list = typesList
            recyclerTypesTaps.adapter = adapterRecyclerTypes
            if (MySharedPreferences.getUserType() != Const.HR) {
                btnAddUser.visibility = View.GONE
            }
        }
    }

    private fun filterDoctors(query: String?) {
        val filteredList = if (query.isNullOrEmpty()) {
            originalDoctorList
        } else {
            originalDoctorList.filter {
                it.first_name.contains(query, ignoreCase = true)
            }
        }
        adapterRecyclerEmployee.list = filteredList as ArrayList<AllUserData>
        adapterRecyclerEmployee.notifyDataSetChanged()
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
}
