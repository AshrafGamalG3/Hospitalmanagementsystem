package com.example.hospitalmanagementsystem.ui.nurse.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.hospitalmanagementsystem.R
import com.example.hospitalmanagementsystem.databinding.FragmentNurseHomeBinding
import com.example.hospitalmanagementsystem.utils.MySharedPreferences
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NurseHomeFragment : Fragment() {
    private var _binding: FragmentNurseHomeBinding? = null
    private val binding get() = _binding!!


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
        return inflater.inflate(R.layout.fragment_nurse_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentNurseHomeBinding.bind(view)

        onClicks()
    }
    private fun onClicks() {

        binding.textType.text = MySharedPreferences.getUserType()
        binding.textName.text  = MySharedPreferences.getUserName()
        binding.apply {


            cardAttendance.setOnClickListener {

                findNavController(). navigate(NurseHomeFragmentDirections.actionNurseHomeFragmentToAttendanceFragment())
            }

            cardProfile.setOnClickListener {
                findNavController(). navigate(
                    NurseHomeFragmentDirections
                        .actionNurseHomeFragmentToProfileFragment(
                            MySharedPreferences.getUserId()
                        )
                )
            }
            cardCases.setOnClickListener {
                findNavController().navigate(NurseHomeFragmentDirections.actionNurseHomeFragmentToAllCasesFragment())
            }

            cardTasks.setOnClickListener {
                findNavController().navigate(NurseHomeFragmentDirections.actionNurseHomeFragmentToTasksFragment())
            }

            cardReports.setOnClickListener {

                findNavController().navigate(NurseHomeFragmentDirections.actionNurseHomeFragmentToReportsFragment())
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }



}