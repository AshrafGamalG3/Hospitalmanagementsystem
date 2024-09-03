package com.example.hospitalmanagementsystem.ui.common.reports.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hospitalmanagementsystem.databinding.ItemReceptionCallBinding
import com.example.hospitalmanagementsystem.ui.common.reports.data.model.ReportsData

class ReportsAdapter :RecyclerView.Adapter<ReportsAdapter.Holder>() {

    var reports: List<ReportsData> = emptyList()
    var onItemClickListener: OnItemClickListener? = null



    inner class Holder(private val binding: ItemReceptionCallBinding) :RecyclerView.ViewHolder(binding.root) {

        fun bind(report: ReportsData) {
            binding.textName.text = report.report_name
            binding.textDate.text = report.created_at
        binding.root.setOnClickListener {
            onItemClickListener?.onItemClick(report)

        }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemReceptionCallBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun getItemCount(): Int {
        return    reports.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(reports[position])
    }

    interface OnItemClickListener {
        fun onItemClick(report: ReportsData)

    }
}