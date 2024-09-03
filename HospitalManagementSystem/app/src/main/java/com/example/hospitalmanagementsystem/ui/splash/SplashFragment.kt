package com.example.hospitalmanagementsystem.ui.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.hospitalmanagementsystem.R
import com.example.hospitalmanagementsystem.utils.Const
import com.example.hospitalmanagementsystem.utils.MySharedPreferences
import com.example.hospitalmanagementsystem.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint


import android.app.AlertDialog
    @AndroidEntryPoint
    class SplashFragment : Fragment() {
        private var _binding: FragmentSplashBinding? = null
        private val binding get() = _binding!!

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
        }

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            return inflater.inflate(R.layout.fragment_splash, container, false)
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            _binding = FragmentSplashBinding.bind(view)

            Log.e("TAG", "onViewCreated: ", )
            Handler(Looper.myLooper()!!).postDelayed({
                navigateUser()
            }, 3000)
        }


        private fun navigateUser() {

                val userType = MySharedPreferences.getUserType()
                navigateUserToHome(userType)

        }


        private fun navigateUserToHome(type: String) {
            when (type) {
                "type" -> {
                    val action = SplashFragmentDirections.actionSplashFragmentToLoginFragment()
                    findNavController().navigate(action)
                }
                Const.HR -> {
                    val action = SplashFragmentDirections.actionSplashFragmentToHrHomeFragment()
                    findNavController().navigate(action)
                }
                Const.RECEPTIONIST -> {
                    val action = SplashFragmentDirections.actionSplashFragmentToReceptionHome()
                    findNavController().navigate(action)
                }
                Const.DOCTOR -> {
                    val action=SplashFragmentDirections.actionSplashFragmentToDoctorHomeFragment()
                    findNavController().navigate(action)

                }
                Const.ANALYSIS -> {

                }
                Const.MANAGER -> {

                }
                Const.NURSE -> {
                findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToNurseHomeFragment())
                }
                else -> {

                }
            }
        }


        override fun onDestroy() {
            super.onDestroy()
            _binding=null

        }

    }

