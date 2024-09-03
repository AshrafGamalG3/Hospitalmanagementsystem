package com.example.hospitalmanagementsystem.ui.doctor.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hospitalmanagementsystem.databinding.ItemDoctorCallsBinding
import com.example.hospitalmanagementsystem.ui.doctor.data.model.DataDoctorCalls
class AdapterDoctorCalls : RecyclerView.Adapter<AdapterDoctorCalls.Holder>() {
    var doctorCalls = ArrayList<DataDoctorCalls>()
    var onUserClick: OnUserClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemDoctorCallsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun getItemCount(): Int {
        return doctorCalls.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(doctorCalls[position], position)
    }

    inner class Holder(val binding: ItemDoctorCallsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(dataDoctorCalls: DataDoctorCalls, position: Int) {
            binding.textName.text = dataDoctorCalls.patient_name
            binding.textDate.text = dataDoctorCalls.created_at
            binding.btnAccept.setOnClickListener {
                onUserClick?.onAccept(dataDoctorCalls.id, position)
            }
            binding.btnReject.setOnClickListener {
                onUserClick?.onReject(dataDoctorCalls.id, position)
            }
        }
    }

    interface OnUserClick {
        fun onAccept(id: Int, position: Int)
        fun onReject(id: Int, position: Int)
    }
}
