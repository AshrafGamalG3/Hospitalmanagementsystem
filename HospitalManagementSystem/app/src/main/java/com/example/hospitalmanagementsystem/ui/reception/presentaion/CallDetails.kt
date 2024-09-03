package com.example.hospitalmanagementsystem.ui.reception.presentaion

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController

import com.example.hospitalmanagementsystem.R
import com.example.hospitalmanagementsystem.utils.Const
import com.example.hospitalmanagementsystem.databinding.FragmentCallDetailsBinding

import com.magdy.hospitalsystem.data.CallData
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CallDetails : Fragment() {
    private var _binding:FragmentCallDetailsBinding?=null
    val binding get() = _binding!!

    private val viewModel: ReceptionViewModel by viewModels ()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_call_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding= FragmentCallDetailsBinding.bind(view)

        val id=CallDetailsArgs.fromBundle(requireArguments()).id
        Log.e("TAG", "onViewCreated: $id ", )
observe()
        getCallById(id)
        onClick()
    }

    private fun observe(){
        viewModel.callData.observe(viewLifecycleOwner){
            Log.e("TAG", "observe: $it", )
            loadDataForCall(it)
        }
    }

    fun onClick(){
        binding.backButton.setOnClickListener {
            val action=CallDetailsDirections.actionCallDetailsToReceptionCallsFragment()
            findNavController().navigate(action)
        }


    }

    private fun loadDataForCall(call: CallData){
        binding.apply {
            textPatientAge.text=call.age
            textPatientDate.text=call.created_at
            textPatientName.text=call.patient_name
            textPatientPhone.text=call.phone
            textCaseDescription.text=call.description

            if (call.status == Const.STATUS_LOGOUT) {
                imageCondition.setImageResource(R.drawable.ic_check)
                btnLogout.visibility = View.GONE
            } else {
                imageCondition.setImageResource(R.drawable.ic_delay)
                btnLogout.visibility = View.VISIBLE
            }

        }
    }

    private fun getCallById(id:Int){
        viewModel.getCallById(id)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}