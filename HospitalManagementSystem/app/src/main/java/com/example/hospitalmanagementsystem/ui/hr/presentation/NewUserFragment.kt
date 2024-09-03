package com.example.hospitalmanagementsystem.ui.hr.presentation

import android.app.DatePickerDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController

import com.example.hospitalmanagementsystem.R
import com.example.hospitalmanagementsystem.utils.Const.ANALYSIS
import com.example.hospitalmanagementsystem.utils.Const.ANALYSIS_VIEW_KEY
import com.example.hospitalmanagementsystem.databinding.FragmentNewUserBinding
import com.example.hospitalmanagementsystem.data.showToast
import dagger.hilt.android.AndroidEntryPoint
import java.util.Calendar

@AndroidEntryPoint
class NewUserFragment : Fragment() {

private var _binding:FragmentNewUserBinding?=null
    private val binding get() =_binding!!
    val viewModel: HrViewModel by viewModels()
    var  birthday = ""
    private val cal: Calendar = Calendar.getInstance()
    private var startDateSetListener: DatePickerDialog.OnDateSetListener? = null
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
        return inflater.inflate(R.layout.fragment_new_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding= FragmentNewUserBinding.bind(view)
        onClicks()
        observeCreateLiveData()
    }

    private fun validation() {
        binding.apply {
            val fName = editFname.text.toString()
            val lName = editLname.text.toString()
            val email = editEmail.text.toString()
            val password = editPassword.text.toString()
            val address = editAddress.text.toString()
            val phone = editPhone.text.toString()
            val gender = spinnerGender.selectedItem.toString()
            val type = spinnerSpecialist.selectedItem.toString()
            val status = spinnerStatus.selectedItem.toString()

            val emailPattern = Patterns.EMAIL_ADDRESS

            when {
                fName.isEmpty() -> {
                    editFname.error = getString(R.string.required)
                }
                lName.isEmpty() -> {
                    editLname.error = getString(R.string.required)
                }
                spinnerGender.selectedItemPosition == 0 -> {
                    Toast.makeText(requireContext(), getString(R.string.please_select_gender), Toast.LENGTH_SHORT).show()
                }
                spinnerSpecialist.selectedItemPosition == 0 -> {
                    Toast.makeText(requireContext(), getString(R.string.specialist_hint), Toast.LENGTH_SHORT).show()
                }
                birthday.isEmpty() -> {
                    Toast.makeText(requireContext(), getString(R.string.birthday_hint), Toast.LENGTH_SHORT).show()
                }
                spinnerStatus.selectedItemPosition == 0 -> {
                    Toast.makeText(requireContext(), getString(R.string.status_hint), Toast.LENGTH_SHORT).show()
                }
                phone.isEmpty() -> {
                    editPhone.error = getString(R.string.required)
                }
                address.isEmpty() -> {
                    editAddress.error = getString(R.string.required)
                }
                email.isEmpty() -> {
                    editEmail.error = getString(R.string.required)
                }
                !emailPattern.matcher(email).matches() -> {
                    editEmail.error = getString(R.string.wrong_email_address)
                }
                password.isEmpty() -> {
                    editPassword.error = getString(R.string.required)
                }
                else -> {
                    val finalType = if (type == ANALYSIS_VIEW_KEY) ANALYSIS else type
                    viewModel.createUser(
                        email,
                        password,
                        fName,
                        lName,
                        gender,
                        finalType,
                        birthday,
                        status,
                        address,
                        phone,
                        finalType
                    )
                }
            }
        }
    }

    private fun onClicks() {
        startDateSetListener =
            DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->

                val realMonth = month + 1

                val myMonth = if (realMonth < 10) "0$realMonth" else realMonth.toString()
                val myday = if (dayOfMonth < 10) "0$dayOfMonth" else dayOfMonth.toString()

                birthday = "${year}-${myMonth}-${myday}"
                binding.editBirthday.text = birthday
            }
        binding.apply {
            btnCreate.setOnClickListener {
                validation ()
            }
            editBirthday.setOnClickListener {
                dataPicker()
            }


            btnBack.setOnClickListener {
                val  action=NewUserFragmentDirections.actionNewUserFragmentToEmployeeFragment()
                findNavController().navigate(action)
            }
        }
    }

    private fun observeCreateLiveData() {
        viewModel.mutableLiveData.observe(viewLifecycleOwner){
            if (it!=null){
              showToast("Created successfully")
                requireActivity().onBackPressed()

            }else{
                showToast("Created Failed")
            }
        }

    }
    private fun dataPicker() {
        val dialog = DatePickerDialog(
            requireContext(),
            android.R.style.Theme_Holo_Light_Dialog_MinWidth,
            startDateSetListener,
            cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)
        )
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()
    }
}