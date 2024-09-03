package com.example.hospitalmanagementsystem.ui.common.tasks.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hospitalmanagementsystem.databinding.ItemRecyclerToDoBinding
import com.example.hospitalmanagementsystem.ui.common.reports.data.model.ReportsData
import com.example.hospitalmanagementsystem.ui.common.tasks.data.model.ToDo

class AdapterRecyclerToDo : RecyclerView.Adapter<AdapterRecyclerToDo.Holder>() {

    var toDo: List<ToDo> = emptyList()


    inner class Holder(val binding: ItemRecyclerToDoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(toDo: ToDo) {
            binding.apply {
                textTittle.text = toDo.title

            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding =
            ItemRecyclerToDoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun getItemCount(): Int {
        return toDo.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(toDo[position])
    }


}