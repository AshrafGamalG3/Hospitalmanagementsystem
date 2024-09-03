package com.magdy.hospitalsystem.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hospitalmanagementsystem.R
import com.example.hospitalmanagementsystem.databinding.ItemAnalysisBinding
import com.google.android.material.button.MaterialButton


class AdapterRecyclerAnalysis  : RecyclerView.Adapter<AdapterRecyclerAnalysis.Holder>() {


    var list = mutableListOf<String>()
    var onRemoveClick : OnRemoveClick ?= null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding=ItemAnalysisBinding.inflate(LayoutInflater.from(parent.context),parent,false)


        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val data = list[position]
        holder.bind(data)


    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class Holder (val binding: ItemAnalysisBinding) : RecyclerView.ViewHolder(binding.root){



        fun bind(text : String){
                binding.textAnalysis.text=text
            binding.btnRemove.setOnClickListener {
                onRemoveClick?.onClick(text)

            }
        }


    }

    interface OnRemoveClick {
        fun onClick (name : String)

    }
}