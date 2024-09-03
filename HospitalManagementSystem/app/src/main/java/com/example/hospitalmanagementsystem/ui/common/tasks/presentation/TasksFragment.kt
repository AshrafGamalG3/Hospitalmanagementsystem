package com.example.hospitalmanagementsystem.ui.common.tasks.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.hospitalmanagementsystem.R
import com.example.hospitalmanagementsystem.databinding.FragmentTasksBinding
import com.example.hospitalmanagementsystem.ui.common.tasks.data.model.TasksData
import com.example.hospitalmanagementsystem.ui.common.tasks.presentation.adapter.TasksAdapter
import com.google.android.material.datepicker.MaterialDatePicker.Builder.datePicker
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@AndroidEntryPoint
class TasksFragment : Fragment(), DatePickerDialog.OnDateSetListener {
    private var _binding: FragmentTasksBinding? = null
    private val binding get() = _binding!!
    private val viewModel: TasksViewModel by viewModels()
    private val adapter = TasksAdapter()




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
        return inflater.inflate(R.layout.fragment_tasks, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentTasksBinding.bind(view)
        observe()
        setDateNow()
        onClick()
    }

    private fun onClick(){
        adapter.listener=object :TasksAdapter.onItemClickListener{
            override fun onItemClick(item: TasksData) {
                val action = TasksFragmentDirections.actionTasksFragmentToTaskDetailsFragment(item.id)
                findNavController().navigate(action)

            }
        }
        binding.apply {
            imageView5.setOnClickListener {
               showDatePickerDialog()
            }
            btnBack.setOnClickListener {
                requireActivity().onBackPressed()
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
        getAllTasks(selectedDate)
    }

    private fun setDateNow() {
        val calendar = Calendar.getInstance()
        val locale = Locale("in", "ID")
        val simpleDateFormat = SimpleDateFormat("yyyy-M-d", locale)
        val currentDate = simpleDateFormat.format(calendar.time)
        binding.textDate.text = currentDate
        getAllTasks(currentDate)
    }

    private fun getAllTasks(date:String){
        viewModel.getAllTasks(date)
    }
    private fun observe(){
    viewModel.tasks.observe(viewLifecycleOwner){
        adapter.list= it.data
        binding.recyclerTasks.adapter = adapter
    }
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}