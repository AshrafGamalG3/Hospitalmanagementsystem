package com.example.hospitalmanagementsystem.ui.reception.presentaion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import com.example.hospitalmanagementsystem.R
import com.example.hospitalmanagementsystem.ui.reception.presentaion.adapter.AdapterRecyclerAllCalls
import com.example.hospitalmanagementsystem.databinding.FragmentReceptionCallsBinding

import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


@AndroidEntryPoint
class ReceptionCallsFragment : Fragment(), DatePickerDialog.OnDateSetListener {
  private  var _binding:FragmentReceptionCallsBinding?=null
 private   val binding get() = _binding!!
    private val adapterCalls= AdapterRecyclerAllCalls()
    private val viewModel: ReceptionViewModel by viewModels ()
    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_reception_calls, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding= FragmentReceptionCallsBinding.bind(view)
        onClick()
        observe()
        setDateNow()

    }

    fun onClick(){
        binding.btnBack.setOnClickListener{
        val action=ReceptionCallsFragmentDirections.actionReceptionCallsFragmentToReceptionHome()

            findNavController().navigate(action)
        }
        binding.textDate.setOnClickListener {
    showDatePickerDialog()
        }
        binding.btnAddCall.setOnClickListener {
            val action=ReceptionCallsFragmentDirections.actionReceptionCallsFragmentToCreateCallFragment()
            findNavController().navigate(action)
        }

        adapterCalls.onClickCall=object :AdapterRecyclerAllCalls.OnClickCall{
            override fun onClick(id: Int) {
        val action=ReceptionCallsFragmentDirections.actionReceptionCallsFragmentToCallDetails(id)
                findNavController().navigate(action)
            }

        }


    }

    private fun getAllCalls(date:String){
        viewModel.getAllCalls(date)
    }
    fun observe(){
        viewModel.mutableLiveData.observe(viewLifecycleOwner){
            adapterCalls.callsList=it
            binding.recyclerCalls.adapter=adapterCalls

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
        getAllCalls(selectedDate)
    }

    private fun setDateNow() {
        val calendar = Calendar.getInstance()
        val locale = Locale("in", "ID")
        val simpleDateFormat = SimpleDateFormat("yyyy-M-d", locale)
        val currentDate = simpleDateFormat.format(calendar.time)
        binding.textDate.text = currentDate
        getAllCalls(currentDate)
    }

        override fun onDestroy() {
            super.onDestroy()
            _binding = null

        }
    }