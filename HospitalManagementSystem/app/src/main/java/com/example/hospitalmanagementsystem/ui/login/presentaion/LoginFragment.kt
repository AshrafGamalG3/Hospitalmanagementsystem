package com.example.hospitalmanagementsystem.ui.login.presentaion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController

import com.example.hospitalmanagementsystem.R
import com.example.hospitalmanagementsystem.utils.Const
import com.example.hospitalmanagementsystem.databinding.FragmentLoginBinding
import com.example.hospitalmanagementsystem.data.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {
    var _binding: FragmentLoginBinding? = null
    val binding get() = _binding!!

    val viewModel: LoginViewModel by viewModels ()
    override fun onCreate(savedInstanceState:  Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentLoginBinding.bind(view)
        onClick()
        observe()

    }
    fun onClick(){
        binding.btnLogin.setOnClickListener {
            validation()

        }
    }


    private fun validation() {
        val email = binding.editEmail.text.toString().trim()
        val password = binding.editTextPassword.text.toString().trim()

        if (email.isEmpty()) {
            binding.editEmail.error = getString(R.string.required)

        } else if (password.isEmpty()) {
            binding.editTextPassword.error = getString(R.string.required)
        }  else {

           viewModel.login(email,password," ")
        }


    }

    fun observe() {
        viewModel.mutableLiveData.observe(viewLifecycleOwner) {
            if (it.status == 1 && it!=null) {
                showToast("Login successfully")
                navigateUserToHome(it.type)
            }else{
                showToast("Login Failed")
            }
        }
    }


    private fun navigateUserToHome (type : String) {

        if (type == Const.HR) {
            val action=LoginFragmentDirections.actionLoginFragmentToHrHomeFragment()
            findNavController().navigate(action)

        }
        else if (type == Const.RECEPTIONIST){
              val action=  LoginFragmentDirections.actionLoginFragmentToReceptionHome()
            findNavController().navigate(action)

        }else if (type == Const.DOCTOR) {
            val action=LoginFragmentDirections.actionLoginFragmentToDoctorHomeFragment()
            findNavController().navigate(action)

        }else if (type == Const.ANALYSIS) {



        }
        else if (type == Const.MANAGER) {

        }
        else if (type == Const.NURSE) {
        findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToNurseHomeFragment())

        }


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}