package com.example.hospitalmanagementsystem.ui.doctor.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.hospitalmanagementsystem.R
import com.example.hospitalmanagementsystem.databinding.FragmentDoctorHomeBinding
import com.example.hospitalmanagementsystem.utils.MySharedPreferences
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DoctorHomeFragment : Fragment() {

    private var _binding  : FragmentDoctorHomeBinding?= null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_doctor_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDoctorHomeBinding.bind(view)
        binding.textName.text=MySharedPreferences.getUserName()
        binding.textType.text=MySharedPreferences.getUserType()
        onClick()
    }

    private fun onClick(){


        binding.apply {
            cardProfile.setOnClickListener {
                val action=DoctorHomeFragmentDirections.actionDoctorHomeFragmentToProfileFragment(MySharedPreferences.getUserId())
                findNavController().navigate(action)
            }

            cardCalls.setOnClickListener{
                val action=DoctorHomeFragmentDirections.actionDoctorHomeFragmentToAllCallsFragment()
                findNavController().navigate(action)
            }

            cardCases.setOnClickListener{
                val action=DoctorHomeFragmentDirections.actionDoctorHomeFragmentToAllCasesFragment()
                findNavController().navigate(action)
            }

            cardTasks.setOnClickListener {
                val action=DoctorHomeFragmentDirections.actionDoctorHomeFragmentToTasksFragment()
                findNavController().navigate(action)

            }
            cardReports.setOnClickListener {
                val action=DoctorHomeFragmentDirections.actionDoctorHomeFragmentToReportsFragment()
                findNavController().navigate(action)
            }

        }
    }



}