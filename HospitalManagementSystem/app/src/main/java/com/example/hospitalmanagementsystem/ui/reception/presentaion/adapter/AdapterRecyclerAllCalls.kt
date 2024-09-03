package com.example.hospitalmanagementsystem.ui.reception.presentaion.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hospitalmanagementsystem.databinding.ItemReceptionCallBinding
import com.example.hospitalmanagementsystem.ui.reception.domain.model.ModelCalls

class AdapterRecyclerAllCalls : RecyclerView.Adapter<AdapterRecyclerAllCalls.Holder>() {

     lateinit var callsList: List<ModelCalls>
    var onClickCall : OnClickCall?= null




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding=ItemReceptionCallBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return Holder(binding)

    }

    override fun getItemCount(): Int {
        return callsList.size

    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(callsList[position])
    }
    inner class Holder(val binding: ItemReceptionCallBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(modelCalls: ModelCalls){
            binding.textDate.text=modelCalls.date
            binding.textName.text=modelCalls.name

            binding.root.setOnClickListener {
                onClickCall?.onClick(callsList[adapterPosition].id)
            }
        }

    }

    interface OnClickCall {
        fun onClick (id : Int)
    }
}