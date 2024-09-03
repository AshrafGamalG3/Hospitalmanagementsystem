package com.example.hospitalmanagementsystem.ui.reception.presentaion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

import com.example.hospitalmanagementsystem.R
import com.example.hospitalmanagementsystem.databinding.FragmentRequestCompleteBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RequestCompleteFragment : Fragment() {
    var _binding:FragmentRequestCompleteBinding?=null
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_request_complete, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding= FragmentRequestCompleteBinding.bind(view)
        onClick()
    }

    fun onClick(){
        binding.btnBackToHome.setOnClickListener {
            val action =RequestCompleteFragmentDirections.actionRequestCompleteFragmentToReceptionHome()
            findNavController().navigate(action)
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null

    }
}