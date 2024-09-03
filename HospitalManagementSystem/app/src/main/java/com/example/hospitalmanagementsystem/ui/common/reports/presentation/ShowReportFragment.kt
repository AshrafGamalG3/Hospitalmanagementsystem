package com.example.hospitalmanagementsystem.ui.common.reports.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.hospitalmanagementsystem.R
import com.example.hospitalmanagementsystem.databinding.FragmentShowReportBinding
import com.example.hospitalmanagementsystem.ui.common.reports.data.model.ModelShowReport
import com.example.hospitalmanagementsystem.utils.Const
import com.example.hospitalmanagementsystem.utils.MySharedPreferences
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShowReportFragment : Fragment() {
    private var _binding: FragmentShowReportBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ReportsViewModel by viewModels()

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
        return inflater.inflate(R.layout.fragment_show_report, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentShowReportBinding.bind(view)
        val id=ShowReportFragmentArgs.fromBundle(requireArguments()).id
        showReport(id)
        hideMangerView()
        reportObserver()
        onClick(id)
    }

    private fun onClick(id: Int){
        binding.apply {
            btnBack.setOnClickListener {
                requireActivity().onBackPressed()
            }
            btnEndReport.setOnClickListener {
                endReport(id)
            }


            btnSendReplyReport.setOnClickListener {
                val answer  = editReplyManager.text.toString().trim()

                if (answer.isEmpty()) {
                    editReplyManager.error =  getString(R.string.required)
                    return@setOnClickListener
                }

                viewModel.answerReport(id,answer)
            }
        }
    }

    private fun reportObserver() {
        viewModel.showReport.observe(viewLifecycleOwner) {
            loadData(it)
        }
        viewModel.endReport.observe(viewLifecycleOwner) {
            requireActivity().onBackPressed()
        }

        viewModel.answerReport.observe(viewLifecycleOwner){
            requireActivity().onBackPressed()
        }
    }
    private fun endReport(id: Int) {
        viewModel.endReport(id)
    }
    private fun loadData(showReport: ModelShowReport){
        val data = showReport.data
        binding.apply {
            data.apply {
                textReportName.text =report_name
                textDate.text = created_at
                textDetails.text = description
                textUserName.text = user.first_name
                textDate2.text = created_at
                textDetailsManager.text = answer
                textManagerName.text = manger.first_name+" "+manger.last_name


                if (answer.isNotEmpty()){
                    textDate2.visibility=View.VISIBLE
                    cardView2.visibility=View.VISIBLE
                    textType2.visibility=View.VISIBLE
                    textDetailsManager.visibility=View.VISIBLE
                }


            }
        }

    }

    private fun hideMangerView(){
        binding.apply {
            if (MySharedPreferences.getUserType() == Const.MANAGER) {

                textDate2.visibility=View.GONE
                cardView2.visibility=View.GONE
                textType2.visibility=View.GONE
                textDetailsManager.visibility=View.GONE
                btnEndReport.visibility=View.GONE
            } else {
                editReplyManager.visibility=View.GONE
                btnSendReplyReport.visibility=View.GONE
            }
        }

    }

    private fun showReport(id: Int) {
     viewModel.showReport(id)
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}