package com.example.hospitalmanagementsystem.ui.reception.presentaion

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

import com.example.hospitalmanagementsystem.R
import com.example.hospitalmanagementsystem.utils.MySharedPreferences
import com.example.hospitalmanagementsystem.databinding.FragmentReceptionHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReceptionHome : Fragment() {
var _binding:FragmentReceptionHomeBinding?=null
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

        return inflater.inflate(R.layout.fragment_reception_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding= FragmentReceptionHomeBinding.bind(view)
    loadData()
    onClick()

    }
private fun loadData(){
    binding.textName.text= MySharedPreferences.getUserName()
    binding.textType.text= MySharedPreferences.getUserType()
}
    private fun onClick(){
        binding.cardProfile.setOnClickListener{
            val action=ReceptionHomeDirections.actionReceptionHomeToProfileFragment(
                MySharedPreferences.getUserId())
            findNavController().navigate(action)
        }
        binding.calls.setOnClickListener{
            val action=ReceptionHomeDirections.actionReceptionHomeToReceptionCallsFragment()
            findNavController().navigate(action)
        }
        binding.cardTasks.setOnClickListener {
            val action= ReceptionHomeDirections.actionReceptionHomeToTasksFragment()

            findNavController().navigate(action)

        }
        binding.cardReports.setOnClickListener {
            val action= ReceptionHomeDirections.actionReceptionHomeToReportsFragment()
            findNavController().navigate(action)
        }

    }
    override fun onDestroy() {
        super.onDestroy()
        _binding=null


    }

}