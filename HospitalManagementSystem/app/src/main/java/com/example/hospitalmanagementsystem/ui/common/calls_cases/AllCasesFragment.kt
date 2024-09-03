package com.example.hospitalmanagementsystem.ui.common.calls_cases

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.hospitalmanagementsystem.R
import com.example.hospitalmanagementsystem.databinding.FragmentAllCasesBinding
import com.example.hospitalmanagementsystem.ui.doctor.data.model.DataDoctorCases
import com.example.hospitalmanagementsystem.ui.doctor.presentation.DoctorViewModel
import com.example.hospitalmanagementsystem.ui.doctor.presentation.adapter.AdapterRecyclerAllCases
import com.example.hospitalmanagementsystem.utils.Const
import com.example.hospitalmanagementsystem.utils.MySharedPreferences
import com.example.hospitalmanagementsystem.utils.ProgressLoading
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllCasesFragment : Fragment() {

    private var _binding: FragmentAllCasesBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DoctorViewModel by viewModels()
    private val adapterDoctorCases = AdapterRecyclerAllCases()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_all_cases, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentAllCasesBinding.bind(view)
        ProgressLoading.show(requireActivity())
        getAllCases()
        observe()
        onClick()
    }

    private fun onClick(){
        binding.btnBack.setOnClickListener {
        requireActivity().onBackPressed()
        }
        adapterDoctorCases.onShowClick=object : AdapterRecyclerAllCases.OnShowClick {
            override fun show(id: Int) {
                if (MySharedPreferences.getUserType() == Const.DOCTOR
                    || MySharedPreferences.getUserType() == Const.MANAGER) {
                    val action=AllCasesFragmentDirections.actionAllCasesFragmentToCaseDetailsFragment(id)
                    findNavController().navigate(action)

                }else if (MySharedPreferences.getUserType() == Const.NURSE){
                    val action=AllCasesFragmentDirections.actionAllCasesFragmentToNurseDetailsFragment(id)
                    findNavController().navigate(action)

                }else if (MySharedPreferences.getUserType() == Const.ANALYSIS){

                }
            }

        }


    }

    private fun getAllCases(){
        viewModel.getDoctorCases()

    }
    private fun observe(){
        viewModel.doctorCases.observe(viewLifecycleOwner){
            ProgressLoading.dismiss()
            adapterDoctorCases.listDataDoctorCases= it as ArrayList<DataDoctorCases>
            binding.recyclerDoctorCases.adapter=adapterDoctorCases
        }
    }
}