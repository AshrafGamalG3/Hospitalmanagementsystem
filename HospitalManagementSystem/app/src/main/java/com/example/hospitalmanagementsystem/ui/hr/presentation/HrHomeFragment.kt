package com.example.hospitalmanagementsystem.ui.hr.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.hospitalmanagementsystem.R
import com.example.hospitalmanagementsystem.utils.MySharedPreferences
import com.example.hospitalmanagementsystem.databinding.FragmentHrHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HrHomeFragment : Fragment() {
    var _binding:FragmentHrHomeBinding?=null
    val binding get() = _binding!!



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_hr_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding= FragmentHrHomeBinding.bind(view)
        binding.textType.text= MySharedPreferences.getUserType()
        binding.textUserName.text= MySharedPreferences.getUserName()
        onClick()
    }

    fun onClick(){
        binding.cardProfile.setOnClickListener {
            val action =HrHomeFragmentDirections.actionHrHomeFragmentToProfileFragment(
                MySharedPreferences.getUserId())
            findNavController().navigate(action)
        }

        binding.cardEmploye.setOnClickListener {
            val action=HrHomeFragmentDirections.actionHrHomeFragmentToEmployeeFragment()
            findNavController().navigate(action)
        }
        binding.cardTasks.setOnClickListener {
            val action=HrHomeFragmentDirections.actionHrHomeFragmentToTasksFragment()
            findNavController().navigate(action)
        }
        binding.cardReports.setOnClickListener {
            val action=HrHomeFragmentDirections.actionHrHomeFragmentToReportsFragment()
            findNavController().navigate(action)
        }
    }
}