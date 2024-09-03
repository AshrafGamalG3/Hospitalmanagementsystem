package com.example.hospitalmanagementsystem.ui.doctor.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hospitalmanagementsystem.databinding.ItemRecyclerAllCasesBinding
import com.example.hospitalmanagementsystem.ui.doctor.data.model.DataDoctorCases

class AdapterRecyclerAllCases : RecyclerView.Adapter<AdapterRecyclerAllCases.Holder>() {


    var listDataDoctorCases = ArrayList<DataDoctorCases>()
    var onShowClick : OnShowClick ?= null


    inner class Holder(val binding: ItemRecyclerAllCasesBinding) :RecyclerView.ViewHolder(binding.root){

        fun bind(dataDoctorCases: DataDoctorCases){
        binding.textName.text=dataDoctorCases.patient_name
            binding.textDate.text=dataDoctorCases.created_at

            binding.btnShowCase.setOnClickListener {
                onShowClick?.show(dataDoctorCases.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding=ItemRecyclerAllCasesBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return Holder(binding)
    }

    override fun getItemCount(): Int {
return listDataDoctorCases.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(listDataDoctorCases[position])
    }

    interface OnShowClick {
        fun show (id : Int)

    }
}