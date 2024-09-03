package com.example.hospitalmanagementsystem.ui.common.reports.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.hospitalmanagementsystem.R
import com.example.hospitalmanagementsystem.databinding.FragmentReportsBinding
import com.example.hospitalmanagementsystem.ui.common.reports.data.model.ReportsData
import com.example.hospitalmanagementsystem.ui.common.reports.presentation.adapter.ReportsAdapter
import com.example.hospitalmanagementsystem.utils.Const
import com.example.hospitalmanagementsystem.utils.MySharedPreferences
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@AndroidEntryPoint
class ReportsFragment : Fragment(), DatePickerDialog.OnDateSetListener {
    private var _binding: FragmentReportsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ReportsViewModel by viewModels()
    private val adapter = ReportsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_reports, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentReportsBinding.bind(view)
        onClicks()
        setDateNow()
        observe()
    }

    private fun onClicks (){

        if(MySharedPreferences.getUserType() == Const.MANAGER){
            binding.btnAddReport.visibility = View.VISIBLE
        }


        binding.apply {
            btnDate.setOnClickListener {
                showDatePickerDialog()
            }
            btnBack.setOnClickListener {
                requireActivity().onBackPressed()
            }
            btnAddReport.setOnClickListener {
            findNavController().navigate(R.id.action_reportsFragment_to_createReportFragment)
            }
        }

        adapter.onItemClickListener=object :ReportsAdapter.OnItemClickListener{
            override fun onItemClick(report: ReportsData) {
                val action = ReportsFragmentDirections.actionReportsFragmentToShowReportFragment(report.id)
                findNavController().navigate(action)
            }

        }


    }


    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val datePickerDialog = DatePickerDialog.newInstance(
            this,
            calendar[Calendar.YEAR],
            calendar[Calendar.MONTH],
            calendar[Calendar.DAY_OF_MONTH]
        )


        val minDateCalendar = Calendar.getInstance()
        minDateCalendar.set(1900, Calendar.JANUARY, 1)
        datePickerDialog.minDate = minDateCalendar

        datePickerDialog.isThemeDark = false
        datePickerDialog.showYearPickerFirst(true)
        datePickerDialog.show(parentFragmentManager, "Datepickerdialog")
    }
    override fun onDateSet(view: DatePickerDialog?, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(year, monthOfYear, dayOfMonth)
        val selectedDate = SimpleDateFormat("yyyy-M-d", Locale("in", "ID")).format(calendar.time)
        binding.textDate.text = selectedDate
        getAllReports(selectedDate)
    }

    private fun setDateNow() {
        val calendar = Calendar.getInstance()
        val locale = Locale("in", "ID")
        val simpleDateFormat = SimpleDateFormat("yyyy-M-d", locale)
        val currentDate = simpleDateFormat.format(calendar.time)
        binding.textDate.text = currentDate
        getAllReports(currentDate)
    }

    private fun getAllReports(date:String){
        viewModel.getAllReports(date)
    }
    private fun observe(){
        viewModel.reports.observe(viewLifecycleOwner){
            adapter.reports = it.data
            binding.recyclerReports.adapter = adapter
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}