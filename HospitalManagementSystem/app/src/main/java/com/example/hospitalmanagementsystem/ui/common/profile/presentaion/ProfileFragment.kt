package com.example.hospitalmanagementsystem.ui.common.profile.presentaion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels

import com.example.hospitalmanagementsystem.R
import com.example.hospitalmanagementsystem.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {
    var _binding:FragmentProfileBinding?=null
    val binding get() = _binding!!
    val viewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding= FragmentProfileBinding.bind(view)
        val  id=ProfileFragmentArgs.fromBundle(requireArguments()).id

        getProfileData(id)
            observe()
            onClicks()
    }



fun getProfileData(id:Int){
viewModel.getProfileData(id)
}
    fun observe(){
        viewModel.mutableLiveData.observe(viewLifecycleOwner){
            binding.textAddress.text=it.address
            binding.textEmail.text=it.email
            binding.textName.text=it.name
            binding.textPhone.text=it.mobile
            binding.textStatus.text=it.status
            binding.textType.text=it.type
            binding.textBirthday.text=it.birthday
            binding.textGender.text=it.gender

        }
    }
    fun onClicks(){
        binding.btnBack.setOnClickListener(View.OnClickListener {
            requireActivity().onBackPressed()
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}