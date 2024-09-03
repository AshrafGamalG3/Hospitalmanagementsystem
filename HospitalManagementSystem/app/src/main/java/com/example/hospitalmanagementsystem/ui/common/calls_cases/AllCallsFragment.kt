package com.example.hospitalmanagementsystem.ui.common.calls_cases

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.hospitalmanagementsystem.R
import com.example.hospitalmanagementsystem.data.showToast
import com.example.hospitalmanagementsystem.databinding.FragmentAllCallsBinding
import com.example.hospitalmanagementsystem.ui.doctor.data.model.DataDoctorCalls
import com.example.hospitalmanagementsystem.ui.doctor.presentation.DoctorViewModel
import com.example.hospitalmanagementsystem.ui.doctor.presentation.adapter.AdapterDoctorCalls
import com.example.hospitalmanagementsystem.utils.ProgressLoading
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllCallsFragment : Fragment() {
    private var _binding: FragmentAllCallsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DoctorViewModel by viewModels()
    private val adapterDoctorCalls = AdapterDoctorCalls()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_all_calls, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentAllCallsBinding.bind(view)
        ProgressLoading.show(requireActivity())
        setupRecyclerView()
        getAllCalls()
        observe()
    }

    private fun setupRecyclerView() {
        binding.recyclerDoctorCalls.adapter = adapterDoctorCalls
        adapterDoctorCalls.onUserClick = object : AdapterDoctorCalls.OnUserClick {
            override fun onAccept(id: Int, position: Int) {
                viewModel.getAcceptORRejectCall(id, "accept")
                updateCallStatus(position, "accepted")
            }

            override fun onReject(id: Int, position: Int) {
                viewModel.getAcceptORRejectCall(id, "reject")
                updateCallStatus(position, "rejected")
            }
        }
        binding.btnBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun getAllCalls() {
        viewModel.getDoctorCalls()
    }

    private fun observe() {
        viewModel.doctorCalls.observe(viewLifecycleOwner) {
            ProgressLoading.dismiss()
            adapterDoctorCalls.doctorCalls = it.data as ArrayList<DataDoctorCalls>
            adapterDoctorCalls.notifyDataSetChanged()
        }

        viewModel.callSuccess.observe(viewLifecycleOwner) {
            getAllCalls()
        }
    }

    private fun updateCallStatus(position: Int, status: String) {
        adapterDoctorCalls.doctorCalls[position].status = status
        adapterDoctorCalls.notifyItemChanged(position)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
