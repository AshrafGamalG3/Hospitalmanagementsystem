package com.example.hospitalmanagementsystem.ui.common.tasks.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hospitalmanagementsystem.databinding.ItemReceptionCallBinding
import com.example.hospitalmanagementsystem.ui.common.tasks.data.model.ModelAllTasks
import com.example.hospitalmanagementsystem.ui.common.tasks.data.model.TasksData

class TasksAdapter : RecyclerView.Adapter<TasksAdapter.Holder>() {
     var list = listOf<TasksData>()
    var listener: onItemClickListener? = null

    inner class Holder(private val binding: ItemReceptionCallBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: TasksData){
            binding.apply {
                textName.text = item.task_name
                textDate.text = item.created_at

                binding.root.setOnClickListener {
                    listener?.onItemClick(item)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemReceptionCallBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
    holder.bind(list[position])
    }
    interface onItemClickListener{
        fun onItemClick(item: TasksData)

    }
}