package com.example.hospitalmanagementsystem.ui.common.attendance.presentation

import android.icu.text.SimpleDateFormat
import android.location.Location
import android.location.LocationRequest
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.hospitalmanagementsystem.R
import com.example.hospitalmanagementsystem.data.showToast
import com.example.hospitalmanagementsystem.databinding.FragmentAttendanceBinding
import com.example.hospitalmanagementsystem.utils.Const
import com.example.hospitalmanagementsystem.utils.MySharedPreferences
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import dagger.hilt.android.AndroidEntryPoint
import java.util.Calendar
import kotlin.math.acos
import kotlin.math.cos
import kotlin.math.sin

@AndroidEntryPoint
class AttendanceFragment : BaseFragment() {
    private var _binding : FragmentAttendanceBinding? = null
    private val binding get() = _binding!!
    private val timeFormat = SimpleDateFormat("hh:mm aa")
    private val cal = Calendar.getInstance()
    private val viewModel : AttendanceViewModel by viewModels()
    private var mFusedLocationClient: FusedLocationProviderClient? = null
    private var mLocationRequest: LocationRequest? = null
    private var mLocationCallback: LocationCallback? = null
    var userLocation : Location?= null

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
        return inflater.inflate(R.layout.fragment_attendance, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentAttendanceBinding.bind(view)
    }

    private fun initView (){


        binding.apply {

            if (MySharedPreferences.getUserAttended() == ""){
                icCondation.visibility= View.GONE
            }else{
                textTime.text = MySharedPreferences.getUserAttended()
                icCondation.visibility= View.GONE
            }
            if (MySharedPreferences.getUserLeaving() == ""){
                icCondationLeaving.visibility= View.GONE
            }else{
                textTimeLeaving.text  = MySharedPreferences.getUserLeaving()
                icCondationLeaving.visibility= View.VISIBLE
            }

        }

    }



    private fun onClicks (){
        binding.apply {


            btnBack.setOnClickListener {
                myActivity?.onBackPressed()
            }

            btnAttendance.setOnClickListener {
                if (MySharedPreferences.getUserAttended() != ""){
                    showToast(getString(R.string.attended_massage))
                    return@setOnClickListener
                }

                if (userLocation == null){
                    binding.massage.text =getString(R.string.location_warn)
                    binding.noteUi.visibility= View.GONE
                    return@setOnClickListener
                }
                if (distance(userLocation?.latitude!!
                        ,userLocation?.longitude!!
                        ,29.936074
                        , 30.883889) > 5){// this num for location of hospital
                    binding.noteUi.visibility= View.GONE

                    return@setOnClickListener

                }
                binding.noteUi.visibility= View.GONE
                MySharedPreferences.setUserAttended(timeFormat.format(System.currentTimeMillis()))
                viewModel.attendance(Const.ATTENDANCE)
            }
            btnLeaving.setOnClickListener {
                if (MySharedPreferences.getUserLeaving() != ""){
                    showToast(getString(R.string.attended_massage))
                    return@setOnClickListener
                }

                if (userLocation == null){
                    binding.massage.text =getString(R.string.location_warn)
                    binding.noteUi.visibility= View.VISIBLE
                    return@setOnClickListener
                }

                if (distance(userLocation?.latitude!!
                        ,userLocation?.longitude!!
                        ,29.936074
                        ,30.883889) > 5){
                    binding.massage.text =getString(R.string.location_warn)
                    binding.noteUi.visibility= View.VISIBLE

                    return@setOnClickListener

                }
                binding.noteUi.visibility= View.GONE
                MySharedPreferences.setUserLeaving(timeFormat.format(System.currentTimeMillis()))
                viewModel.attendance(Const.LEAVING)
            }
        }


    }


    private fun distance(
        lat1: Double,
        lon1: Double,
        lat2: Double,
        lon2: Double,
    ): Double {
        return if (lat1 == lat2 && lon1 == lon2) {
            0.0
        } else {
            val theta = lon1 - lon2
            var dist =
                sin(Math.toRadians(lat1)) * sin(
                    Math.toRadians(lat2)
                ) + cos(Math.toRadians(lat1)) * cos(
                    Math.toRadians(
                        lat2
                    )
                ) * cos(Math.toRadians(theta))
            dist = acos(dist)

            dist = Math.toDegrees(dist)
            dist *= 60 * 1.1515


            dist *= 1.609344

            dist
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}